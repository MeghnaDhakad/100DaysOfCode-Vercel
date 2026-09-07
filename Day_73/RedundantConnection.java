import java.util.Arrays;

public class RedundantConnection {

    int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // Nodes are 1-indexed, so we use size n + 1
        parent = new int[n + 1];
        
        // Initially, every node is its own parent (disjoint sets)
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Process each edge
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // If they share the same root parent, a cycle is detected!
            if (find(u) == find(v)) {
                return edge;
            } else {
                union(u, v);
            }
        }
        
        return new int[0];
    }

    // Find the root parent of a node (with Path Compression)
    private int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        // Path compression
        return parent[node] = find(parent[node]);
    }

    // Merge two disjoint sets
    private void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        
        if (rootU != rootV) {
            parent[rootU] = rootV;
        }
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        RedundantConnection solution = new RedundantConnection();

        // Test Case 1: Simple triangle cycle
        int[][] edges1 = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: [2, 3]");
        System.out.println("Actual:   " + Arrays.toString(solution.findRedundantConnection(edges1)));
        System.out.println();

        // Test Case 2: Larger graph with a cycle
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println("Test Case 2:");
        System.out.println("Expected: [1, 4]");
        System.out.println("Actual:   " + Arrays.toString(solution.findRedundantConnection(edges2)));
    }
}