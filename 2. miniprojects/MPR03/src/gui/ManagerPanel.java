package gui;

import account.MayorAccountData;
import uml.*;

import javax.swing.*;
import java.awt.*;

public class ManagerPanel extends JPanel {

    private final Manager manager;
    private final MayorAccountData mayorData;

    private final JComboBox<Assignment> assignmentComboBox = new JComboBox<>();
    private final JTextField caseField = new JTextField();
    private final JComboBox<CaseApplicant> applicantComboBox = new JComboBox<>();
    private final JTextField documentIdField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    private final JLabel statusLabel = new JLabel();

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardsPanel = new JPanel(cardLayout);

    private static final String STEP_ASSIGNMENT = "assignment";
    private static final String STEP_CASE = "case";
    private static final String STEP_APPLICANT = "applicant";
    private static final String STEP_DOCUMENT = "document";

    private static final String CORRECT_DOCUMENT_PASSWORD = "doc123";

    public ManagerPanel(MyFrame myFrame, Manager manager, MayorAccountData mayorData) {
        this.manager = manager;
        this.mayorData = mayorData;

        setupComboBoxRenderers();

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        setBackground(new Color(246, 241, 248));

        add(createHeaderPanel(myFrame), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createStatusPanel(), BorderLayout.SOUTH);

        loadAssignments();
        refreshStatus();
    }

    private JPanel createHeaderPanel(MyFrame myFrame) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel navigationPanel = GuiUtils.createNavigationPanel(myFrame);

