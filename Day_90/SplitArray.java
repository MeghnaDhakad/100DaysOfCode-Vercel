public class SplitArray {

    public int splitArray(int[] nums, int k) {
        int left = 0;
        int right = 0;
        
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        
        int ans = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canSplit(nums, k, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
    
    private boolean canSplit(int[] nums, int k, int maxSum) {
        int subarrays = 1;
        int currentSum = 0;
        
        for (int num : nums) {
            if (currentSum + num > maxSum) {
                subarrays++;
                currentSum = num;
                if (subarrays > k) {
                    return false;
                }
            } else {
                currentSum += num;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArray solution = new SplitArray();

        // Test Case 1: Standard case
        int[] nums1 = {7, 2, 5, 10, 8};
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 18");
        System.out.println("Actual:   " + solution.splitArray(nums1, k1));
        System.out.println();

        // Test Case 2: Array elements form their own subarrays
        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 5;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 5");
        System.out.println("Actual:   " + solution.splitArray(nums2, k2));
        System.out.println();

        // Test Case 3: Entire array is one subarray
        int[] nums3 = {1, 4, 4};
        int k3 = 1;
        System.out.println("Test Case 3:");
        System.out.println("Expected: 9");
        System.out.println("Actual:   " + solution.splitArray(nums3, k3));
    }
}