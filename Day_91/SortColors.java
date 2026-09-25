import java.util.Arrays;

public class SortColors {

    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;
        
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--; 
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        SortColors solution = new SortColors();

        // Test Case 1: Standard mix of 0s, 1s, and 2s
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        System.out.println("Test Case 1:");
        solution.sortColors(nums1);
        System.out.println("Expected: [0, 0, 1, 1, 2, 2]");
        System.out.println("Actual:   " + Arrays.toString(nums1));
        System.out.println();

        // Test Case 2: Array already mostly sorted
        int[] nums2 = {2, 0, 1};
        System.out.println("Test Case 2:");
        solution.sortColors(nums2);
        System.out.println("Expected: [0, 1, 2]");
        System.out.println("Actual:   " + Arrays.toString(nums2));
        System.out.println();
        
        // Test Case 3: Single element
        int[] nums3 = {0};
        System.out.println("Test Case 3:");
        solution.sortColors(nums3);
        System.out.println("Expected: [0]");
        System.out.println("Actual:   " + Arrays.toString(nums3));
    }
}