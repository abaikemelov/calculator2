import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Controller implements EventHandler<ActionEvent> {
    private final Model model;

    public Controller(Viewer viewer) {
        System.out.println("I am Controller object.");
        model = new Model(viewer);
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button)event.getTarget();
        String textButton = button.getText();
        String command = "";
        if(textButton.equals("1")) {
            command = "One";
        } else if(textButton.equals("2")) {
            command = "Two";
        } else if(textButton.equals("3")) {
            command = "Three";
        } else if(textButton.equals("4")) {
            command = "Four";
        } else if(textButton.equals("5")) {
            command = "Five";
        } else if(textButton.equals("6")) {
            command = "Six";
        } else if(textButton.equals("7")) {
            command = "Seven";
        } else if(textButton.equals("8")) {
            command = "Eight";
        } else if(textButton.equals("9")) {
            command = "Nine";
        } else if(textButton.equals("0")) {
            command = "Zero";
        } else if(textButton.equals(".")) {
            command = "Dot";
        } else if(textButton.equals("+-")) {
            command = "+-";
        } else if(textButton.equals("+")) {
            command = "+";
        } else if(textButton.equals("-")) {
            command = "-";
        } else if(textButton.equals("×")) {
            command = "×";
        } else if(textButton.equals("÷")) {
            command = "÷";
        } else if(textButton.equals("√")) {
            command = "√";
        } else if(textButton.equals("%")) {
            command = "%";
        } else if(textButton.equals("x" + "\u00B2")) {
            command = "x" + "\u00B2";
        } else if(textButton.equals("1/x")) {
            command = "1/x";
        } else if(textButton.equals("=")) {
            command = "=";
        } else if(textButton.equals("C")) {
            command = "C";
        } else if(textButton.equals("del")) {
            command = "del";
        } else {
            return ;
        }
        model.calculate(command);
    }

}