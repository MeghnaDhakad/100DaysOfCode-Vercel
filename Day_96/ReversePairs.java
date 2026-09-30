public class ReversePairs {

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSortAndCount(nums, 0, nums.length - 1);
    }
    
    private int mergeSortAndCount(int[] nums, int left, int right) {
        if (left >= right) return 0;
        
        int mid = left + (right - left) / 2;
        int count = mergeSortAndCount(nums, left, mid) + mergeSortAndCount(nums, mid + 1, right);
        
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && nums[i] > 2L * nums[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }
        
        merge(nums, left, mid, right);
        return count;
    }
    
    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) temp[k++] = nums[i++];
            else temp[k++] = nums[j++];
        }
        
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];
        
        for (int p = 0; p < temp.length; p++) {
            nums[left + p] = temp[p];
        }
    }

    public static void main(String[] args) {
        ReversePairs solution = new ReversePairs();

        // Test Case 1: Standard
        int[] nums1 = {1, 3, 2, 3, 1};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.reversePairs(nums1));
        System.out.println();

        // Test Case 2: Sorted in descending order
        int[] nums2 = {2, 4, 3, 5, 1};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.reversePairs(nums2));
        System.out.println();

        // Test Case 3: Integer overflow edge case
        int[] nums3 = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println("Test Case 3:");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.reversePairs(nums3));
    }
}