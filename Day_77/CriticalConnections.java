import java.util.*;

public class CriticalConnections {

    private int timer = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> conn : connections) {
            graph[conn.get(0)].add(conn.get(1));
            graph[conn.get(1)].add(conn.get(0));
        }

        boolean[] visited = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<List<Integer>> bridges = new ArrayList<>();

        dfs(0, -1, visited, tin, low, graph, bridges);

        return bridges;
    }

    private void dfs(int node, int parent, boolean[] visited, int[] tin, int[] low, 
                     List<Integer>[] graph, List<List<Integer>> bridges) {
        
        visited[node] = true;
        tin[node] = low[node] = timer++;

        for (int neighbor : graph[node]) {
            if (neighbor == parent) continue;

            if (!visited[neighbor]) {
                dfs(neighbor, node, visited, tin, low, graph, bridges);
                low[node] = Math.min(low[node], low[neighbor]);
                
                if (low[neighbor] > tin[node]) {
                    bridges.add(Arrays.asList(node, neighbor));
                }
            } else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }
    }

    public static void main(String[] args) {
        CriticalConnections solution = new CriticalConnections();

        // Test Case 1: A square with an extra tail
        int n1 = 4;
        List<List<Integer>> connections1 = new ArrayList<>();
        connections1.add(Arrays.asList(0, 1));
        connections1.add(Arrays.asList(1, 2));
        connections1.add(Arrays.asList(2, 0));
        connections1.add(Arrays.asList(1, 3));
        
        System.out.println("Test Case 1:");
        System.out.println("Expected: [[1, 3]]");
        System.out.println("Actual:   " + solution.criticalConnections(n1, connections1));
        System.out.println();

        // Test Case 2: Just two nodes connected
        int n2 = 2;
        List<List<Integer>> connections2 = new ArrayList<>();
        connections2.add(Arrays.asList(0, 1));

        System.out.println("Test Case 2:");
        System.out.println("Expected: [[0, 1]]");
        System.out.println("Actual:   " + solution.criticalConnections(n2, connections2));
    }
}