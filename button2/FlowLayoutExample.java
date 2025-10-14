import javax.swing.*;
import java.awt.*;

public class FlowLayoutExample {
    public static void main(String[] args) {
        JFrame okno = new JFrame();
        okno.setSize(500, 700);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton przycisk1 = new JButton("a");
        JButton przycisk2 = new JButton("b");
        JButton przycisk3 = new JButton("c");
        JButton przycisk4 = new JButton("d");
        JButton przycisk5 = new JButton("e");
        JButton przycisk6 = new JButton("f");

        JPanel warstwa = new JPanel();
        FlowLayout styl = new FlowLayout();
        warstwa.setLayout(styl);

        warstwa.add(przycisk1);
        warstwa.add(przycisk2);
        warstwa.add(przycisk3);
        warstwa.add(przycisk4);
        warstwa.add(przycisk5);

        okno.add(warstwa);
        okno.setVisible(true);
    }
}

