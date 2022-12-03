import java.net.*;
import java.io.*;
import java.awt.Robot;


/**
 * @author Niaina  
 */
public class Client {
    
  public static void main(String[] args) {
    URLConnection connexion = null;
    
   try {
    URL url = new URL("D:/Lesson/java/api");

    System.out.println("Connexion a l'url ...");
    connexion = url.openConnection();
      InputStream input= 	url.openStream();
      Robot r=new Robot();
      r.createMultiResolutionScreenCapture(null)

    connexion.setAllowUserInteraction(true);
    DataInputStream in = new DataInputStream(connexion.getInputStream());
    System.out.println("mety:"+input.read());
    input.close();
 
      //   while (true) {
      //     System.out.print(in.readUTF());
      // }
    } catch (Exception e) {
    	e.printStackTrace();
    } finally {
      //connexion.disconnect();
      }
    System.exit(0);
  }

  final static int port = 9632;

  // public static void main(String[] args) {
  //   try {
  //     ServerSocket socketServeur = new ServerSocket(port);
  //     System.out.println("Lancement du serveur");

  //     while (true) {
  //       Socket socketClient = socketServeur.accept();
  //       String message = "";

  //       System.out.println("Connexion avec : "+socketClient.getInetAddress());

  //       // InputStream in = socketClient.getInputStream();
  //       // OutputStream out = socketClient.getOutputStream();

  //       BufferedReader in = new BufferedReader(
  //         new InputStreamReader(socketClient.getInputStream()));
  //       PrintStream out = new PrintStream(socketClient.getOutputStream());
  //       message = in.readLine();
  //       out.println(message);

  //       socketClient.close();
  //     }
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
}