import java.util.*;

public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {

        int[] freq = new int[26];

        // Count frequency of each task
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        int maxFreq = 0;

        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        int maxCount = 0;

        for (int f : freq) {
            if (f == maxFreq) {
                maxCount++;
            }
        }

        int intervals = (maxFreq - 1) * (n + 1) + maxCount;

        return Math.max(intervals, tasks.length);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of tasks: ");
        int m = sc.nextInt();

        char[] tasks = new char[m];

        System.out.println("Enter tasks (A-Z):");
        for (int i = 0; i < m; i++) {
            tasks[i] = sc.next().charAt(0);
        }

        System.out.print("Enter cooldown (n): ");
        int n = sc.nextInt();

        int result = leastInterval(tasks, n);

        System.out.println("Minimum intervals required: " + result);

        sc.close();
    }
}