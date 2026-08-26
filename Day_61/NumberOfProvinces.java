public class NumberOfProvinces {

    // Function to find the number of provinces
    public static int findCircleNum(int[][] isConnected) {
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
    
    // Depth-First Search helper method
    private static void dfs(int[][] isConnected, boolean[] visited, int city) {
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

    public static void main(String[] args) {
        // --- Test Case 1 ---
        // Graph visualization:
        // 0 -- 1
        // 
        // 2 (isolated)
        int[][] isConnected1 = {
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        
        System.out.println("Test Case 1 Output: " + findCircleNum(isConnected1)); 
        // Expected Output: 2

        // --- Test Case 2 ---
        // Graph visualization:
        // 0 (isolated)
        // 1 (isolated)
        // 2 (isolated)
        int[][] isConnected2 = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        
        System.out.println("Test Case 2 Output: " + findCircleNum(isConnected2)); 
        // Expected Output: 3
    }
}