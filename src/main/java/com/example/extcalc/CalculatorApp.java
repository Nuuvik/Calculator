package com.example.extcalc;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private Label display;
    private CalculatorLogic calculatorLogic; // экземпляр класса с логикой калькулятора
    private boolean startNewNumber = true; // Флаг для ввода нового числа

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Extended Calculator");

        calculatorLogic = new CalculatorLogic(); // инициализация логики

        // Display (экран вывода)
        display = new Label();
        display.setStyle("-fx-font-size: 24px; -fx-background-color: #eee; -fx-alignment: center-right;");
        display.setPrefHeight(50);
        display.setMinWidth(200);

        // Кнопки
        GridPane buttonGrid = createButtonGrid();

        // Основной layout
        VBox root = new VBox(10, display, buttonGrid);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        String[] buttonTexts = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Button button = new Button(buttonTexts[k++]);
                button.setPrefSize(50, 50);
                button.setStyle("-fx-font-size: 18px;");
                grid.add(button, j, i);

                button.setOnAction(e -> handleButtonClick(button.getText()));
            }
        }

        // Кнопка очистки
        Button clearButton = new Button("C");
        clearButton.setPrefSize(50, 50);
        clearButton.setStyle("-fx-font-size: 18px;");
        clearButton.setOnAction(e -> {
            display.setText("");
            calculatorLogic.clear();
            startNewNumber = true;
        });
        grid.add(clearButton, 3, 4);  // Расположим кнопку "C" в нижнем ряду справа

        return grid;
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "+":
            case "-":
            case "*":
            case "/":
                applyOperator(text);
                break;
            case "=":
                calculateResult();
                break;
            case ".":
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
                startNewNumber = false;
                break;
            default: // Если это число
                if (startNewNumber) {
                    display.setText("");  // Очистим дисплей для нового числа
                    startNewNumber = false;
                }
                display.setText(display.getText() + text);
                break;
        }
    }

    // Устанавливаем оператор и первое число
    private void applyOperator(String operator) {
        if (!display.getText().isEmpty()) {
            double currentValue = Double.parseDouble(display.getText());
            calculatorLogic.setOperands(currentValue, 0, operator); // устанавливаем первое число и оператор
            startNewNumber = true;
        }
    }

    // Вычисляем результат
    private void calculateResult() {
        if (!display.getText().isEmpty()) {
            double currentValue = Double.parseDouble(display.getText());
            calculatorLogic.setOperands(calculatorLogic.previousNumber, currentValue, calculatorLogic.currentOperator);
            String result = calculatorLogic.calculate(); // Получаем результат из логики
            display.setText(result); // Отображаем результат
            startNewNumber = true;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
