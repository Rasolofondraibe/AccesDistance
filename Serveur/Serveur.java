import java.net.ServerSocket;

/**
 * Serveur
 */
public class Serveur {

    public static void main(String[] args) throws Exception {
        ServerSocket serveurSocket = new ServerSocket(5000);
        System.out.println("Enter des clients");
        new ChoixDuClient(serveurSocket);
    }
}