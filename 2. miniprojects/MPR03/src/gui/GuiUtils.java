package gui;

import javax.swing.*;
import java.awt.*;

public class GuiUtils {

    public static JLabel createImageLabel(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(image));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public static JPanel createNavigationPanel(MyFrame myFrame) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBackground(new Color(246, 241, 248));

        JButton backButton = new JButton("Back");
        JButton logoutButton = new JButton("Logout");

        backButton.setBackground(new Color(232, 244, 237));
        logoutButton.setBackground(new Color(244, 218, 218));

        backButton.setFocusPainted(false);
        logoutButton.setFocusPainted(false);

        backButton.addActionListener(e -> myFrame.goBack());
        logoutButton.addActionListener(e -> myFrame.showLoginPanel());

        panel.add(backButton);
        panel.add(logoutButton);

        return panel;
    }
}