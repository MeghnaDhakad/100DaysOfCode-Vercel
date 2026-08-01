package Day_35;
import java.util.*;

public class MyQueue {

    Stack<Integer> input;
    Stack<Integer> output;

    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {

        if (output.isEmpty()) {

            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MyQueue queue = new MyQueue();

        System.out.print("Enter number of operations: ");
        int n = sc.nextInt();

        System.out.println("1 x = Push x");
        System.out.println("2   = Pop");
        System.out.println("3   = Peek");
        System.out.println("4   = Check Empty");

        for (int i = 0; i < n; i++) {

            int operation = sc.nextInt();

            if (operation == 1) {
                int value = sc.nextInt();
                queue.push(value);
            }
            else if (operation == 2) {
                System.out.println(queue.pop());
            }
            else if (operation == 3) {
                System.out.println(queue.peek());
            }
            else if (operation == 4) {
                System.out.println(queue.empty());
            }
        }

        sc.close();
    }
}