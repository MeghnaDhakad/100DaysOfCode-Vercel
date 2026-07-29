package Day_33;
import java.util.*;

public class EvaluateRPN {

    public static int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (token.equals("+")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a + b);
            }
            else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            }
            else if (token.equals("*")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a * b);
            }
            else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of tokens: ");
        int n = sc.nextInt();

        String[] tokens = new String[n];

        System.out.println("Enter tokens:");

        for (int i = 0; i < n; i++) {
            tokens[i] = sc.next();
        }

        int result = evalRPN(tokens);

        System.out.println("Result: " + result);

        sc.close();
    }
}