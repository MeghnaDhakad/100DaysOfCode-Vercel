import java.util.Arrays;

public class TravellingSalesmanProblem {

    public int tsp(int[][] cost) {
        int n = cost.length;
        
        // Memoization table: memo[mask][pos]
        // Size is (2^n) x n
        int[][] memo = new int[1 << n][n];
        
        // Initialize the memoization table with -1 (indicating uncomputed states)
        for (int i = 0; i < (1 << n); i++) {
            Arrays.fill(memo[i], -1);
        }
        
        // Start from city 0. 
        // Initial mask is 1 (binary 000...01) because we've visited city 0.
        return solve(1, 0, cost, memo);
    }
    
    private int solve(int mask, int pos, int[][] cost, int[][] memo) {
        int n = cost.length;
        
        // Base case: All cities have been visited
        // (1 << n) - 1 creates a number where the first 'n' bits are all 1s
        if (mask == (1 << n) - 1) {
            return cost[pos][0]; 
        }
        
        // If we have already computed this state, return the saved result
        if (memo[mask][pos] != -1) {
            return memo[mask][pos];
        }
        
        int minCost = Integer.MAX_VALUE;
        
        // Try visiting all other unvisited cities
        for (int city = 0; city < n; city++) {
            // Check if 'city' is unvisited (i.e., the city-th bit in mask is 0)
            if ((mask & (1 << city)) == 0) {
                // Mark 'city' as visited in the new mask
                int newMask = mask | (1 << city);
                
                // Recursively calculate cost and find the minimum
                int currentCost = cost[pos][city] + solve(newMask, city, cost, memo);
                minCost = Math.min(minCost, currentCost);
            }
        }
        
        // Save and return the result
        return memo[mask][pos] = minCost;
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        TravellingSalesmanProblem solution = new TravellingSalesmanProblem();

        // Test Case 1: Simple 2-city tour
        int[][] cost1 = {
            {0, 111}, 
            {112, 0}
        };
        System.out.println("Test Case 1:");
        System.out.println("Expected: 223");
        System.out.println("Actual:   " + solution.tsp(cost1));
        System.out.println();

        // Test Case 2: 3-city tour
        int[][] cost2 = {
            {0, 1000, 5000},
            {5000, 0, 1000},
            {1000, 5000, 0}
        };
        System.out.println("Test Case 2:");
        System.out.println("Expected: 3000");
        System.out.println("Actual:   " + solution.tsp(cost2));
        System.out.println();
        
        // Test Case 3: 4-city tour (Adding an extra test case for better verification)
        int[][] cost3 = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        System.out.println("Test Case 3 (4 Cities):");
        System.out.println("Expected: 80"); // Tour: 0 -> 1 -> 3 -> 2 -> 0 => 10 + 25 + 30 + 15 = 80
        System.out.println("Actual:   " + solution.tsp(cost3));
    }
}