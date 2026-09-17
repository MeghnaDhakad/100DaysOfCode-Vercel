import java.util.Arrays;

public class FirstAndLastPosition {

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        
        // Find the first occurrence (lower bound)
        int first = findBound(nums, target, true);
        
        // If the first occurrence isn't found, the target isn't in the array
        if (first == -1) {
            return result;
        }
        
        // Find the last occurrence (upper bound)
        int last = findBound(nums, target, false);
        
        result[0] = first;
        result[1] = last;
        return result;
    }

    // Helper method to find either the first or last bound
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int bound = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                bound = mid; // Record the potential answer
                
                if (isFirst) {
                    right = mid - 1; // Keep searching the left half for an earlier match
                } else {
                    left = mid + 1;  // Keep searching the right half for a later match
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return bound;
    }

    // Main method to run and test the code locally
    public static void main(String[] args) {
        FirstAndLastPosition solution = new FirstAndLastPosition();

        // Test Case 1: Target exists multiple times
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        System.out.println("Test Case 1:");
        System.out.println("Expected: [3, 4]");
        System.out.println("Actual:   " + Arrays.toString(solution.searchRange(nums1, target1)));
        System.out.println();

        // Test Case 2: Target does not exist
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        System.out.println("Test Case 2:");
        System.out.println("Expected: [-1, -1]");
        System.out.println("Actual:   " + Arrays.toString(solution.searchRange(nums2, target2)));
        System.out.println();

        // Test Case 3: Empty array
        int[] nums3 = {};
        int target3 = 0;
        System.out.println("Test Case 3 (Empty Array):");
        System.out.println("Expected: [-1, -1]");
        System.out.println("Actual:   " + Arrays.toString(solution.searchRange(nums3, target3)));
    }
}