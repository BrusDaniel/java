import javax.swing.*;

public class BoxLayoutExample {
    public static void main(String[] args) {
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
        BoxLayout styl = new BoxLayout(warstwa, BoxLayout.X_AXIS);
        warstwa.setLayout(styl);

        warstwa.add(przycisk1);
        warstwa.add(przycisk2);
        warstwa.add(przycisk3);
        warstwa.add(przycisk4);
        warstwa.add(przycisk5);
        warstwa.add(przycisk6);


        okno.add(warstwa);
        okno.setVisible(true);

    }
}