public class BinarySearch {

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();

        // Test Case 1: Target exists in the array
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 4");
        System.out.println("Actual:   " + solution.search(nums1, target1));
        System.out.println();

        // Test Case 2: Target does not exist in the array
        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        int target2 = 2;
        System.out.println("Test Case 2:");
        System.out.println("Expected: -1");
        System.out.println("Actual:   " + solution.search(nums2, target2));
        System.out.println();
        
        // Test Case 3: Single element array (Edge Case)
        int[] nums3 = {5};
        int target3 = 5;
        System.out.println("Test Case 3 (Single Element):");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.search(nums3, target3));
    }
}