package presentation;

import business.BlockchainManager;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }


    public void showWelcome() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("  ⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣶⣾⣿⣿⣿⣿⣷⣶⣦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println(" ⠀⠀⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀");
        System.out.println(" ⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀");
        System.out.println(" ⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⠟⠿⠿⡿⠀⢰⣿⠁⢈⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀");
        System.out.println(" ⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣤⣄⠀⠀⠀⠈⠉⠀⠸⠿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀");
        System.out.println(" ⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⢠⣶⣶⣤⡀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⡆");
        System.out.println(" ⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠼⣿⣿⡿⠃⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣷");
        System.out.println(" ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢀⣀⣀⠀⠀⠀⠀⢴⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        System.out.println(" ⢿⣿⣿⣿⣿⣿⣿⣿⢿⣿⠁⠀⠀⣼⣿⣿⣿⣦⠀⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⡿");
        System.out.println(" ⠸⣿⣿⣿⣿⣿⣿⣏⠀⠀⠀⠀⠀⠛⠛⠿⠟⠋⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⠇");
        System.out.println(" ⠀⢻⣿⣿⣿⣿⣿⣿⣿⠇⠀⣤⡄⠀⣀⣀⣀⣀⣠⣾⣿⣿⣿⣿⣿⣿⣿⡟⠀");
        System.out.println("⠀ ⠀⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀");
        System.out.println("⠀ ⠀⠀⠀⠀⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀");
        System.out.println("⠀  ⠀⠀⠀⠀⠀⠀⠀⠉⠛⠻⠿⢿⣿⣿⣿⣿⡿⠿⠟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀");

    }

    public void showMenu() {
        System.out.println("\n=== Bitcoin Blockchain Manager Menu ===");
        System.out.println("1. Search a Block.");
        System.out.println("2. Search a Transaction.");
        System.out.println("3. Display Network Statistics.");
        System.out.println("4. Display Mempool Transactions");
        System.out.println("5. Display Mining Information");
        System.out.println("6. Display Node Connected Information");
        System.out.println("9. Exit");
    }

    /**
     * Asks for an integer to the user.
     *
     * @param message Message shown to ask for the integer.
     * @return The integer provided.
     */
    public int askForInteger(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("This isn't an integer!");
            } finally {
                scanner.nextLine();     //for deleting the \n introduced after the number
            }
        }
    }

    /**
     * Asks for a String to the user.
     *
     * @param message Message shown to ask for the String.
     * @return The String provided.
     */
    public String askForString(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextLine().trim();   //.trim() eliminara \n y posibles espacios introducidos al final del texto
            } catch (InputMismatchException e) {
                System.out.println("This isn't a text!");
            }
        }
    }


    //Block
    public void showBlockInfo(BitcoindRpcClient.Block block) {
        System.out.println("--------------------------------------------------");
        System.out.println("|                Block Information               |");
        System.out.println("--------------------------------------------------");
        System.out.println("Block Hash:         " + block.hash());
        System.out.println("Block Height:       " + block.height());
        System.out.println("Block size:         " + block.size() + " bytes");
        System.out.println("Difficulty:         " + (block.difficulty().divide(BigDecimal.valueOf(1000000))) + " MH");
        System.out.println("Timestamp:          " + block.time());
        System.out.println("Merkle Root:        " + block.merkleRoot());
        System.out.println("Total transactions: " + block.tx().size());
        System.out.println("--------------------------------------------------\n");

    }

    public void showBlockOptions() {
        System.out.println("\n=== Block options ===");
        System.out.println("1. Show transactions.");
        System.out.println("2. Show next block.");
        System.out.println("3. Show previous block.");
        System.out.println("9. Exit");
    }

    public void showBlockTransactions(BitcoindRpcClient.Block block) {
        System.out.println("--------------------------------------------------");
        System.out.println("|              Transactions in Block             |");
        System.out.println("--------------------------------------------------");
        for (String tx : block.tx()) {
            System.out.println(tx);
        }
    }


    //Transactions

    /**
     * Displays detailed information about a Bitcoin transaction.
     *
     * @param tx                The transaction to display information about.
     * @param blockchainManager The BlockchainManager for retrieving additional transaction details.
     */
    public void showTransactionInfo(BitcoindRpcClient.RawTransaction tx, BlockchainManager blockchainManager) {
        System.out.println("--------------------------------------------------");
        System.out.println("|             Transaction Information            |");
        System.out.println("--------------------------------------------------");
        System.out.println("Transaction Hash ID: " + tx.txId());
        System.out.println("Included in block:   " + tx.blockHash());
        System.out.println("Fee:                 " + blockchainManager.getTransactionFee(tx));
        System.out.println("Size:                " + tx.size() + " bytes");
        System.out.println("Date/Time:           " + tx.time());
        System.out.println("--------------------------------------------------");
        System.out.println("|                     Inputs                    |");
        System.out.println("--------------------------------------------------");
        int i = 0;
        for (BitcoindRpcClient.RawTransaction.In in : tx.vIn()) {
            String inputTxId = in.txid();
            int inputVout = in.vout();

            // Obtener información de la transacción de referencia (la transacción de la que viene el input)
            BitcoindRpcClient.RawTransaction previousTransaction = blockchainManager.getRawTransaction(inputTxId);
            BigDecimal amount = previousTransaction.vOut().get(inputVout).value();

            // Imprimir las direcciones de los inputs junto su cantidad
            List<String> addresses = previousTransaction.vOut().get(inputVout).scriptPubKey().addresses();
            if (addresses != null && !addresses.isEmpty()) {
                // Imprimir todas las direcciones de los outputs
                for (String address : addresses) {
                    System.out.println(i + ") " + address + "\t" + amount + " BTC");
                }
            } else {
                // Manejar caso en que no haya dirección asociada (puede ser el caso de OP_RETURN, por ejemplo)
                System.out.println(i + ") " + "No address (OP_RETURN)\t" + amount + " BTC");
            }
            i++;
        }

        System.out.println("--------------------------------------------------");
        System.out.println("|                     Outputs                    |");
        System.out.println("--------------------------------------------------\n");
        i = 0;
        for (BitcoindRpcClient.RawTransaction.Out out : tx.vOut()) {
            BigDecimal amount = out.value();
            List<String> addresses = out.scriptPubKey().addresses();
            if (addresses != null && !addresses.isEmpty()) {
                // Imprimir todas las direcciones de los outputs
                for (String address : addresses) {
                    System.out.println(i + ") " + address + "\t" + amount + " BTC");
                }
            } else {
                // Manejar caso en que no haya dirección asociada (puede ser el caso de OP_RETURN, por ejemplo)
                System.out.println(i + ") " + "No address (OP_RETURN)\t" + amount + " BTC");
            }
            i++;
        }
        System.out.println("--------------------------------------------------");

    }


    /**
     * Displays statistics and information about the Bitcoin network.
     *
     * @param networkInfo The NetworkInfo object containing network-related details.
     */
    public void displayNetworkInfo(BitcoindRpcClient.NetworkInfo networkInfo) {
        System.out.println("--------------------------------------------------");
        System.out.println("|           Bitcoin Network Statistics           |");
        System.out.println("--------------------------------------------------");
        System.out.println("Version: " + networkInfo.version());
        System.out.println("Subversion: " + networkInfo.subversion());
        System.out.println("Protocol Version: " + networkInfo.protocolVersion());
        System.out.println("Connections: " + networkInfo.connections());
        System.out.println("Relay Fee: " + networkInfo.relayFee());
        System.out.println("Time Offset: " + networkInfo.timeOffset());

    }


    /**
     * Displays a list of transaction hashes currently present in the Bitcoin mempool.
     *
     * @param transactionsHash List of transaction hashes in the mempool.
     */
    public void displayMempoolInfo(List<String> transactionsHash) {
        System.out.println("--------------------------------------------------");
        System.out.println("|          Mempool Transactions (Hash)           |");
        System.out.println("--------------------------------------------------");
        System.out.println(transactionsHash);

    }

    /**
     * Displays information about Bitcoin mining.
     *
     * @param miningInfo The MiningInfo object containing mining-related details.
     */
    public void displayMiningInfo(BitcoindRpcClient.MiningInfo miningInfo) {
        System.out.println("--------------------------------------------------");
        System.out.println("|               Mining Information               |");
        System.out.println("--------------------------------------------------");
        System.out.println("Blocks: " + miningInfo.blocks());
        System.out.println("Difficulty: " + miningInfo.difficulty());
        System.out.println("Network Hashrate: " + miningInfo.networkHashps());
        System.out.println("Pooled transactions: " + miningInfo.pooledTx());

    }

    /**
     * Displays information about the connected Bitcoin node.
     *
     * @param client The BitcoinJSONRPCClient connected to the node.
     */
    public void displayNodeInfo(BitcoindRpcClient client) {
        try {
            // Obtiene información sobre el nodo
            String version = client.getNetworkInfo().subversion();
            String blockHash = client.getBestBlockHash();
            int blockCount = client.getBlockCount();
            long connections = client.getConnectionCount();

            // Muestra la información en la consola
            System.out.println("--------------------------------------------------");
            System.out.println("|             Bitcoin Node Information            |");
            System.out.println("--------------------------------------------------");
            System.out.println("Version: " + version);
            System.out.println("Best Block Hash: " + blockHash);
            System.out.println("Block Count: " + blockCount);
            System.out.println("Connections: " + connections);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
