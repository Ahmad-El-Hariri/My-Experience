import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField TextField; // main text output field
    JTextField historyField; // second field for see history operations
    JButton[] numberButtons = new JButton[10]; // 0-9
    JButton[] functionButtons = new JButton[9]; // all operations buttons (+,-,*,/,.,=,c,(-))
    JButton addButton, subButton, mulButton, divButton; // buttons
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel; // main panel for buttons

    Font myFont = new Font("Times New Roman", Font.BOLD, 30); // global font

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {

        // application window
        frame = new JFrame("Calculator"); // title name
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        // output Text window
        TextField = new JTextField();
        TextField.setBounds(50, 45, 300, 50); // window location
        TextField.setFont(myFont);
        TextField.setEditable(false);
        TextField.setHorizontalAlignment(SwingConstants.RIGHT);

        // operations history window
        historyField = new JTextField();
        historyField.setBounds(50, 15, 300, 25);
        historyField.setEditable(false);
        historyField.setBorder(null); // hide frame border
        historyField.setHorizontalAlignment(SwingConstants.RIGHT); // writing switch on right

        // setfont and style of the text
        historyField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        historyField.setForeground(Color.DARK_GRAY); // text color

        // all operation buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("X");
        clrButton = new JButton("C");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {

            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        // adding a buttons and setPosition
        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // background for buttons
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        // panel.setBackground(Color.GRAY);

        // add all buttons on the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // add buttons and textFields on the frame
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(TextField);
        frame.add(historyField);
        frame.setVisible(true);

        frame.repaint(); // bag fix with buttons loadings

    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    public static double calculate(String c) throws NumberFormatException { // "4+9*2*3-10/5/2+20*0.5"

        for (int i = 0; i < c.length(); i++) {
            String letter = c.charAt(i) + "";
            if (letter.equals("x")) {

                int startIndex = i - 1;
                while (startIndex >= 0
                        && (Character.isDigit(c.charAt(startIndex)) || c.charAt(startIndex) == '.')) {
                    startIndex--;
                }
                startIndex++;

                int endIndex = i + 1;
                while (endIndex < c.length() && (Character.isDigit(c.charAt(endIndex)) || c.charAt(endIndex) == '.'
                        || c.charAt(i + 1) == '-')) {
                    endIndex++;
                }

                double num1 = Double.parseDouble(c.substring(startIndex, i));
                double num2 = Double.parseDouble(c.substring(i + 1, endIndex));

                c = c.substring(0, startIndex) + (num1 * num2) + c.substring(endIndex, c.length());// 26.0

                i = startIndex;

            }
        }

        if (!c.contains("x")) {

            for (int i = 0; i < c.length(); i++) {
                String letter = c.charAt(i) + "";
                if (letter.equals("/")) {

                    int startIndex = i - 1;
                    while (startIndex >= 0
                            && (Character.isDigit(c.charAt(startIndex)) || c.charAt(startIndex) == '.')) {
                        startIndex--;
                    }
                    startIndex++;

                    int endIndex = i + 1;
                    while (endIndex < c.length() && (Character.isDigit(c.charAt(endIndex))
                            || c.charAt(endIndex) == '.' || c.charAt(i + 1) == '-')) {
                        endIndex++;
                    }

                    double num1 = Double.parseDouble(c.substring(startIndex, i));
                    double num2 = Double.parseDouble(c.substring(i + 1, endIndex));

                    c = c.substring(0, startIndex) + (num1 / num2) + c.substring(endIndex, c.length());

                    i = startIndex;
                }
            }
        }

        if (!c.contains("x") && !c.contains("/")) {

            for (int i = 0; i < c.length(); i++) {
                String letter = c.charAt(i) + "";
                if (letter.equals("-")) {

                    int startIndex = i - 1;
                    while (startIndex >= 0
                            && (Character.isDigit(c.charAt(startIndex)) || c.charAt(startIndex) == '.')) {
                        startIndex--;
                    }
                    startIndex++;

                    int endIndex = i + 1;
                    while (endIndex < c.length()
                            && (Character.isDigit(c.charAt(endIndex)) || c.charAt(endIndex) == '.')) {
                        endIndex++;
                    }

                    double num1 = Double.parseDouble(c.substring(startIndex, i));
                    double num2 = Double.parseDouble(c.substring(i + 1, endIndex));

                    c = c.substring(0, startIndex) + (num1 - num2) + c.substring(endIndex, c.length());

                    i = startIndex;

                }
            }
        }

        if (!c.contains("x") && !c.contains("/") && !c.contains("-")) {

            for (int i = 0; i < c.length(); i++) {
                String letter = c.charAt(i) + "";
                if (letter.equals("+")) {

                    int startIndex = i - 1;
                    while (startIndex >= 0
                            && (Character.isDigit(c.charAt(startIndex)) || c.charAt(startIndex) == '.')) {
                        startIndex--;
                    }
                    startIndex++;

                    int endIndex = i + 1;
                    while (endIndex < c.length()
                            && (Character.isDigit(c.charAt(endIndex)) || c.charAt(endIndex) == '.')) {
                        endIndex++;
                    }

                    double num1 = Double.parseDouble(c.substring(startIndex, i));
                    double num2 = Double.parseDouble(c.substring(i + 1, endIndex));

                    c = c.substring(0, startIndex) + (num1 + num2) + c.substring(endIndex, c.length());

                    i = startIndex;
                }
            }
        }

        return Double.parseDouble(c);
        // return c;
    }

    String s = "";

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == numberButtons[0]) {
            s += "0";

        } else if (e.getSource() == numberButtons[1]) {
            s += "1";

        } else if (e.getSource() == numberButtons[2]) {
            s += "2";

        } else if (e.getSource() == numberButtons[3]) {
            s += "3";

        } else if (e.getSource() == numberButtons[4]) {
            s += "4";

        } else if (e.getSource() == numberButtons[5]) {
            s += "5";

        } else if (e.getSource() == numberButtons[6]) {
            s += "6";

        } else if (e.getSource() == numberButtons[7]) {
            s += "7";

        } else if (e.getSource() == numberButtons[8]) {
            s += "8";

        } else if (e.getSource() == numberButtons[9]) {
            s += "9";

        } else if (e.getSource() == functionButtons[0]) {
            s += "+";
        } else if (e.getSource() == functionButtons[1]) {
            s += "-";
        } else if (e.getSource() == functionButtons[2]) {
            s += "x";
        } else if (e.getSource() == functionButtons[3]) {
            s += "/";
        } else if (e.getSource() == functionButtons[4]) {
            s += ".";

        } else if (e.getSource() == functionButtons[6]) {
            s = s.substring(0, s.length() - 1);

        } else if (e.getSource() == functionButtons[7]) {
            s = null;
            s = "";
        }

        // } else if (e.getSource() == functionButtons[8]) {
        // s += "-";
        // }

        else if (e.getSource() == functionButtons[5]) {
            s += "=";
            s += calculate(s.substring(0, s.length() - 1));
            // s = calculate(s.substring(0, s.length() - 1));
        }

        TextField.setText(s);

        // TODO: Implement the logic for each button press.

        // Example:
        // if (e.getSource() == numberButtons[1]) {
        // // Handle the press of the number 1 button.
        // // Update the TextField or perform any necessary operations.
        // }

        // ... (implement the logic for other buttons)

    }
}
