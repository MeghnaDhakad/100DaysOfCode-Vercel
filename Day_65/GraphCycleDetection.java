import java.util.*;

public class GraphCycleDetection {

    // Function to detect cycle in an undirected graph
    public static boolean isCycle(int V, int[][] edges) {

        // Step 1: Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Step 2: Visited array
        boolean[] visited = new boolean[V];

        // Step 3: Check every component
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, visited, adj)) {
                    return true;
                }
            }
        }

        return false;
    }

    // DFS helper
    private static boolean dfs(
            int current,
            int parent,
            boolean[] visited,
            List<List<Integer>> adj) {

        visited[current] = true;

        for (int neighbor : adj.get(current)) {

            // If neighbor is not visited, continue DFS
            if (!visited[neighbor]) {
                if (dfs(neighbor, current, visited, adj)) {
                    return true;
                }
            }

            // If visited and not the parent, cycle exists
            else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    // Main method for VS Code testing
    public static void main(String[] args) {

        int V = 5;

        int[][] edges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 4},
            {4, 1}
        };

        boolean result = isCycle(V, edges);

        System.out.println("Cycle present: " + result);
    }
}