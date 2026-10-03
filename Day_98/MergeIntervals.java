import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Step 1: Sort by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        
        // Step 2: Set the first interval as active
        int[] currentInterval = intervals[0];
        merged.add(currentInterval);

        // Step 3: Iterate and merge
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) {
                // Overlap: update the end time
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap: add the new interval and make it active
                currentInterval = interval;
                merged.add(currentInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    // Helper method to print the 2D array exactly like LeetCode's output format
    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print("[" + intervals[i][0] + "," + intervals[i][1] + "]");
            if (i < intervals.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();

        // Test Case 1: Multiple overlapping intervals
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: [[1,6],[8,10],[15,18]]");
        System.out.print("Actual:   ");
        printIntervals(solution.merge(intervals1));
        System.out.println();

        // Test Case 2: Exact boundary overlap
        int[][] intervals2 = {{1, 4}, {4, 5}};
        System.out.println("Test Case 2:");
        System.out.println("Expected: [[1,5]]");
        System.out.print("Actual:   ");
        printIntervals(solution.merge(intervals2));
        System.out.println();
        
        // Test Case 3: One interval completely swallows another
        int[][] intervals3 = {{1, 4}, {2, 3}};
        System.out.println("Test Case 3:");
        System.out.println("Expected: [[1,4]]");
        System.out.print("Actual:   ");
        printIntervals(solution.merge(intervals3));
    }
}