import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample {
    public static void main (String []args){
        JFrame okno = new JFrame();
        okno.setSize(500,700);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton przycisk1 = new JButton("a");
        JButton przycisk2 = new JButton("b");
        JButton przycisk3 = new JButton("c");
        JButton przycisk4 = new JButton("d");
        JButton przycisk5 = new JButton("e");
        JButton przycisk6 = new JButton("f");

        JPanel warstwa = new JPanel();
        BorderLayout styl = new BorderLayout();
        warstwa.setLayout(styl);

        warstwa.add(przycisk1, BorderLayout.NORTH);
        warstwa.add(przycisk2, BorderLayout.SOUTH);
        warstwa.add(przycisk3, BorderLayout.CENTER);
        warstwa.add(przycisk4, BorderLayout.EAST);
        warstwa.add(przycisk5, BorderLayout.WEST);

        okno.add(warstwa);
        okno.setVisible(true);
    }
}
