package presentation;

import business.BlockchainManager;
import business.BusinessException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.math.BigDecimal;

public class Controller {
    private Menu menu;
    private BlockchainManager blockchainManager;

    public final static int EXIT = 9;

    public Controller(Menu menu, BlockchainManager blockchainManager) {
        this.menu = menu;
        this.blockchainManager = blockchainManager;
    }

    /**
     * Runs the Bitcoin Blockchain Manager application, displaying a menu and executing user-selected options.
     *
     * @throws BusinessException If there is a business-related exception during the execution.
     */
    public void run() throws BusinessException {

        int option;

        do {
            menu.showWelcome();
            menu.showMenu();
            option = menu.askForInteger("\nChoose an option: ");

            switch (option) {
                case 1:
                    searchBlock();
                    break;
                case 2:
                    searchTransaction();
                    break;
                case 3:
                    displayNetworkStadistics();
                    break;
                case 4:
                    showMempool();
                    break;
                case 5:
                    showMiningInfo();
                    break;
                case 6:
                    showNodeInfo();
                    break;
                case EXIT:
                    System.out.println("Thanks for using our Bitcoin Blockchain Manager.");
                    break;

                default:
                    System.out.println("Incorrect option");
                    break;
            }
        } while (option != EXIT);
    }

    //1
    /**
     * Performs a search for a specific Bitcoin block based on user input (hash or height) and
     * provides options for further exploration.
     */
    private void searchBlock() {
        String hash_height = menu.askForString("Introduce the hash or height of the block: ");
        BitcoindRpcClient.Block block;
        if (hash_height.length() < 11) {
            // Is height
            int height = Integer.parseInt(hash_height);
            block = blockchainManager.getBlock(height);
        } else {
            // Is hash
            block = blockchainManager.getBlock(hash_height);
        }

        int option;
        do {
            menu.showBlockInfo(block);
            menu.showBlockOptions();
            option = menu.askForInteger("\nChoose an option: ");
            switch (option) {
                case 1: //Show transactions
                    menu.showBlockTransactions(block);
                    break;
                case 2:
                    block = block.next();
                    break;
                case 3:
                    block = block.previous();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;

            }

        } while (option != 9);

    }

    //2
    /**
     * Searches for a specific Bitcoin transaction based on the provided transaction hash.
     * Displays detailed information about the transaction and provides options for further actions.
     */
    private void searchTransaction() {
        String hash = menu.askForString("Introduce the hash of the transaction: ");
        BitcoindRpcClient.RawTransaction rawTransaction = blockchainManager.getRawTransaction(hash);

        menu.showTransactionInfo(rawTransaction, blockchainManager);
    }

    //3
    private void displayNetworkStadistics() {
        menu.displayNetworkInfo(blockchainManager.getNetworkInfo());
    }

    //4
    private void showMempool() {
        menu.displayMempoolInfo(blockchainManager.getRawMempool());
    }

    //5
    private void showMiningInfo() {
        menu.displayMiningInfo(blockchainManager.getMiningInfo());
    }

    //6
    private void showNodeInfo() {
        menu.displayNodeInfo(blockchainManager.getClient());
    }

}
