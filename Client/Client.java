import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 * @author Niaina
 */
public class Client {
  private static Socket s = null;
  private static OutputStream output;
  private static DataInputStream input;

  public static void main(String[] args) throws UnknownHostException, IOException, AWTException {
    s = new Socket("localhost", 5000);
    Robot r = new Robot();
    input = new DataInputStream(s.getInputStream());
    getImage(r);
    getCommande(r, input);

  }

  private static void getImage(Robot r) {
    while (true) {
      Image image = null;
      Rectangle rect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
      try {
        output = s.getOutputStream();
        image = r.createScreenCapture(rect);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // ImageIO.write((RenderedImage) image, "png", new File("F:/Acces
        // final/image.png"));
        ImageIO.write((RenderedImage) image, "jpg", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        output.write(size);
        output.write(byteArrayOutputStream.toByteArray());
        output.flush();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void getCommande(Robot robot, DataInputStream input) {
    while (true) {
      try {
        new ReceiveEvent(s, robot);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
