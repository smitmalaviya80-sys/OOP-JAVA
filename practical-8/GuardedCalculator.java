import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            boolean success = false;

            try {
                System.out.println("Enter first number:");
                double a = Double.parseDouble(sc.nextLine());

                System.out.println("Enter operator (+, -, *, /):");
                String operator = sc.nextLine();

                System.out.println("Enter second number:");
                double b = Double.parseDouble(sc.nextLine());

                double result;

                switch (operator) {
                    case "+":
                        result = a + b;
                        break;

                    case "-":
                        result = a - b;
                        break;

                    case "*":
                        result = a * b;
                        break;

                    case "/":
                        if (b == 0) {
                            throw new DivideByZeroException(
                                    "Cannot divide by zero."
                            );
                        }
                        result = a / b;
                        break;

                    default:
                        System.out.println("Invalid operator.");
                        continue;
                }

                System.out.println("Result: " + result);
                success = true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number input.");

            } catch (DivideByZeroException e) {
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("Calculation attempt completed.");
                System.out.println();
            }

            if (success) {
                break;
            }
        }

        sc.close();
    }
}