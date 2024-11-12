import com.example.extcalc.CalculatorLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorLogicTest {

    private CalculatorLogic calculator;

    @BeforeEach
    public void setUp() {
        calculator = new CalculatorLogic();
    }

    @Test
    public void testAdditionWithIntegers() {
        calculator.setOperands(10, 20, "+");
        assertEquals("30", calculator.calculate());
    }

    @Test
    public void testAdditionWithFloats() {
        calculator.setOperands(5.5, 4.5, "+");
        assertEquals("10", calculator.calculate());
    }

    @Test
    public void testSubtraction() {
        calculator.setOperands(20, 10, "-");
        assertEquals("10", calculator.calculate());
    }

    @Test
    public void testMultiplicationWithIntegers() {
        calculator.setOperands(7, 3, "*");
        assertEquals("21", calculator.calculate());
    }

    @Test
    public void testMultiplicationWithFloats() {
        calculator.setOperands(2.5, 4, "*");
        assertEquals("10", calculator.calculate());
    }

    @Test
    public void testDivisionWithIntegers() {
        calculator.setOperands(20, 4, "/");
        assertEquals("5", calculator.calculate());
    }

    @Test
    public void testDivisionWithFloats() {
        calculator.setOperands(7, 2, "/");
        assertEquals("3.5", calculator.calculate());
    }

    @Test
    public void testDivisionByZero() {
        calculator.setOperands(10, 0, "/");
        assertEquals("Error", calculator.calculate());
    }

    @Test
    public void testInvalidOperation() {
        calculator.setOperands(10, 5, "^"); // Некорректный оператор
        assertEquals("Invalid operation", calculator.calculate());
    }
}
