public class FindMinimumRotated {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumRotated solution = new FindMinimumRotated();

        // Test Case 1: Rotated 3 times
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.findMin(nums1));
        System.out.println();

        // Test Case 2: Rotated 4 times, minimum is in the middle
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.findMin(nums2));
        System.out.println();
        
        // Test Case 3: Fully sorted (0 rotations)
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Test Case 3 (Not Rotated):");
        System.out.println("Expected: 11");
        System.out.println("Actual:   " + solution.findMin(nums3));
    }
}