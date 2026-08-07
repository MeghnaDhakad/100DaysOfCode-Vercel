package Day_42;
import java.util.*;

public class MedianFinderDemo {

    static class MedianFinder {

        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {

            maxHeap.offer(num);

            minHeap.offer(maxHeap.poll());

            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }

            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }

        public void displayHeaps() {
            System.out.println("Max Heap: " + maxHeap);
            System.out.println("Min Heap: " + minHeap);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MedianFinder mf = new MedianFinder();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");

        for (int i = 0; i < n; i++) {

            int num = sc.nextInt();

            mf.addNum(num);

            System.out.println("\nAfter inserting " + num + ":");
            mf.displayHeaps();
            System.out.println("Current Median = " + mf.findMedian());
        }

        sc.close();
    }
}