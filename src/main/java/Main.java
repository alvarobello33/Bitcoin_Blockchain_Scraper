import business.BlockchainManager;
import business.BusinessException;
import business.Exercise;
import presentation.Controller;
import presentation.Menu;
import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Main {



    public static void main(String[] args) throws IOException {

        Menu menu = new Menu();

        try {
            BlockchainManager blockchainManager = new BlockchainManager();
            // Crear una instancia del controlador y ejecutar la aplicación
            Controller controller = new Controller(menu, blockchainManager);
            controller.run();
            //Exercise.showInfo();
        } catch (Exception e) {
            // En caso de excepción, mostrar el mensaje de error en el menú
            System.out.println(e.toString());
        }

    }
}