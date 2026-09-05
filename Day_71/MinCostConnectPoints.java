import java.util.PriorityQueue;

public class MinCostConnectPoints {

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        
        // Min-Heap stores arrays of size 2: {distance, node_index}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // Start from node 0 with a cost of 0
        pq.offer(new int[]{0, 0});
        
        boolean[] visited = new boolean[n];
        int connectedNodes = 0;
        int minCost = 0;

        while (connectedNodes < n) {
            int[] current = pq.poll();
            int cost = current[0];
            int u = current[1];

            // If we already visited this node, skip it (Lazy deletion)
            if (visited[u]) continue;

            // Mark as visited and add to our MST cost
            visited[u] = true;
            minCost += cost;
            connectedNodes++;

            // Calculate the distance to all unvisited nodes and add to the queue
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int dist = Math.abs(points[u][0] - points[v][0]) + 
                               Math.abs(points[u][1] - points[v][1]);
                    
                    pq.offer(new int[]{dist, v});
                }
            }
        }

        return minCost;
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        MinCostConnectPoints solution = new MinCostConnectPoints();

        // Test Case 1: Standard case
        int[][] points1 = {{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 20");
        System.out.println("Actual:   " + solution.minCostConnectPoints(points1));
        System.out.println();

        // Test Case 2: Negative coordinates
        int[][] points2 = {{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 18");
        System.out.println("Actual:   " + solution.minCostConnectPoints(points2));
        System.out.println();
        
        // Test Case 3: All points in a straight line
        int[][] points3 = {{0, 0}, {1, 1}, {10, 10}, {-1, -1}};
        System.out.println("Test Case 3:");
        System.out.println("Expected: 22");
        System.out.println("Actual:   " + solution.minCostConnectPoints(points3));
    }
}