import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.image.RenderedImage;
import java.beans.PropertyVetoException;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Robot;
import panel.Paneel;

/**
 * testServer
 */
public class Serveur extends Thread {

  final static int port = 5000;
  private static Socket socket;
  private static ServerSocket serverSocket = null;
  private static Robot r = null;
  private static InputStream input;
  private static DataOutputStream dout;
  private static Paneel panel = new Paneel();

  public static void main(String[] args) {
    try {
      serverSocket = new ServerSocket(5000);
      System.out.println("En attente de connaction ....");
      socket = serverSocket.accept();
      input = socket.getInputStream();
      dout= new DataOutputStream(socket.getOutputStream());
      JFrame frame = new JFrame();
      // System.out.println(input);
      grapheImage(dout,frame);
      getImage(frame);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void getImage(JFrame frame) {
    //while (true) {
      Image image = null;
      System.out.println("imageAr");
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
        System.out.println(g + "   projfdshvgoildskhgiudsaghfuzsldihguids");

        g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);

      } catch (IOException e) {
        e.printStackTrace();
      }
    //}
  }

  private static void grapheImage(DataOutputStream output,JFrame frame) throws PropertyVetoException {
    JDesktopPane desktop = new JDesktopPane();
    JInternalFrame internalFrame = new JInternalFrame("Screen Of ", true, true, true);
    SendEvents se = new SendEvents(socket);
    frame.add(desktop, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(3);
    frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

    internalFrame.getContentPane().add(panel, BorderLayout.CENTER);
    internalFrame.setSize(100, 100);
    desktop.add(internalFrame);

    panel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    internalFrame.setMaximum(true);
    desktop.setFocusable(false);
    internalFrame.setFocusable(false);
    panel.setFocusable(true);
    frame.setFocusable(false);

    panel.addKeyListener(se);
    panel.addMouseListener(se);
    panel.addMouseMotionListener(se);

    internalFrame.setVisible(true);
    frame.setVisible(true);

  }
}