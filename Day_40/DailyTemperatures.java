import java.util.*;

public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temperatures) {

        int n = temperatures.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() &&
                   temperatures[i] > temperatures[stack.peek()]) {

                int index = stack.pop();
                answer[index] = i - index;
            }

            stack.push(i);
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of days: ");
        int n = sc.nextInt();

        int[] temperatures = new int[n];

        System.out.println("Enter temperatures:");
        for (int i = 0; i < n; i++) {
            temperatures[i] = sc.nextInt();
        }

        int[] result = dailyTemperatures(temperatures);

        System.out.println("Days until warmer temperature:");

        for (int day : result) {
            System.out.print(day + " ");
        }

        sc.close();
    }
}