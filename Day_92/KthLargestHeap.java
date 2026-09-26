import java.util.PriorityQueue;

public class KthLargestHeap {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.add(num);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }

    public static void main(String[] args) {
        KthLargestHeap solution = new KthLargestHeap();

        // Test Case 1: Standard case
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 5");
        System.out.println("Actual:   " + solution.findKthLargest(nums1, k1));
        System.out.println();

        // Test Case 2: Array with duplicates (The TLE killer!)
        int[] nums2 = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        int k2 = 4;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.findKthLargest(nums2, k2));
    }
}