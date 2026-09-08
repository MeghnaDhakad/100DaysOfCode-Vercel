package Day_74;

public class NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinceCount = 0;

        // Iterate through every city
        for (int i = 0; i < n; i++) {
            // If the city hasn't been visited yet, we've found a new province
            if (!visited[i]) {
                provinceCount++;
                // Use DFS to visit all cities in this province
                dfs(isConnected, visited, i);
            }
        }

        return provinceCount;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        // Mark the current city as visited
        visited[city] = true;

        // Check all potential neighbors of the current city
        for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
            // If there's a connection and the neighbor hasn't been visited
            if (isConnected[city][neighbor] == 1 && !visited[neighbor]) {
                dfs(isConnected, visited, neighbor);
            }
        }
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        NumberOfProvinces solution = new NumberOfProvinces();

        // Test Case 1: From the LeetCode example in the image
        int[][] isConnected1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Test Case 1:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.findCircleNum(isConnected1));
        System.out.println();

        // Test Case 2: Fully connected graph (1 province)
        int[][] isConnected2 = {
            {1, 1, 0},
            {1, 1, 1},
            {0, 1, 1}
        };
        System.out.println("Test Case 2 (Indirect connection):");
        System.out.println("Expected: 1"); // 0 is connected to 1, and 1 is connected to 2
        System.out.println("Actual:   " + solution.findCircleNum(isConnected2));
        System.out.println();
        
        // Test Case 3: completely disconnected graph (3 provinces)
        int[][] isConnected3 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println("Test Case 3 (Completely disconnected):");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.findCircleNum(isConnected3));
    }
}