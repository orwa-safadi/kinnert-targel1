//Student names: Orwa safadi, Jawlan dabous
//Student IDs: 324910561, 207112137



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression (e.g., one plus two times three):");
        String inputExpression = scanner.nextLine().trim();

        int result = calculate(inputExpression);
        System.out.println("The value of expression '" + inputExpression + "' is: " + result);
        scanner.close();
    }

    private static int calculate(String expression) {
        List<Object> array = new ArrayList<>();
        String[] spots = expression.split("\\s+");

        for (String spot : spots) {
            Integer number = WordToNumber(spot);
            if (number != null) {
                array.add(number);
            } else {
                String symbol = WordToSymbol(spot);
                if (symbol != null) {
                    array.add(symbol);
                }
            }
        }

        List<Object> nextArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Object token = array.get(i);
            if (token.equals("*") || token.equals("/")) {
                if (nextArray.isEmpty()) {
                    System.err.println("Error: Invalid expression format.");
                    return 0; // Or throw an exception
                }
                int num1 = (int) nextArray.remove(nextArray.size() - 1);
                String op = (String) token;
                if (i + 1 >= array.size()) {
                    System.err.println("Error: Invalid expression format.");
                    return 0; // Or throw an exception
                }
                Object nextToken = array.get(++i);
                if (!(nextToken instanceof Integer)) {
                    System.err.println("Error: Invalid expression format.");
                    return 0; // Or throw an exception
                }
                int num2 = (int) nextToken;
                nextArray.add(op.equals("*") ? num1 * num2 : num1 / num2);
            } else {
                nextArray.add(token);
            }
        }

        if (nextArray.isEmpty()) {
            return 0; // Or throw an exception
        }
        int result = (int) nextArray.get(0);
        for (int i = 1; i < nextArray.size(); i += 2) {
            if (i + 1 >= nextArray.size()) {
                System.err.println("Error: Invalid expression format.");
                return result; // Return the current result or throw an exception
            }
            String op = (String) nextArray.get(i);
            Object nextToken = nextArray.get(i + 1);
            if (!(nextToken instanceof Integer)) {
                System.err.println("Error: Invalid expression format.");
                return result; // Return the current result or throw an exception
            }
            int num = (int) nextToken;
            if (op.equals("+")) {
                result += num;
            } else if (op.equals("-")) {
                result -= num;
            }
        }
        return result;
    }

    private static Integer WordToNumber(String word) {
        switch (word.toLowerCase()) {
            case "zero":
                return 0;
            case "one":
                return 1;
            case "two":
                return 2;
            case "three":
                return 3;
            case "four":
                return 4;
            case "five":
                return 5;
            case "six":
                return 6;
            case "seven":
                return 7;
            case "eight":
                return 8;
            case "nine":
                return 9;
            case "ten":
                return 10;
            default:
                return null;
        }
    }

    private static String WordToSymbol(String word) {
        switch (word.toLowerCase()) {
            case "plus":
                return "+";
            case "minus":
                return "-";
            case "times":
                return "*";
            case "divided": // Changed to handle "divided" only
                return "/";
            case "by": // Added handling for "by" to be skipped if preceded by "divided"
                return null;
            default:
                return null;
        }
    }
}
