import javax.swing.*;
import java.awt.*;

public class ex3 {
    public static void main (String [] args){
        JFrame frame = new JFrame();
        frame.setSize(400,600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.BLUE);
        panel1.setBounds(0,0,400,200);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.PINK);
        panel2.setBounds(0,200,400,200);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(panel2);

        JButton button1 = new JButton("first");
        panel1.add(button1, FlowLayout.LEFT);

        JButton button2 = new JButton("second");
        panel1.add(button2, FlowLayout.CENTER);

        JButton button3 = new JButton("third");
        panel1.add(button3, FlowLayout.RIGHT);



        button1.addActionListener(e -> {
            JLabel label = new JLabel("nowy");
            panel2.add(label);
            frame.revalidate();
            frame.repaint();
        });

        button2.addActionListener(e -> {
            JLabel label = new JLabel("nowy");
            panel2.add(label);
            frame.revalidate();
            frame.repaint();
        });

        button3.addActionListener(e -> {
            JLabel label = new JLabel("nowy");
            panel2.add(label);
            frame.revalidate();
            frame.repaint();
        });

    }
}
