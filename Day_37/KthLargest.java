import java.util.*;

public class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {

        if (minHeap.size() < k) {
            minHeap.offer(val);
        }
        else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }

        return minHeap.peek();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of k: ");
        int k = sc.nextInt();

        System.out.print("Enter number of initial elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        KthLargest kthLargest = new KthLargest(k, nums);

        System.out.print("Enter number of values to add: ");
        int q = sc.nextInt();

        System.out.println("Kth Largest after each insertion:");

        for (int i = 0; i < q; i++) {
            int val = sc.nextInt();
            System.out.println(kthLargest.add(val));
        }

        sc.close();
    }
}