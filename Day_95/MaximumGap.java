import java.util.Arrays;

public class MaximumGap {

    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int min = nums[0], max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) return 0;

        int n = nums.length;
        int gap = (int) Math.ceil((double) (max - min) / (n - 1));
        
        int bucketCount = (max - min) / gap + 1;
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        for (int num : nums) {
            int idx = (num - min) / gap;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
        }

        int maxGap = 0;
        int prevMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (bucketMin[i] == Integer.MAX_VALUE) continue;
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        MaximumGap solution = new MaximumGap();

        // Test Case 1: Standard scattered array
        int[] nums1 = {3, 6, 9, 1};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.maximumGap(nums1));
        System.out.println();

        // Test Case 2: Array with < 2 elements
        int[] nums2 = {10};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.maximumGap(nums2));
        System.out.println();

        // Test Case 3: Large gap in the middle
        int[] nums3 = {1, 10000000};
        System.out.println("Test Case 3:");
        System.out.println("Expected: 9999999");
        System.out.println("Actual:   " + solution.maximumGap(nums3));
    }
}