        JLabel titleLabel = new JLabel("Manager Panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(new Color(74, 68, 88));

        JLabel imageLabel = GuiUtils.createImageLabel("src/gui/images/manager.jpg", 75, 75);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(imageLabel, BorderLayout.CENTER);

        panel.add(navigationPanel, BorderLayout.NORTH);
        panel.add(titlePanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setOpaque(false);

        mainPanel.add(createHintPanel(), BorderLayout.WEST);
        mainPanel.add(createWorkPanel(), BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createHintPanel() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setPreferredSize(new Dimension(300, 0));
        wrapper.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));

        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setPreferredSize(new Dimension(280, 310));
        panel.setBackground(new Color(232, 244, 237));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(178, 213, 190)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel title = new JLabel("Document access hint");
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(new Color(55, 92, 68));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 13));
        textArea.setForeground(new Color(58, 72, 64));
        textArea.setText(
                "Process:\n\n" +
                        "1. Choose assignment.\n" +
                        "2. System shows case.\n" +
                        "3. Choose applicant.\n" +
                        "4. Enter document ID.\n" +
                        "5. Enter password.\n\n" +
                        "Wrong document ID:\n" +
                        "document not found,\n" +
                        "no password attempt.\n\n" +
                        "Correct password:\n" +
                        CORRECT_DOCUMENT_PASSWORD
        );

        panel.add(title, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);

        wrapper.add(panel, BorderLayout.NORTH);
        return wrapper;
    }

    private JPanel createWorkPanel() {
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.setOpaque(false);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setPreferredSize(new Dimension(700, 390));
        panel.setBackground(new Color(255, 252, 245));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(224, 204, 170)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JLabel title = new JLabel("Document access workflow", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(109, 87, 57));

        cardsPanel.setOpaque(false);
        cardsPanel.add(createAssignmentStep(), STEP_ASSIGNMENT);
        cardsPanel.add(createCaseStep(), STEP_CASE);
        cardsPanel.add(createApplicantStep(), STEP_APPLICANT);
        cardsPanel.add(createDocumentStep(), STEP_DOCUMENT);

        panel.add(title, BorderLayout.NORTH);
        panel.add(cardsPanel, BorderLayout.CENTER);

        outerPanel.add(panel);
        return outerPanel;
    }

    private JPanel createAssignmentStep() {
        JPanel panel = createStepPanel();

        JLabel stepTitle = createStepTitle("Step 1. Choose assignment");
        JLabel label = createLabel("Assignment:");

        assignmentComboBox.setPreferredSize(new Dimension(390, 32));
        assignmentComboBox.setMaximumSize(new Dimension(390, 32));
        assignmentComboBox.setBackground(new Color(250, 247, 255));

        JButton nextButton = createBlueButton("Next: show case");

        nextButton.addActionListener(e -> {
            Assignment selectedAssignment = (Assignment) assignmentComboBox.getSelectedItem();

            if (selectedAssignment == null) {
                JOptionPane.showMessageDialog(this, "Select assignment first!");
                return;
            }

            loadCaseForSelectedAssignment();
            cardLayout.show(cardsPanel, STEP_CASE);
        });

        addTitleRow(panel, stepTitle, 0);
        addInputRow(panel, label, assignmentComboBox, 1);
        addOneButtonRow(panel, nextButton, 2);

        return panel;
    }

    private JPanel createCaseStep() {
        JPanel panel = createStepPanel();

        JLabel stepTitle = createStepTitle("Step 2. Case selected from assignment");
        JLabel label = createLabel("Selected case:");

        caseField.setPreferredSize(new Dimension(390, 32));
        caseField.setMaximumSize(new Dimension(390, 32));
        caseField.setBackground(new Color(250, 247, 255));
        caseField.setEditable(false);

        JButton backButton = createGreenButton("Back");
        JButton nextButton = createBlueButton("Next: choose applicant");

        backButton.addActionListener(e -> cardLayout.show(cardsPanel, STEP_ASSIGNMENT));

        nextButton.addActionListener(e -> {
            loadApplicantsForSelectedAssignment();

            if (applicantComboBox.getItemCount() == 0) {
                JOptionPane.showMessageDialog(this, "No applicants for selected case!");
                return;
            }

            cardLayout.show(cardsPanel, STEP_APPLICANT);
        });

        addTitleRow(panel, stepTitle, 0);
        addInputRow(panel, label, caseField, 1);
        addTwoButtonsRow(panel, backButton, nextButton, 2);

        return panel;
    }

    private JPanel createApplicantStep() {
        JPanel panel = createStepPanel();

        JLabel stepTitle = createStepTitle("Step 3. Choose applicant from selected case");
        JLabel label = createLabel("Applicant:");

        applicantComboBox.setPreferredSize(new Dimension(390, 32));
        applicantComboBox.setMaximumSize(new Dimension(390, 32));
        applicantComboBox.setBackground(new Color(250, 247, 255));

        JButton backButton = createGreenButton("Back");
        JButton nextButton = createBlueButton("Next: enter document ID");

        backButton.addActionListener(e -> cardLayout.show(cardsPanel, STEP_CASE));

        nextButton.addActionListener(e -> {
            CaseApplicant selectedApplicant = (CaseApplicant) applicantComboBox.getSelectedItem();

            if (selectedApplicant == null) {
                JOptionPane.showMessageDialog(this, "Select applicant first!");
                return;
            }

            if (selectedApplicant.getApplicant().getDocuments().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selected applicant has no documents!");
                return;
            }

            documentIdField.setText("");
            passwordField.setText("");
            cardLayout.show(cardsPanel, STEP_DOCUMENT);
        });

        addTitleRow(panel, stepTitle, 0);
        addInputRow(panel, label, applicantComboBox, 1);
        addTwoButtonsRow(panel, backButton, nextButton, 2);

        return panel;
    }

    private JPanel createDocumentStep() {
        JPanel panel = createStepPanel();

        JLabel stepTitle = createStepTitle("Step 4. Enter document ID and password");
        JLabel documentLabel = createLabel("Document ID:");
        JLabel passwordLabel = createLabel("Password:");

        documentIdField.setPreferredSize(new Dimension(390, 32));
        documentIdField.setMaximumSize(new Dimension(390, 32));
        documentIdField.setBackground(new Color(250, 247, 255));

        passwordField.setPreferredSize(new Dimension(390, 32));
        passwordField.setMaximumSize(new Dimension(390, 32));
        passwordField.setBackground(new Color(250, 247, 255));

        JButton backButton = createGreenButton("Back");
        JButton checkButton = createBlueButton("Check document");

        backButton.addActionListener(e -> cardLayout.show(cardsPanel, STEP_APPLICANT));
        checkButton.addActionListener(e -> checkDocument());

        addTitleRow(panel, stepTitle, 0);
        addInputRow(panel, documentLabel, documentIdField, 1);
        addInputRow(panel, passwordLabel, passwordField, 2);
        addTwoButtonsRow(panel, backButton, checkButton, 3);

        return panel;
    }

    private JPanel createStepPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 35, 15, 35));
        return panel;
    }

    private JLabel createStepTitle(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(109, 87, 57));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(78, 72, 66));
        return label;
    }

    private JButton createBlueButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 36));
        button.setBackground(new Color(211, 226, 245));
        button.setForeground(new Color(45, 63, 87));
        button.setFocusPainted(false);
        return button;
    }

    private JButton createGreenButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(200, 36));
        button.setBackground(new Color(232, 244, 237));
        button.setForeground(new Color(55, 92, 68));
        button.setFocusPainted(false);
        return button;
    }

    private void addTitleRow(JPanel panel, JLabel title, int row) {
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(25));
    }

    private void addInputRow(JPanel panel, JLabel label, JComponent component, int row) {
        JPanel rowPanel = new JPanel(new BorderLayout(12, 0));
        rowPanel.setOpaque(false);
        rowPanel.setMaximumSize(new Dimension(580, 40));

        label.setPreferredSize(new Dimension(150, 30));

        component.setPreferredSize(new Dimension(390, 32));
        component.setMaximumSize(new Dimension(390, 32));

        rowPanel.add(label, BorderLayout.WEST);
        rowPanel.add(component, BorderLayout.CENTER);

        panel.add(rowPanel);
        panel.add(Box.createVerticalStrut(16));
    }

    private void addOneButtonRow(JPanel panel, JButton button, int row) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(580, 45));

        button.setPreferredSize(new Dimension(230, 36));

        buttonPanel.add(button);

        panel.add(Box.createVerticalStrut(5));
        panel.add(buttonPanel);
    }

    private void addTwoButtonsRow(JPanel panel, JButton backButton, JButton nextButton, int row) {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(430, 42));
        buttonPanel.setPreferredSize(new Dimension(430, 42));

        backButton.setPreferredSize(new Dimension(200, 36));
        nextButton.setPreferredSize(new Dimension(200, 36));

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        panel.add(Box.createVerticalStrut(8));
        panel.add(buttonPanel);
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(232, 239, 250));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(185, 202, 224)),
                BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));

        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(new Color(45, 63, 87));

        panel.add(statusLabel, BorderLayout.CENTER);
        return panel;
    }

    private void setupComboBoxRenderers() {
        assignmentComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Assignment assignment) {
                    setText(
                            assignment.getCase().getTitle() +
                                    " | " +
                                    assignment.getEmployee().getFirstName() + " " +
                                    assignment.getEmployee().getSurname()
                    );
                }

                return this;
            }
        });

        applicantComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof CaseApplicant caseApplicant) {
                    Applicant applicant = caseApplicant.getApplicant();

                    setText(
                            applicant.getFirstName() + " " +
                                    applicant.getSurname() +
                                    " | submitted: " +
                                    caseApplicant.getDateOfSubmission()
                    );
                }

                return this;
            }
        });
    }

    private void loadAssignments() {
        assignmentComboBox.removeAllItems();

        for (Assignment assignment : manager.getManagedAssignments()) {
            assignmentComboBox.addItem(assignment);
        }
    }

    private void loadCaseForSelectedAssignment() {
        Assignment selectedAssignment = (Assignment) assignmentComboBox.getSelectedItem();

        if (selectedAssignment == null) {
            caseField.setText("");
            return;
        }

        Case selectedCase = selectedAssignment.getCase();
        caseField.setText(selectedCase.getTitle());
    }

    private void loadApplicantsForSelectedAssignment() {
        applicantComboBox.removeAllItems();
        documentIdField.setText("");

        Assignment selectedAssignment = (Assignment) assignmentComboBox.getSelectedItem();

        if (selectedAssignment == null) {
            return;
        }

        Case selectedCase = selectedAssignment.getCase();

        for (CaseApplicant caseApplicant : selectedCase.getCaseApplicants()) {
            applicantComboBox.addItem(caseApplicant);
        }
    }

    private void checkDocument() {
        if (manager.isSuspended()) {
            JOptionPane.showMessageDialog(this, "Manager is suspended!");
            refreshStatus();
            return;
        }

        Assignment selectedAssignment = (Assignment) assignmentComboBox.getSelectedItem();
        CaseApplicant selectedCaseApplicant = (CaseApplicant) applicantComboBox.getSelectedItem();

        if (selectedAssignment == null) {
            JOptionPane.showMessageDialog(this, "Select assignment first!");
            return;
        }

        if (selectedCaseApplicant == null) {
            JOptionPane.showMessageDialog(this, "Select applicant first!");
            return;
        }

        String documentIdText = documentIdField.getText();

        if (documentIdText.isBlank()) {
            JOptionPane.showMessageDialog(this, "Document ID is required!");
            return;
        }

        int documentId;

        try {
            documentId = Integer.parseInt(documentIdText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Document ID must be a number!");
            return;
        }

        Applicant applicant = selectedCaseApplicant.getApplicant();

        Document selectedDocument;

        try {
            selectedDocument = applicant.findDocumentById(documentId);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Document not found for selected applicant!");
            return;
        }

        String enteredPassword = new String(passwordField.getPassword());

        boolean accessGranted = manager.attemptDocumentAccess(
                enteredPassword,
                CORRECT_DOCUMENT_PASSWORD
        );

        if (accessGranted) {
            JOptionPane.showMessageDialog(
                    this,
                    "Access granted!\n\n" +
                            "Case: " + selectedAssignment.getCase().getTitle() + "\n" +
                            "Applicant: " + selectedCaseApplicant.getApplicant().getFirstName() + " " +
                            selectedCaseApplicant.getApplicant().getSurname() + "\n\n" +
                            "Document id: " + selectedDocument.getId() + "\n" +
                            "Type: " + selectedDocument.getType() + "\n" +
                            "Number and series: " + selectedDocument.getNumberAndSeries() + "\n" +
                            "Issue date: " + selectedDocument.getIssueDate() + "\n" +
                            "Country: " + selectedDocument.getCountry()
            );
        } else {
            int attemptsLeft = manager.getMaxPasswordAttempts() - manager.getPasswordAttempts();

            if (manager.getPasswordAttempts() >= manager.getMaxPasswordAttempts()) {
                mayorData.addManagerToReview(manager);

                JOptionPane.showMessageDialog(
                        this,
                        "Wrong password!\nManager sent to mayor review."
                );
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Wrong password!\nAttempts left: " + attemptsLeft
                );
            }
        }

        passwordField.setText("");
        refreshStatus();
    }

    private void refreshStatus() {
        statusLabel.setText(
                "Suspended: " + manager.isSuspended() +
                        "    |    Password attempts: " + manager.getPasswordAttempts() +
                        "/" + manager.getMaxPasswordAttempts()
        );
    }
}