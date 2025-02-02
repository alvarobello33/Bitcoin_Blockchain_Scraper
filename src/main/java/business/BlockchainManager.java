package business;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class BlockchainManager {

    private static final String user = "new_user"; // User (from the Bitcoin node)
    private static final String password = "new_password"; // Password (from the Bitcoin node)
    private static final String host = "new_node_host.com"; // Address (from the Bitcoin node)
    private static final String port = "8332"; // Port (from the Bitcoin node)

    private static BitcoinJSONRPCClient client;

    public BlockchainManager() throws BusinessException {
        try {
            URL url = new URL("http://" + user + ':' + password + "@" + host + ":" + port + "/");
            this.client = new BitcoinJSONRPCClient(url);
        } catch (MalformedURLException e) {
            throw new BusinessException("The connection with the server was not possible.", e);
        }
    }

    public BitcoindRpcClient.Block getBlock(String hash) {
        return client.getBlock(hash);
    }

    public BitcoindRpcClient.Block getBlock(int height) {
        return client.getBlock(height);
    }


    public BitcoindRpcClient.RawTransaction getRawTransaction(String hash) {
        return client.getRawTransaction(hash);
    }

    public BigDecimal getTransactionFee(BitcoindRpcClient.RawTransaction tx) {
        //Calcular suma inputs de la transacción
        BigDecimal totalInputs = BigDecimal.valueOf(0);
        for (BitcoinJSONRPCClient.RawTransaction.In in:tx.vIn()) {
            String inputTxId = in.txid();
            int inputVout = in.vout();

            // Obtener información de la transacción de referencia (la transacción de la que viene el input)
            BitcoinJSONRPCClient.RawTransaction previousTransaction = client.getRawTransaction(inputTxId);
            // Sumamos el output de la previous tx coincidente con el input de nuestra tx
            totalInputs = totalInputs.add(previousTransaction.vOut().get(inputVout).value());
        }

        //Calcular suma outputs de la transacción
        BigDecimal totalOutputs = BigDecimal.valueOf(0);
        for (BitcoinJSONRPCClient.RawTransaction.Out out:tx.vOut()) {
            totalOutputs = totalOutputs.add(out.value());
        }

        return totalInputs.subtract(totalOutputs);
    }

    public BitcoindRpcClient.NetworkInfo getNetworkInfo() {
        return client.getNetworkInfo();
    }


    public List<String> getRawMempool() {
        return client.getRawMemPool();
    }

    public BitcoindRpcClient.MiningInfo getMiningInfo() {
        return client.getMiningInfo();
    }

    public BitcoindRpcClient getClient() {
        return client;
    }
}
