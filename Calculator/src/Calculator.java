//
// Name:        Le, Emily
// Project:     2
// Due:         10/26/15
// Course:      CS-245-01-f15
//
// Description:
//      A simple integer calculator that further helps me practice GUI concepts
//

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class Calculator implements ActionListener {

    // variables dealing with the GUI
    public JFrame frame;
    public JButton[] calcButtons = new JButton[16];
    public String[] stringButton = {"7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"};
    public JTextField displayText;

    // variables dealing with logic
    public boolean equalPressed;
    public String operator;
    public String answer;
    public String op1 = null;
    public String op2 = null;
    public String result;
    StringBuilder buildDisplayText = new StringBuilder();



    // -----------------------------------------------------------------------------------------------------------------
    //  Constructor:
    // -----------------------------------------------------------------------------------------------------------------
    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setLayout(new GridLayout(0, 1));
        frame.setSize(340, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // centers the frame in the middle of the screen
        frame.setLocationRelativeTo(null);

        //setIconImage(frame); //adds the program icon
        //this.setIconImage(new ImageIcon(getClass().getResource("/resource/icon.png")).getImage());

        // add image icon to frame
        URL icon = null;
        try {
            icon = new URL("http://www.cpp.edu/~tvnguyen7/courses/cs245f15/projs/Calculator.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(icon));

        displayText = new JTextField(9);
        displayText.setHorizontalAlignment(displayText.RIGHT);
        frame.add(displayText);
        createButtons();
        displayText.setText("0");

        // set frame visible
        frame.setVisible(true);

        // set default focus to '='
        frame.getRootPane().setDefaultButton(calcButtons[14]);
        calcButtons[14].requestFocus();
    }


    // -----------------------------------------------------------------------------------------------------------------
    //  createButtons:
    //
    //      Will create buttons that that user will see. These buttons will be added to the Jpanel, then to the Jframe.
    // -----------------------------------------------------------------------------------------------------------------
    public void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            calcButtons[i] = new JButton();
            calcButtons[i].setText(stringButton[i]);
            calcButtons[i].setActionCommand(stringButton[i]);
        }

        // add action listeners to all buttons and will add all of the buttons to the Jpanel
        for (int i = 0; i < calcButtons.length; i++) {
            calcButtons[i].addActionListener(this);
            buttonPanel.add(calcButtons[i]);
        }

        // adding Jpanel to Jframe
        frame.add(buttonPanel);
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  actionPerformed:
    //
    //          Will display text and set respective variables (op1, op2, operator)
    // -----------------------------------------------------------------------------------------------------------------
    public void actionPerformed(ActionEvent ae) {
        switch(ae.getActionCommand()){
            case "0":
                if(displayText.getText().equals("0")){
                    // do nothing
                }else{
                    buildDisplayText.append("0");
                    displayText.setText(buildDisplayText.toString());
                }
                break;
            case "1":
                buildDisplayText.append("1");
                displayText.setText(buildDisplayText.toString());
                break;
            case "2":
                buildDisplayText.append("2");
                displayText.setText(buildDisplayText.toString());
                break;
            case "3":
                buildDisplayText.append("3");
                displayText.setText(buildDisplayText.toString());
                break;
            case "4":
                buildDisplayText.append("4");
                displayText.setText(buildDisplayText.toString());
                break;
            case "5":
                buildDisplayText.append("5");
                displayText.setText(buildDisplayText.toString());
                break;
            case "6":
                buildDisplayText.append("6");
                displayText.setText(buildDisplayText.toString());
                break;
            case "7":
                buildDisplayText.append("7");
                displayText.setText(buildDisplayText.toString());
                break;
            case "8":
                buildDisplayText.append("8");
                displayText.setText(buildDisplayText.toString());
                break;
            case "9":
                buildDisplayText.append("9");
                displayText.setText(buildDisplayText.toString());
                break;
            case "/":
                if (op1 != null && op2 != null){
                    doMath(op1, op2, operator);
                }else{
                    operator = "/";
                    executeOperation(operator);
                }
                break;
            case "*":
                if (op1 != null && op2 != null){
                    doMath(op1, op2, operator);
                }else{
                    operator = "*";
                    executeOperation(operator);
                }
                break;
            case "-":
                if (op1 != null && op2 != null){
                    doMath(op1, op2, operator);
                }else{
                    operator = "-";
                    executeOperation(operator);
                }
                break;
            case"+":
                if (op1 != null && op2 != null){
                    doMath(op1, op2, operator);
                }else{
                    operator = "+";
                    executeOperation(operator);
                }
                break;
            case "C":
                enableButtons();
                resetVariables();
                displayText.setText("0");
                break;
            case "=":
                if (op2 == null){
                    op2 = buildDisplayText.toString();
                }
                if(op2.equals("0")){
                    displayText.setText("Cannot Divide By 0.");
                    disableButtons();
                    resetVariables();
                }else {
                    result = doMath(op1, op2, operator);
                    displayText.setText(result);
                    op1 = result;
                    op2 = null;
                    operator = null;
                    buildDisplayText.setLength(0);
                    //resetVariables();
                    equalPressed = true;
                }
                break;
        }

        if (ae.getActionCommand().equals("C") && (ae.getModifiers() & ActionEvent.CTRL_MASK) !=0)  {
            displayText.setText("(c) 2015 Emily Le");
            disableButtons();
            resetVariables();
        }
        if (buildDisplayText.toString().length() > 10){
            displayText.setText("Overflow!");
            disableButtons();
            resetVariables();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  executeOperation:
    //
    //              Will set operands depending on certain cases
    // -----------------------------------------------------------------------------------------------------------------
    public void executeOperation(String operator){
        if (op1 == null){
            op1 = buildDisplayText.toString();
            buildDisplayText.setLength(0);
        }
        else if(equalPressed){
            equalPressed = false;
            if (!buildDisplayText.toString().isEmpty()){
                op1 = buildDisplayText.toString();
                buildDisplayText.setLength(0);
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  doMath:
    //
    //              Will execute primary mathematical operations (/ * - +)
    // -----------------------------------------------------------------------------------------------------------------
    public String doMath(String operand1, String operand2, String operator){
        switch(operator){
            case "/":
                answer = Long.toString(Long.parseLong(operand1) / Long.parseLong(operand2));
                break;
            case "*":
                answer = Long.toString(Long.parseLong(operand1) * Long.parseLong(operand2));
                break;
            case "-":
                answer = Long.toString(Long.parseLong(operand1) - Long.parseLong(operand2));
                break;
            case "+":
                answer = Long.toString(Long.parseLong(operand1) + Long.parseLong(operand2));
                break;
        }
        return answer;
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  disableButtons:
    //
    //          Will disable all buttons except for 'C'
    // -----------------------------------------------------------------------------------------------------------------
    public void disableButtons(){
        for (int i = 0; i < 13; i++){
            calcButtons[i].setEnabled(false);
        }
        for (int i = 14; i < calcButtons.length; i++){
            calcButtons[i].setEnabled(false);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  enableButtons:
    //
    //          Will disable all buttons except for 'C'
    // -----------------------------------------------------------------------------------------------------------------
    public void enableButtons(){
        for (int i = 0; i < 13; i++){
            calcButtons[i].setEnabled(true);
        }
        for (int i = 14; i < calcButtons.length; i++){
            calcButtons[i].setEnabled(true);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  resetVariables:
    //
    //          Will reset all variables
    // -----------------------------------------------------------------------------------------------------------------
    public void resetVariables(){
        op1 = null;
        op2 = null;
        operator = null;
        buildDisplayText.setLength(0);
    }

    // -----------------------------------------------------------------------------------------------------------------
    //  main:
    //
    //          Main method that will start the GUI
    // -----------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
//        // will allow this application's look and feel to be consistent across all platforms (I am using OSX)
//        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            JFrame.setDefaultLookAndFeelDecorated(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}
