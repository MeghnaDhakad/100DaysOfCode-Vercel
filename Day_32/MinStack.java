package Day_32;
import java.util.*;

public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }

        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }

        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MinStack obj = new MinStack();

        System.out.print("Enter number of operations: ");
        int n = sc.nextInt();

        System.out.println("1 = Push");
        System.out.println("2 = Pop");
        System.out.println("3 = Top");
        System.out.println("4 = Get Minimum");

        for (int i = 0; i < n; i++) {

            int operation = sc.nextInt();

            if (operation == 1) {
                int value = sc.nextInt();
                obj.push(value);
            }
            else if (operation == 2) {
                obj.pop();
            }
            else if (operation == 3) {
                System.out.println(obj.top());
            }
            else if (operation == 4) {
                System.out.println(obj.getMin());
            }
        }

        sc.close();
    }
}