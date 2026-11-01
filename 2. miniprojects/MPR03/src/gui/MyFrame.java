package gui;

import account.MayorAccountData;
import account.UserAccount;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MyFrame extends JFrame {

    private final List<UserAccount> accounts;
    private final MayorAccountData mayorData;

    private JPanel currentPanel;
    private JPanel previousPanel;

    public MyFrame(List<UserAccount> accounts, MayorAccountData mayorData) {
        this.accounts = accounts;
        this.mayorData = mayorData;

        setTitle("Municipal Office Workflow Management System");
        setSize(950, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    public void showPanel(JPanel panel) {
        previousPanel = currentPanel;
        currentPanel = panel;

        getContentPane().removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void goBack() {
        if (previousPanel != null) {
            JPanel panelToShow = previousPanel;
            previousPanel = null;
            currentPanel = panelToShow;

            getContentPane().removeAll();
            add(panelToShow, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    public void showLoginPanel() {
        previousPanel = null;
        showPanel(new LoginPanel(this, accounts, mayorData));
    }
}