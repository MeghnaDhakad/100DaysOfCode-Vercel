public class SearchRotated {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SearchRotated solution = new SearchRotated();

        // Test Case 1: Target exists in the right (rotated) half
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 4");
        System.out.println("Actual:   " + solution.search(nums1, target1));
        System.out.println();

        // Test Case 2: Target does not exist
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Test Case 2:");
        System.out.println("Expected: -1");
        System.out.println("Actual:   " + solution.search(nums2, target2));
        System.out.println();

        // Test Case 3: Empty/Small array case
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Test Case 3 (Single Element):");
        System.out.println("Expected: -1");
        System.out.println("Actual:   " + solution.search(nums3, target3));
    }
}