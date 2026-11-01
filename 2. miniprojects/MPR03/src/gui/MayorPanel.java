package gui;

import account.MayorAccountData;
import uml.Manager;

import javax.swing.*;
import java.awt.*;

public class MayorPanel extends JPanel {

    private final MayorAccountData mayorData;

    private final DefaultListModel<Manager> reviewModel = new DefaultListModel<>();
    private final DefaultListModel<Manager> suspendedModel = new DefaultListModel<>();
    private final DefaultListModel<Manager> activeModel = new DefaultListModel<>();

    private final JList<Manager> reviewList = new JList<>(reviewModel);
    private final JList<Manager> suspendedList = new JList<>(suspendedModel);
    private final JList<Manager> activeList = new JList<>(activeModel);

    public MayorPanel(MyFrame myFrame, MayorAccountData mayorData) {
        this.mayorData = mayorData;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setBackground(new Color(246, 241, 248));

        add(createHeaderPanel(myFrame), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        refreshLists();
    }

    private JPanel createHeaderPanel(MyFrame myFrame) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel navigationPanel = GuiUtils.createNavigationPanel(myFrame);

        JLabel titleLabel = new JLabel("Mayor Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(74, 68, 88));

        JLabel imageLabel = GuiUtils.createImageLabel("src/gui/images/mayor_cat.jpg", 85, 85);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(imageLabel, BorderLayout.CENTER);

        panel.add(navigationPanel, BorderLayout.NORTH);
        panel.add(titlePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setOpaque(false);

        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 15));
        panel.setPreferredSize(new Dimension(820, 310));
        panel.setBackground(new Color(255, 252, 245));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 204, 170)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        panel.add(createListPanel("Waiting for decision", reviewList));
        panel.add(createListPanel("Suspended managers", suspendedList));
        panel.add(createListPanel("Active managers", activeList));

        outerPanel.add(panel);
        return outerPanel;
    }

    private JPanel createListPanel(String title, JList<Manager> list) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);

        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(109, 87, 57));

        list.setBackground(new Color(250, 247, 255));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panel.setBackground(new Color(246, 241, 248));

        JButton suspendButton = new JButton("Suspend selected");
        JButton unfreezeButton = new JButton("Unfreeze selected");
        JButton ignoreButton = new JButton("Ignore review");
        JButton refreshButton = new JButton("Refresh");

        suspendButton.setBackground(new Color(244, 218, 218));
        unfreezeButton.setBackground(new Color(232, 244, 237));
        ignoreButton.setBackground(new Color(255, 239, 204));
        refreshButton.setBackground(new Color(211, 226, 245));

        suspendButton.setFocusPainted(false);
        unfreezeButton.setFocusPainted(false);
        ignoreButton.setFocusPainted(false);
        refreshButton.setFocusPainted(false);

        suspendButton.addActionListener(e -> suspendSelectedManager());
        unfreezeButton.addActionListener(e -> unfreezeSelectedManager());
        ignoreButton.addActionListener(e -> ignoreSelectedManager());
        refreshButton.addActionListener(e -> refreshLists());

        panel.add(suspendButton);
        panel.add(unfreezeButton);
        panel.add(ignoreButton);
        panel.add(refreshButton);

        return panel;
    }

    private void refreshLists() {
        reviewModel.clear();
        suspendedModel.clear();
        activeModel.clear();

        for (Manager manager : mayorData.getManagersToReview()) {
            reviewModel.addElement(manager);
        }

        for (Manager manager : mayorData.getSuspendedManagers()) {
            suspendedModel.addElement(manager);
        }

        for (Manager manager : mayorData.getActiveManagers()) {
            activeModel.addElement(manager);
        }
    }

    private void suspendSelectedManager() {
        Manager manager = reviewList.getSelectedValue();

        if (manager == null) {
            JOptionPane.showMessageDialog(this, "Select manager from review list first!");
            return;
        }

        mayorData.suspendManager(manager);
        JOptionPane.showMessageDialog(this, "Manager suspended!");
        refreshLists();
    }

    private void unfreezeSelectedManager() {
        Manager manager = suspendedList.getSelectedValue();

        if (manager == null) {
            JOptionPane.showMessageDialog(this, "Select suspended manager first!");
            return;
        }

        mayorData.unfreezeManager(manager);
        JOptionPane.showMessageDialog(this, "Manager unfrozen!");
        refreshLists();
    }

    private void ignoreSelectedManager() {
        Manager manager = reviewList.getSelectedValue();

        if (manager == null) {
            JOptionPane.showMessageDialog(this, "Select manager from review list first!");
            return;
        }

        mayorData.ignoreManager(manager);
        JOptionPane.showMessageDialog(this, "Review ignored.");
        refreshLists();
    }
}