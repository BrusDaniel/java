package gui;

import uml.Assignment;
import uml.Employee;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {

    public EmployeePanel(MyFrame myFrame, Employee employee) {
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Employee panel", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);

        StringBuilder text = new StringBuilder();

        text.append("Logged as employee:\n");
        text.append(employee).append("\n\n");

        text.append("Assignments:\n");

        for (Assignment assignment : employee.getAssignments()) {
            text.append("- ")
                    .append(assignment.getCase().getTitle())
                    .append(", status: ")
                    .append(assignment.getCaseStatus())
                    .append("\n");
        }

        infoArea.setText(text.toString());

        add(titleLabel, BorderLayout.NORTH);
        add(new JScrollPane(infoArea), BorderLayout.CENTER);
    }
}