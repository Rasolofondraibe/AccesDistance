import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;

import java.awt.image.RenderedImage;
import java.beans.PropertyVetoException;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import panel.Paneel;

public class ReceiveImage extends Thread {

  private static InputStream input;
  private static Paneel panel;

  public ReceiveImage(InputStream input, Paneel panel) {
    this.input = input;
    this.panel = panel;
    start();
  }

  public void run() {
    while (true) {
      Image image = null;
      try {

        byte[] nbByte = new byte[4];
        input.read(nbByte);

        int size = ByteBuffer.wrap(nbByte).asIntBuffer().get();
        byte[] imageAr = new byte[size];
        int nbrRead = 0;
        int currentRead = 0;
        while (nbrRead < size && (currentRead = input.read(imageAr, nbrRead, size - nbrRead)) > 0) {
          nbrRead += currentRead;
        }
        image = ImageIO.read(new ByteArrayInputStream(imageAr));
        Graphics g = panel.getGraphics();

        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
