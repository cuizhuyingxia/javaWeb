package cn.itcast.junit;

public class CalculatorTest {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int result1 = calculator.add(1, 2);
        System.out.println(result1);

        int result2 = calculator.sub(1, 2);
        System.out.println(result2);
    }
}
