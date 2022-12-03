import java.net.*;
import java.io.*;
/**
 * testServer
 */
public class Serveur  extends Thread{

    final static int port = 9632;
  private Socket socket;

  public static void main(String[] args) {
    try {
      try (ServerSocket socketServeur = new ServerSocket(port)) {
        System.out.println("Lancement du serveur");
        
            Socket socketClient = socketServeur.accept();
          while (true) {
            testServer t = new testServer(socketClient);
            t.start();
          }
    }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public testServer(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    traitements();
  }

  public void traitements() {
    try {
      String message = "";

      System.out.println("Connexion avec le client : " + socket.getInetAddress());

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintStream out = new PrintStream(socket.getOutputStream());
      message = in.readLine();
      out.println("Bonjour " + message);

      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}