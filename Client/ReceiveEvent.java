import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveEvent extends Thread {
    static Socket s = null;
    static Robot r = null;

    public ReceiveEvent(Socket socket, Robot robot) throws IOException, AWTException {
        this.s = socket;
        this.r = robot;

        this.start();
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(s.getInputStream());

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension dimensions = toolkit.getScreenSize();

            Robot re = new Robot();
            BufferedImage st = re.createScreenCapture(new Rectangle(dimensions));

            int height = st.getHeight();
            int width = st.getWidth();

            while (true) {
                try {
                    int event = scanner.nextInt();

                    switch (event) {
                        case 1:
                            int mask = InputEvent.getMaskForButton(event);
                            r.mousePress(mask);
                           
                            System.out.println("Mouse pressed");
                            break;
                        case 2:
                            int mask1 = InputEvent.getMaskForButton(event);
                            r.mouseRelease(mask1);
                            break;
                        case 3:
                            String cood = scanner.next();
                            int x = Integer.parseInt(cood.split(":")[0]);
                            int y = Integer.parseInt(cood.split(":")[1]);
                            r.mouseMove(x, y);
                            break;
                        case 4:
                            r.keyPress(scanner.nextInt());
                            break;
                        case 5:
                            r.keyRelease(scanner.nextInt());
                            break;
                        default:
                            break;
                    }
                } catch (Exception ex) {

                    System.out.println("Exception in receive events:" + ex);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



///https://github.com/NiainaEtu001857/AccesDistance.git