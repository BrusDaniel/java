import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ex1 {

    private JFrame frame;
    private JPanel panel;
    private JTextField display;

    private double firstOperand = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public ex1() {
        frame = new JFrame("Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(display);

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                if (isOperatorClicked) {
                    display.setText(command);
                    isOperatorClicked = false;
                } else {
                    display.setText(display.getText() + command);
                }
            } else if (command.equals(".")) {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
            } else if (command.matches("[/*\\-+]")) {
                if (!operator.isEmpty()) {
                    calculate();
                }
                firstOperand = Double.parseDouble(display.getText());
                operator = command;
                isOperatorClicked = true;
            } else if (command.equals("=")) {
                calculate();
                operator = "";
            }
        }

        private void calculate() {
            double secondOperand = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    firstOperand += secondOperand;
                    break;
                case "-":
                    firstOperand -= secondOperand;
                    break;
                case "*":
                    firstOperand *= secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        firstOperand /= secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(firstOperand));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ex1();
            }
        });
    }
}
