import java.util.*;

public class CountSCC {

    public int countSCC(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) adj.get(edge[0]).add(edge[1]);

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs1(i, visited, adj, stack);
        }

        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        for (int i = 0; i < V; i++) transpose.add(new ArrayList<>());
        for (int[] edge : edges) transpose.get(edge[1]).add(edge[0]);

        Arrays.fill(visited, false);
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                sccCount++;
                dfs2(node, visited, transpose);
            }
        }

        return sccCount;
    }

    private void dfs1(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) dfs1(neighbor, visited, adj, stack);
        }
        stack.push(node);
    }

    private void dfs2(int node, boolean[] visited, ArrayList<ArrayList<Integer>> transpose) {
        visited[node] = true;
        for (int neighbor : transpose.get(node)) {
            if (!visited[neighbor]) dfs2(neighbor, visited, transpose);
        }
    }

    public static void main(String[] args) {
        CountSCC solution = new CountSCC();

        // Test Case 1
        int V1 = 6;
        int[][] edges1 = {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3}, {4, 5}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.countSCC(V1, edges1));
        System.out.println();

        // Test Case 2
        int V2 = 3;
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.countSCC(V2, edges2));
    }
}