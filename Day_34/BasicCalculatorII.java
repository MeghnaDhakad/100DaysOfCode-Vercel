package Day_34;
import java.util.*;

public class BasicCalculatorII {

    public static int calculate(String s) {

        Stack<Integer> stack = new Stack<>();

        int num = 0;
        char operator = '+';

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Build the number
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            // Operator found or end of string
            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {

                if (operator == '+') {
                    stack.push(num);
                }
                else if (operator == '-') {
                    stack.push(-num);
                }
                else if (operator == '*') {
                    stack.push(stack.pop() * num);
                }
                else if (operator == '/') {
                    stack.push(stack.pop() / num);
                }

                operator = ch;
                num = 0;
            }
        }

        int result = 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter expression: ");
        String s = sc.nextLine();

        int result = calculate(s);

        System.out.println("Result: " + result);

        sc.close();
    }
}