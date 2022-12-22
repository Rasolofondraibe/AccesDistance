import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ChoixDuClient
 */
public class ChoixDuClient extends Thread {
    private ServerSocket serveurSocket;
    private DataInputStream dataInputStream;
    private static InputStream input;
    private static DataOutputStream dout;

    public ChoixDuClient(ServerSocket serverSocket) {
        this.serveurSocket = serverSocket;
        start();
    }

    public void run() {
        JFrame frame = new JFrame("Choix du client");
        int y = 0;
        while (true) {
            try {
                frame.setSize(700, 800);
                frame.setLayout(null);
                Socket s = serveurSocket.accept();
                dataInputStream = new DataInputStream(s.getInputStream());
                input = s.getInputStream();
                OutputStream outputStream = s.getOutputStream();
                dout = new DataOutputStream(s.getOutputStream());
                JPanel panel = new JPanel();
                JLabel label = new JLabel(dataInputStream.readUTF());
                JButton button = new JButton("Acceder !");
                button.setName("Go");
                Listeners ls = new Listeners(button, serveurSocket, s, input, dout);
                panel.setBounds(0, 10 + y, 400, 70);
                button.addMouseListener(ls);
                panel.add(label);
                panel.add(button);

                frame.add(panel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setVisible(true);

                y += 50;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}