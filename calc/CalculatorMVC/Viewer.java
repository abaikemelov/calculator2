import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Viewer extends Application {
    private final Controller controller;
    protected TextField textField;
    protected TextField textFieldHistory;


    public Viewer() {
        System.out.println("I am Viewer object.");
        this.controller = new Controller(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        textFieldHistory = new TextField();
        textFieldHistory.setText("");
        textFieldHistory.setLayoutX(50);
        textFieldHistory.setLayoutY(10);
        textFieldHistory.setPrefSize(350, 25);
        textFieldHistory.setStyle("-fx-text-fill: #000000;");
        textFieldHistory.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        textField = new TextField();
        textField.setText("");
        textField.setLayoutX(50);
        textField.setLayoutY(40);
        textField.setPrefSize(350, 50);
        textField.setStyle("-fx-text-fill: #000000;");
        textField.setFont(Font.font("Verdana", FontWeight.BOLD, 25));

        Button buttonClear = createButton("C", 50, 100);
        Button buttonDelete = createButton("del", 140, 100);
        Button buttonXSquare = createButton("x2", 230, 100);
        Button buttonDivide = createButton("÷", 320, 100);
        Button buttonPercent = createButton("%", 50, 190);
        Button buttonRoot = createButton("√", 140, 190);
        Button buttonMultiplyInverse = createButton("1/x", 230, 190);
        Button buttonMultiply = createButton("×", 320, 190);
        Button buttonSeven = createButton("7", 50, 280);
        Button buttonEight = createButton("8", 140, 280);
        Button buttonNine = createButton("9", 230, 280);
        Button buttonFour = createButton("4", 50, 370);
        Button buttonFive = createButton("5", 140, 370);
        Button buttonSix = createButton("6", 230, 370);
        Button buttonOne = createButton("1", 50, 460);
        Button buttonTwo = createButton("2", 140, 460);
        Button buttonThree = createButton("3", 230, 460);
        Button buttonPlus = createButton("+", 320, 370);
        Button buttonEquals = createButton("=", 320, 460);
        Button buttonMinus = createButton("-", 320, 280);
        Button buttonNumberSign = createButton("+-", 50, 550);
        Button buttonZero = createButton("0", 140, 550);
        Button buttonDot = createButton(".", 230, 550);

        Pane root = new Pane();
        root.getChildren().clear();
        root.getChildren().addAll(
                buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix,
                buttonSeven, buttonEight, buttonNine, buttonZero,
                buttonPlus, buttonEquals, buttonMinus, buttonNumberSign, buttonDot,
                buttonClear, buttonDelete, buttonXSquare, buttonDivide,
                buttonPercent, buttonRoot, buttonMultiplyInverse, buttonMultiply,
                textField, textFieldHistory
        );

        Scene scene = new Scene(root, 450, 650);

        primaryStage.setTitle("JavaFX Frontend MVC RPN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text, double x, double y) {
        Button button = new Button();
        button.setText(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefSize(80, 80);
        if (text.equals("=")) {
            button.setPrefSize(80, 170);
        }
        button.setFont(Font.font("Bernard MT Condensed", 25));
        button.setOnAction(controller);
        return button;
    }

    public void appendToResultTextField(String text) {
        String currentText = textField.getText();
        textField.setText(currentText + text);
    }

    public void appendToHistoryTextField(String text) {
        String currentText = textFieldHistory.getText();
        textFieldHistory.setText(currentText + text);
    }

    public static void startCalculator(String[] args) {
        launch(args);
    }

}
