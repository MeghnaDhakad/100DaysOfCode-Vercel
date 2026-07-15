package Day_19;
import java.util.*;

public class MaxSum_Circular_SubArray{

    public static int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currentMax = nums[0];
        int maxSum = nums[0];

        int currentMin = nums[0];
        int minSum = nums[0];

        for (int i = 0; i < nums.length; i++) {

            totalSum += nums[i];

            if (i > 0) {

                // Find maximum subarray sum
                currentMax = Math.max(nums[i], currentMax + nums[i]);
                maxSum = Math.max(maxSum, currentMax);

                // Find minimum subarray sum
                currentMin = Math.min(nums[i], currentMin + nums[i]);
                minSum = Math.min(minSum, currentMin);
            }
        }

        // If all elements are negative
        if (maxSum < 0) {
            return maxSum;
        }

        int circularSum = totalSum - minSum;

        return Math.max(maxSum, circularSum);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int result = maxSubarraySumCircular(nums);

        System.out.println("Maximum circular subarray sum: " + result);

        sc.close();
    }
}
