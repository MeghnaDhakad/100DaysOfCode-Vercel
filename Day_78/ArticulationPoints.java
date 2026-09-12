import java.util.ArrayList;

public class ArticulationPoints {

    static int timer = 0;

    public static ArrayList<Integer> articulationPoints(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        timer = 0; 

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, visited, tin, low, isAP, adj);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isAP[i]) {
                ans.add(i);
            }
        }

        if (ans.size() == 0) {
            ans.add(-1);
        }

        return ans;
    }

    static void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low, 
                    boolean[] isAP, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        tin[node] = low[node] = timer++;
        int childCount = 0;

        for (int neighbor : adj.get(node)) {
            if (neighbor == parent) continue;

            if (!visited[neighbor]) {
                childCount++;
                dfs(neighbor, node, visited, tin, low, isAP, adj);
                low[node] = Math.min(low[node], low[neighbor]);
                
                if (parent != -1 && low[neighbor] >= tin[node]) {
                    isAP[node] = true;
                }
            } else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }

        if (parent == -1 && childCount > 1) {
            isAP[node] = true;
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        int V1 = 5;
        int[][] edges1 = {{0, 1}, {1, 4}, {4, 3}, {4, 2}, {2, 3}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: [1, 4]");
        System.out.println("Actual:   " + articulationPoints(V1, edges1));
        System.out.println();

        // Test Case 2
        int V2 = 4;
        int[][] edges2 = {{0, 1}, {0, 2}};
        System.out.println("Test Case 2:");
        System.out.println("Expected: [0]");
        System.out.println("Actual:   " + articulationPoints(V2, edges2));
    }
}