public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }

    public static void main(String[] args) {
        FindPeakElement solution = new FindPeakElement();

        // Test Case 1: Single peak in the middle
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 2 (Index of value 3)");
        System.out.println("Actual:   " + solution.findPeakElement(nums1));
        System.out.println();

        // Test Case 2: Multiple peaks, returning any valid peak index is fine
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Test Case 2:");
        // The peaks are at index 1 (value 2) and index 5 (value 6).
        // Based on our algorithm's path, it will find index 5.
        System.out.println("Expected: 1 or 5");
        System.out.println("Actual:   " + solution.findPeakElement(nums2));
        System.out.println();
        
        // Test Case 3: Strictly increasing (Peak is at the very end)
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 3 (Strictly Increasing):");
        System.out.println("Expected: 4 (Index of value 5)");
        System.out.println("Actual:   " + solution.findPeakElement(nums3));
    }
}