import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

public class SendEvents implements MouseListener, MouseMotionListener, KeyListener {

    PrintWriter writer;
    int width, height;
    JFrame frame;

    public SendEvents(Socket s) {
        try {
            writer = new PrintWriter(s.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        writer.println("4");
        int code = e.getKeyCode();
        writer.println(code);
        writer.flush();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        writer.println("5");
        int code = e.getKeyCode();
        writer.println(code);
        writer.flush();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

        writer.println("3");
        writer.println(e.getX() + ":" + e.getY());
        writer.flush();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        writer.println("1");
        System.out.println("Mouse Pressed");
        int Click = 4;
        if (e.getButton() == MouseEvent.BUTTON1) {
            Click = 16;
        } else if (e.getButton() == MouseEvent.BUTTON2) {
            Click = 4;
        }
        System.out.println(Click);
        writer.println(Click);
        writer.flush();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        // writer.println("1");
        // System.out.println("Mouse Pressed");
        // int click = e.getButton();
        // writer.println(click);
        // writer.flush();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        writer.println("2");
        System.out.println("Mouse released");
        int click = e.getButton();
        writer.println(click);
        writer.flush();

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
