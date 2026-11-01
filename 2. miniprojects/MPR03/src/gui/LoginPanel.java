package gui;

import account.AccountType;
import account.MayorAccountData;
import account.UserAccount;
import uml.Applicant;
import uml.Employee;
import uml.Manager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginPanel extends JPanel {

    private final MyFrame myFrame;
    private final List<UserAccount> accounts;
    private final MayorAccountData mayorData;

    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    public LoginPanel(MyFrame myFrame, List<UserAccount> accounts, MayorAccountData mayorData) {
        this.myFrame = myFrame;
        this.accounts = accounts;
        this.mayorData = mayorData;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        setBackground(new Color(246, 241, 248));

        JLabel titleLabel = new JLabel("Municipal Office Workflow Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(74, 68, 88));

        add(titleLabel, BorderLayout.NORTH);

        JPanel leftWrapper = new JPanel(new BorderLayout());
        leftWrapper.setOpaque(false);
        leftWrapper.setPreferredSize(new Dimension(260, 0));
        leftWrapper.setBorder(BorderFactory.createEmptyBorder(140, 0, 0, 0));
        leftWrapper.add(createHintPanel(), BorderLayout.NORTH);

        add(leftWrapper, BorderLayout.WEST);
        add(createLoginForm(), BorderLayout.CENTER);
    }

    private JPanel createHintPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setPreferredSize(new Dimension(240, 330));
        panel.setBackground(new Color(232, 244, 237));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(178, 213, 190)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel title = new JLabel("Test logins");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(55, 92, 68));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 13));
        textArea.setForeground(new Color(58, 72, 64));
        textArea.setText(
                "Applicant:\n" +
                        "login: applicant\n" +
                        "password: 123\n\n" +

                        "Employee:\n" +
                        "login: employee\n" +
                        "password: 123\n\n" +

                        "Manager:\n" +
                        "login: manager\n" +
                        "password: 123\n\n" +

                        "Mayor:\n" +
                        "login: mayor\n" +
                        "password: 123"
        );

        panel.add(title, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);

        return panel;
    }
    private JPanel createLoginForm() {
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setOpaque(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setPreferredSize(new Dimension(410, 280));
        formPanel.setBackground(new Color(255, 252, 245));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 204, 170)),
                BorderFactory.createEmptyBorder(28, 28, 28, 28)
        ));

        JLabel loginTitle = new JLabel("Login");
        loginTitle.setFont(new Font("Arial", Font.BOLD, 22));
        loginTitle.setForeground(new Color(109, 87, 57));

        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");

        loginLabel.setForeground(new Color(78, 72, 66));
        passwordLabel.setForeground(new Color(78, 72, 66));

        loginField.setPreferredSize(new Dimension(230, 32));
        passwordField.setPreferredSize(new Dimension(230, 32));

        loginField.setBackground(new Color(250, 247, 255));
        passwordField.setBackground(new Color(250, 247, 255));

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(230, 36));
        loginButton.setBackground(new Color(211, 226, 245));
        loginButton.setForeground(new Color(45, 63, 87));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(e -> login());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(loginTitle, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(loginLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(loginField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(loginButton, gbc);

        outerPanel.add(formPanel);


        return outerPanel;
    }
    private void login() {
        String login = loginField.getText();
        String password = new String(passwordField.getPassword());

        UserAccount account = findAccount(login, password);

        if (account == null) {
            JOptionPane.showMessageDialog(this, "Invalid login or password!");
            return;
        }

        if (account.getAccountType() == AccountType.APPLICANT) {
            myFrame.showPanel(new ApplicantPanel(myFrame, (Applicant) account.getOwner()));

        } else if (account.getAccountType() == AccountType.EMPLOYEE) {
            myFrame.showPanel(new EmployeePanel(myFrame, (Employee) account.getOwner()));

        } else if (account.getAccountType() == AccountType.MANAGER) {
            myFrame.showPanel(new ManagerPanel(myFrame, (Manager) account.getOwner(), mayorData));

        } else if (account.getAccountType() == AccountType.MAYOR) {
            myFrame.showPanel(new MayorPanel(myFrame, (MayorAccountData) account.getOwner()));
        }
    }

    private UserAccount findAccount(String login, String password) {
        for (UserAccount account : accounts) {
            if (account.getLogin().equals(login) && account.checkPassword(password)) {
                return account;
            }
        }

        return null;
    }
}