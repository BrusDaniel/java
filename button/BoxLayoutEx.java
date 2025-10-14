import javax.swing.*;
import java.awt.*;

public class BoxLayoutEx {

    public static void main(String[] args) {

        JFrame boxLayout = new JFrame();
        boxLayout.setSize(500,500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton button1 = new JButton("przycisk 1");
        JButton button2 = new JButton("przycisk 2");
        JButton button3 = new JButton("przycisk 3");
        JButton button4 = new JButton("przycisk 4");

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        boxLayout.getContentPane().add(panel);

        boxLayout.setVisible(true);


    }
}