import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class NetworkDelayTime {

    public int networkDelayTime(int[][] times, int n, int k) {
        // 1. Build the graph using an Adjacency List
        // We use n + 1 because nodes are 1-indexed (1 to n)
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : times) {
            int u = edge[0]; // source
            int v = edge[1]; // target
            int w = edge[2]; // time/weight
            graph[u].add(new int[]{v, w});
        }

        // 2. Min-Heap Priority Queue to store {distance_from_source, node}
        // Ordered by minimum distance first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // 3. Array to track shortest distances, initialized to Infinity
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // Start at node k
        dist[k] = 0;
        pq.offer(new int[]{0, k});

        // 4. Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int u = current[1];

            // Lazy deletion: if we already found a shorter path to u, skip this one
            if (currentDist > dist[u]) continue;

            // Visit neighbors
            for (int[] neighbor : graph[u]) {
                int v = neighbor[0];
                int weight = neighbor[1];

                // Relaxation step: If we found a faster way to reach v
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        // 5. Find the maximum distance among all nodes
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1; // A node was unreachable
            }
            maxTime = Math.max(maxTime, dist[i]);
        }

        return maxTime;
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();

        // Test Case 1: Standard case where all nodes are reached
        int[][] times1 = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n1 = 4;
        int k1 = 2;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.networkDelayTime(times1, n1, k1));
        System.out.println();

        // Test Case 2: Simple two-node graph, fully reachable
        int[][] times2 = {{1, 2, 1}};
        int n2 = 2;
        int k2 = 1;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.networkDelayTime(times2, n2, k2));
        System.out.println();

        // Test Case 3: Disconnected node (node 1 cannot be reached from node 2)
        int[][] times3 = {{1, 2, 1}};
        int n3 = 2;
        int k3 = 2;
        System.out.println("Test Case 3 (Disconnected):");
        System.out.println("Expected: -1");
        System.out.println("Actual:   " + solution.networkDelayTime(times3, n3, k3));
    }
}