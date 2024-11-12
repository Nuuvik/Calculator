package com.example.extcalc;

public class CalculatorLogic {

    double previousNumber;
    private double currentNumber;
    String currentOperator;

    public CalculatorLogic() {
        clear();
    }

    // Устанавливаем числа и операцию
    public void setOperands(double previous, double current, String operator) {
        this.previousNumber = previous;
        this.currentNumber = current;
        this.currentOperator = operator;
    }

    // Метод для вычисления результата
    public String calculate() {
        double result;
        switch (currentOperator) {
            case "+":
                result = previousNumber + currentNumber;
                break;
            case "-":
                result = previousNumber - currentNumber;
                break;
            case "*":
                result = previousNumber * currentNumber;
                break;
            case "/":
                if (currentNumber == 0) return "Error"; // деление на ноль
                result = previousNumber / currentNumber;
                break;
            default:
                return "Invalid operation";
        }

        // Возвращаем результат в нужном формате
        if (result == Math.floor(result)) {
            return String.valueOf((long) result);  // Целое число
        } else {
            return String.valueOf(result);  // Дробное число
        }
    }

    public void clear() {
        previousNumber = 0;
        currentNumber = 0;
        currentOperator = "";
    }
}
