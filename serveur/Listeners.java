import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;

public class Listeners implements MouseListener {
    public JButton button;
    public ServerSocket serverSocket;
    public Socket socket;
    private static InputStream input;
    private static DataOutputStream dout;

    public Listeners(JButton button, ServerSocket serverSocket, Socket socket, InputStream input,
            DataOutputStream dout) {
        this.button = button;
        this.serverSocket = serverSocket;
        this.input = input;
        this.dout = dout;
        this.socket = socket;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (button.getName().equals("Go")) {
            new ReponseClient(serverSocket, this.socket, dout);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
