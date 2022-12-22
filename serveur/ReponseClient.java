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
public class ReponseClient extends Thread {

    private static Socket socket;
    private static ServerSocket serverSocket = null;
    private static Robot r = null;
    private static InputStream input;
    private static DataOutputStream dout;
    public OutputStream s;
    private static Paneel panel = new Paneel();

    public ReponseClient(ServerSocket serverSocket, Socket socket, DataOutputStream dout) {
        this.serverSocket = serverSocket;
        this.socket = socket;
        this.dout = dout;
        start();
    }

    public void run() {
        try {
            JFrame frame = new JFrame();
            // System.out.println(input);
            input = socket.getInputStream();
            grapheImage(this.socket, dout, frame);
            new ReceiveImage(input, panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void grapheImage(Socket socket, DataOutputStream output, JFrame frame)
            throws PropertyVetoException, IOException {
        JDesktopPane desktop = new JDesktopPane();
        JInternalFrame internalFrame = new JInternalFrame("Screen Of ", true, true, true);
        System.out.println(socket);
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
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}