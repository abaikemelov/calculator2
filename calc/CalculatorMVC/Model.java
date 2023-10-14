public class Model {
    private final Viewer viewer;
    private String inputExpression;
    private final RPN rpn;
    private String textButton;
    private String result;

    public Model(Viewer viewer) {
        inputExpression = "";
        rpn = new RPN(this);
        this.viewer = viewer;
    }

    public void calculate(String newData) {
        if (newData.equals("Zero")) {
            appendDigit("0");
        } else if (newData.equals("One")) {
            appendDigit("1");
        } else if (newData.equals("Two")) {
            appendDigit("2");
        } else if (newData.equals("Three")) {
            appendDigit("3");
        } else if (newData.equals("Four")) {
            appendDigit("4");
        } else if (newData.equals("Five")) {
            appendDigit("5");
        } else if (newData.equals("Six")) {
            appendDigit("6");
        } else if (newData.equals("Seven")) {
            appendDigit("7");
        } else if (newData.equals("Eight")) {
            appendDigit("8");
        } else if (newData.equals("Nine")) {
            appendDigit("9");
        } else if (newData.equals("Dot")) {
            if (viewer.textField.getText().isEmpty() && viewer.textFieldHistory.getText().isEmpty()) {
                textButton = "";
            } else {
                inputExpression = inputExpression + ".";
                textButton = ".";
                viewer.appendToResultTextField(textButton);
                viewer.appendToHistoryTextField(textButton);
            }
        } else if (newData.equals("+")) {
            if (!viewer.textField.getText().isEmpty() || !viewer.textFieldHistory.getText().isEmpty()) {
                appendOperator("+");
            }
        } else if (newData.equals("-")) {
            if (!viewer.textField.getText().isEmpty() || !viewer.textFieldHistory.getText().isEmpty()) {
                appendOperator("-");
            }
        } else if (newData.equals("×")) {
            if (!viewer.textField.getText().isEmpty() || !viewer.textFieldHistory.getText().isEmpty()) {
                appendOperator("*");
            }
        } else if (newData.equals("÷")) {
            if (!viewer.textField.getText().isEmpty() || !viewer.textFieldHistory.getText().isEmpty()) {
                appendOperator("/");
            }
        } else if (newData.equals("%")) {
            if (!viewer.textField.getText().isEmpty() || !viewer.textFieldHistory.getText().isEmpty()) {
                appendOperator("%");
            }
        } else if (newData.equals("√")) {
            if (viewer.textField.getText().equals(result) && viewer.textFieldHistory.getText().isEmpty()) {
                inputExpression = newData + " ";
                viewer.textField.clear();
                viewer.appendToHistoryTextField(newData);
                viewer.appendToResultTextField(newData);
            } else if ((viewer.textField.getText().isEmpty() || viewer.textFieldHistory.getText().isEmpty())) {
                inputExpression = inputExpression + " " + newData + " ";
                viewer.appendToResultTextField("√");
                viewer.appendToHistoryTextField("√");
            }
        } else if (newData.equals("+-")) {
            handleNegate();
        } else if (newData.equals("1/x")) {
            if (viewer.textField.getText().isEmpty() && viewer.textFieldHistory.getText().isEmpty()) {
                textButton = "";
            } else {
                inputExpression = "1 " + "/" + " " + inputExpression;
                textButton = "^(-1)";
                viewer.textField.clear();
                viewer.appendToHistoryTextField(textButton);
            }
        } else if (newData.equals("del")) {
            String currentHistoryText = viewer.textFieldHistory.getText();
            String currentResultText = viewer.textField.getText();
            if ((!currentHistoryText.isEmpty() && !currentResultText.isEmpty())) {
                viewer.textFieldHistory.clear();
                viewer.textField.clear();
                viewer.appendToResultTextField(currentResultText.substring(0, currentResultText.length() - 1));
                viewer.appendToHistoryTextField(currentHistoryText.substring(0, currentHistoryText.length() - 1));
                inputExpression = inputExpression.substring(0, inputExpression.length() - 1);
            } else if (inputExpression.endsWith(" ")) {
                viewer.textFieldHistory.clear();
                viewer.appendToHistoryTextField(currentHistoryText.substring(0, currentHistoryText.length() - 1));
                inputExpression = inputExpression.substring(0, inputExpression.length() - 3);
            }
        } else if (newData.equals("C")) {
            viewer.textField.clear();
            viewer.textFieldHistory.clear();
            inputExpression = "";
        } else if (newData.equals("=")) {
            viewer.textField.clear();
            viewer.textFieldHistory.clear();
            double answer = rpn.infixToPostfix(inputExpression);
            result = String.valueOf(Math.round(answer));
            viewer.appendToResultTextField(result);
            inputExpression = result;
        }
    }

    private void appendDigit(String digit) {
        if (viewer.textField.getText().equals(result) && viewer.textFieldHistory.getText().isEmpty()) {
            inputExpression = digit;
            viewer.textField.clear();
            viewer.appendToHistoryTextField(digit);
            viewer.appendToResultTextField(digit);
        } else {
            inputExpression = inputExpression + digit;
            viewer.appendToResultTextField(digit);
            viewer.appendToHistoryTextField(digit);
        }
    }

    private void appendOperator(String operator) {
        String op;
        if (inputExpression.endsWith(" ")) {
            operator = "";
        }
        if (operator.equals("*")) {
            op = "×";
        } else if (operator.equals("/")) {
            op = "÷";
        } else {
            op = operator;
        }
        if (!viewer.textField.getText().isEmpty() && viewer.textFieldHistory.getText().isEmpty() && viewer.textField.getText().equals(result)) {
            viewer.textField.clear();
            viewer.appendToHistoryTextField(inputExpression);
            inputExpression = inputExpression + " " + operator + " ";
        } else {
            viewer.textField.clear();
            inputExpression = inputExpression + " " + operator + " ";
        }
        viewer.appendToHistoryTextField(op);
    }

    private void handleNegate() {
        if (viewer.textField.getText().isEmpty() && viewer.textFieldHistory.getText().isEmpty()) {
            inputExpression = inputExpression + "-";
            textButton = "-";
            viewer.textField.clear();
            viewer.appendToHistoryTextField(inputExpression);
            viewer.appendToResultTextField(inputExpression);
        } else if ((viewer.textField.getText().matches("\\d+") && !viewer.textField.getText().contains(" ")) || (viewer.textField.getText().contains(".") && !viewer.textField.getText().contains(" ") && !viewer.textField.getText().contains("-"))) {
            String prevOperand = inputExpression;
            inputExpression = "-" + prevOperand;
            viewer.textField.clear();
            viewer.textFieldHistory.clear();
            textButton = "-";
            viewer.appendToHistoryTextField(inputExpression);
            viewer.appendToResultTextField(inputExpression);
            textButton = "";
        } else {
            textButton = "";
        }
    }
}