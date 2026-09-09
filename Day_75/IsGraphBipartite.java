import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; 

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                colors[i] = 1; 

                while (!queue.isEmpty()) {
                    int node = queue.poll();

                    for (int neighbor : graph[node]) {
                        if (colors[neighbor] == colors[node]) {
                            return false;
                        }
                        if (colors[neighbor] == 0) {
                            colors[neighbor] = -colors[node];
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsGraphBipartite solution = new IsGraphBipartite();

        // Test Case 1: Graph with an odd cycle (Triangle between 0, 1, 2)
        int[][] graph1 = {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: false");
        System.out.println("Actual:   " + solution.isBipartite(graph1));
        System.out.println();

        // Test Case 2: Valid Bipartite Graph
        int[][] graph2 = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println("Test Case 2:");
        System.out.println("Expected: true");
        System.out.println("Actual:   " + solution.isBipartite(graph2));
    }
}