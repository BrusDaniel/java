package gui;

import uml.Applicant;
import uml.CaseApplicant;

import javax.swing.*;
import java.awt.*;

public class ApplicantPanel extends JPanel {

    public ApplicantPanel(MyFrame myFrame, Applicant applicant) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Applicant panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);

        StringBuilder text = new StringBuilder();

        text.append("Logged as applicant:\n");
        text.append(applicant).append("\n\n");

        text.append("Submitted cases:\n");

        for (CaseApplicant caseApplicant : applicant.getCaseApplicants()) {
            text.append("- ")
                    .append(caseApplicant.getCase().getTitle())
                    .append(", submitted: ")
                    .append(caseApplicant.getDateOfSubmission())
                    .append("\n");
        }

        infoArea.setText(text.toString());

        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(infoArea), BorderLayout.CENTER);
    }
}