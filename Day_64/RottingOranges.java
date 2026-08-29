import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    // Function to calculate the minimum minutes for all oranges to rot
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Queue will store the {row, col} coordinates of rotten oranges
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        // Step 1: Scan the grid. 
        // Put all initially rotten oranges in the queue and count fresh oranges.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        
        // If there are no fresh oranges to begin with, it takes 0 minutes.
        if (freshCount == 0) {
            return 0;
        }
        
        int minutes = 0;
        // Arrays to easily calculate the 4 adjacent neighbors (Up, Down, Left, Right)
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        // Step 2: Multi-Source BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedSomethingThisMinute = false;
            
            // Process all oranges that are rotting at the current minute
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int row = current[0];
                int col = current[1];
                
                // Check all 4 adjacent directions
                for (int[] dir : directions) {
                    int newRow = row + dir[0];
                    int newCol = col + dir[1];
                    
                    // If the neighbor is within bounds and is a fresh orange...
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        // Rot the fresh orange
                        grid[newRow][newCol] = 2;
                        freshCount--;
                        
                        // Add the newly rotten orange to the queue for the next minute's spread
                        queue.offer(new int[]{newRow, newCol});
                        rottedSomethingThisMinute = true;
                    }
                }
            }
            
            // Only increment time if we actually spread the rot to new oranges
            if (rottedSomethingThisMinute) {
                minutes++;
            }
        }
        
        // Step 3: Check if any fresh oranges survived the rot
        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        // --- Test Case 1 ---
        // Normal spread
        int[][] grid1 = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println("Test Case 1 Output: " + orangesRotting(grid1)); 
        // Expected Output: 4

        // --- Test Case 2 ---
        // An isolated fresh orange in the bottom left cannot be reached
        int[][] grid2 = {
            {2, 1, 1},
            {0, 1, 1},
            {1, 0, 1}
        };
        System.out.println("Test Case 2 Output: " + orangesRotting(grid2)); 
        // Expected Output: -1 (because of the trapped 1 at the bottom left)

        // --- Test Case 3 ---
        // Already zero fresh oranges
        int[][] grid3 = {
            {0, 2}
        };
        System.out.println("Test Case 3 Output: " + orangesRotting(grid3)); 
        // Expected Output: 0
    }
}