import java.util.Arrays;

public class CheapestFlights {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Array to track the minimum cost to reach each node
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Run the loop exactly K + 1 times (K stops = K + 1 edges)
        for (int i = 0; i <= k; i++) {
            // Create a temp array to prevent chaining flights in the same step
            int[] temp = Arrays.copyOf(dist, n);
            
            for (int[] flight : flights) {
                int u = flight[0]; // from
                int v = flight[1]; // to
                int w = flight[2]; // price

                // If the starting node is reachable, check if this flight is cheaper
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < temp[v]) {
                    temp[v] = dist[u] + w;
                }
            }
            // Update the main distance array for the next iteration
            dist = temp;
        }

        // If the destination is still unreachable, return -1
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        CheapestFlights solution = new CheapestFlights();

        // Test Case 1: 1 stop allowed
        int n1 = 4;
        int[][] flights1 = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int src1 = 0, dst1 = 3, k1 = 1;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 700");
        System.out.println("Actual:   " + solution.findCheapestPrice(n1, flights1, src1, dst1, k1));
        System.out.println();

        // Test Case 2: 1 stop allowed, cheaper to take the stop
        int n2 = 3;
        int[][] flights2 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src2 = 0, dst2 = 2, k2 = 1;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 200");
        System.out.println("Actual:   " + solution.findCheapestPrice(n2, flights2, src2, dst2, k2));
        System.out.println();

        // Test Case 3: 0 stops allowed (must be a direct flight)
        int n3 = 3;
        int[][] flights3 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src3 = 0, dst3 = 2, k3 = 0;
        System.out.println("Test Case 3:");
        System.out.println("Expected: 500");
        System.out.println("Actual:   " + solution.findCheapestPrice(n3, flights3, src3, dst3, k3));
    }
}