import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class RPN {

    private Model model;

    public RPN(Model model) {
        this.model = model;
    }

    public double infixToPostfix(String infixExpr) {
        HashMap<String, Integer> prec = new HashMap<>();

        prec.put("%", 3);
        prec.put("√", 3);
        prec.put("*", 3);
        prec.put("/", 3);
        prec.put("+", 2);
        prec.put("-", 2);

        Stack<String> opStack = new Stack<>();
        List<String> postfixList = new ArrayList<>();
        String[] tokenList = infixExpr.split(" ");

        for (String token : tokenList) {
            if (isNumeric(token)) {
                postfixList.add(token);
            } else if (isOperator(token)) {
                while (!opStack.isEmpty() && prec.getOrDefault(opStack.peek(), 0) >= prec.get(token)) {
                    postfixList.add(opStack.pop());
                }
                opStack.push(token);
            }
        }
        while (!opStack.isEmpty()) {
            postfixList.add(opStack.pop());
        }
        String answer = String.join(" ", postfixList);
        return rpnCalculate(answer);
    }
    private double rpnCalculate(String expression) {
        System.out.println("Calculating RPN for expression: " + expression);
        Stack<Double> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");
        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (token.equals("√")) {
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Invalid expression: " + expression);
                    }
                    double num = stack.pop();
                    double result = squareRoot(num, token);
                    stack.push(result);
                } else {
                    if (stack.isEmpty()) {
                        throw new IllegalArgumentException("Invalid expression: " + expression);
                    }
                    double num2 = stack.pop();
                    double num1 = stack.pop();
                    double result = performOperation(num1, num2, token);
                    stack.push(result);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }
        return stack.pop();
    }



    private static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-")
                || s.equals("*")
                || s.equals("/")
                || s.equals("%")
                || s.equals("√");
    }

    public static double performOperation(double num1, double num2, String operator) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        } else if (operator.equals("/")) {
            return num1 / num2;
        } else if (operator.equals("%")) {
            return num1 % num2;
        } else if (operator.equals("√")) {
            return Math.sqrt(num1);
        } else {
            throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private static double squareRoot(double num, String operator) {
        if (operator.equals("√"))
            return Math.sqrt(num);
        else
            throw new IllegalArgumentException("Invalid operator: " + operator);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
