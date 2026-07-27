package Day_31;
import java.util.*;

public class ValidParentheses {

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening bracket
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }

            // Closing bracket
            else {

                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if (ch == ')' && top != '(') {
                    return false;
                }

                if (ch == '}' && top != '{') {
                    return false;
                }

                if (ch == ']' && top != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter parentheses string: ");
        String s = sc.nextLine();

        if (isValid(s)) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }

        sc.close();
    }
}