package business;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

public class Exercise {
    static final String user = "student";
    static final String password = "n23PTn9YHfRDE6KPNJTakd4cfmNVj62jd8kr2REi2i8Tn";
    static final String host = "blockchain.oss.unist.hr";
    static final String port = "8332";

    public static void showInfo() throws IOException {

        URL url = new URL("http://" + user + ':' + password + "@" + host + ":" + port + "/");

        BitcoinJSONRPCClient client = new BitcoinJSONRPCClient(url);

        //Task1
        String transactionId = "2ad96239079af5b507bb80e2eca4991150ea2b27c23c31702a1227f1698839ae";

        try {
            // Obtener información de la transacción
            BitcoindRpcClient.RawTransaction rawTransaction = client.getRawTransaction(transactionId);
            System.out.println("Transacción cruda: " + rawTransaction);

            // Convertir la transacción cruda a un objeto JSON o trabajar con la cadena según tus necesidades

            // Puedes contar el número de outputs
            int numOutputs = rawTransaction.vOut().size();
            System.out.println("Number of outputs: " + numOutputs);

            //Task 2

            String blockHash = client.getBlockHash(123123);
            BitcoindRpcClient.Block block = client.getBlock(blockHash);

            int numTransactions = block.tx().size();
            System.out.println("Number of transactions in the block: " + numTransactions);

            //Obtener transacciones del bloque
            System.out.println("Transactions:\n");
            for (String txid: block.tx()) {
                rawTransaction = client.getRawTransaction(txid);

                //Calcular suma outputs de la transacción
                BigDecimal totalAmmount = BigDecimal.valueOf(0);
                for (BitcoindRpcClient.RawTransaction.Out out:rawTransaction.vOut()) {
                    totalAmmount = totalAmmount.add(out.value());
                }

                System.out.println("Transaction: " + txid + "\nTotal ammount: " + totalAmmount + "\n");

            }

            //Task 3
            transactionId = "01666c67758fdd1c6f956a0f9bc5f1282d919fa00f93b782848e29a6fdda4a2a";
            rawTransaction = client.getRawTransaction(transactionId);

            //Calcular suma inputs de la transacción
            BigDecimal totalInputs = BigDecimal.valueOf(0);
            for (BitcoindRpcClient.RawTransaction.In in:rawTransaction.vIn()) {
                String inputTxId = in.txid();
                int inputVout = in.vout();

                // Obtener información de la transacción de referencia (la transacción de la que viene el input)
                BitcoindRpcClient.RawTransaction previousTransaction = client.getRawTransaction(inputTxId);
                // Sumamos el output de la previous tx coincidente con el input de nuestra tx
                totalInputs = totalInputs.add(previousTransaction.vOut().get(inputVout).value());
            }

            //Calcular suma outputs de la transacción
            BigDecimal totalOutputs = BigDecimal.valueOf(0);
            for (BitcoindRpcClient.RawTransaction.Out out:rawTransaction.vOut()) {
                totalOutputs = totalOutputs.add(out.value());
            }

            //Calculamos el fee de la transacción como la diferencia entre las sumas de inputs y outputs
            System.out.println("The fee of the transaction is: " + (totalInputs.subtract(totalOutputs)) );
        } catch (RuntimeException e) {
            System.err.println("Error al obtener información de la transacción: " + e.getMessage());
        }



        //
    }
}
