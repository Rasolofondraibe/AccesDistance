import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

public class SendImage extends Thread {
  private static Socket s = null;
  private static OutputStream output;
  private static Robot r;

  public SendImage(Socket s, OutputStream output, Robot r) {
    this.s = s;
    this.output = output;
    this.r = r;
    this.start();
  }

  public void run() {
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
}
