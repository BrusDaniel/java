import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.add(panel);
        GridLayout grid = new GridLayout(3,2);
        panel.setLayout(grid);

        JLabel login = new JLabel("login");
        panel.add(login);

        JTextField loginTextField = new JTextField();
        panel.add(loginTextField);

        JLabel haslo = new JLabel("haslo");
        panel.add(haslo);

        JTextField hasloTextField = new JTextField();
        panel.add(hasloTextField);

        JButton button = new JButton("Zaloguj");
        button.addActionListener(
                e -> {
                    String loginText = loginTextField.getText();
                    String hasloText = hasloTextField.getText();
                    JOptionPane.showMessageDialog(null, "Wiadomość");
        }
        );
        panel.add(button);

        panel.add(new JPanel());




        frame.pack();



    }
}