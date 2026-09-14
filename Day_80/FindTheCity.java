import java.util.Arrays;

public class FindTheCity {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dist = new int[n][n];
        int INF = 1000000000;

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            dist[u][v] = w;
            dist[v][u] = w;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }

        int minReachable = n;
        int bestCity = -1;

        for (int i = 0; i < n; i++) {
            int reachableCount = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= distanceThreshold) {
                    reachableCount++;
                }
            }

            if (reachableCount <= minReachable) {
                minReachable = reachableCount;
                bestCity = i;
            }
        }

        return bestCity;
    }

    public static void main(String[] args) {
        FindTheCity solution = new FindTheCity();

        // Test Case 1
        int n1 = 4;
        int[][] edges1 = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold1 = 4;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.findTheCity(n1, edges1, distanceThreshold1));
        System.out.println();

        // Test Case 2
        int n2 = 5;
        int[][] edges2 = {{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
        int distanceThreshold2 = 2;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 0");
        System.out.println("Actual:   " + solution.findTheCity(n2, edges2, distanceThreshold2));
    }
}