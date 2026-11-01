import javax.swing.*;


public class ex2 {
    public static void main (String [] args) {
        JFrame frame = new JFrame();
        frame.setSize(400,400);
        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("click");
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JLabel label1 = new JLabel("2");
        JLabel label2 = new JLabel("7");
        panel.add(label1);
        panel.add(label2);

        button.addActionListener(
                e -> {
                    panel.remove(label1);
                    panel.remove(label2);
                    panel.add(new JLabel("9"));
                    panel.revalidate();
                    panel.repaint();
                }
        );
    }
}
