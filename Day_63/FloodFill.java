import java.util.Arrays;

public class FloodFill {

    // Function to perform the flood fill
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        
        // If the starting pixel is already the target color, do nothing
        if (originalColor != color) {
            dfs(image, sr, sc, originalColor, color);
        }
        
        return image;
    }
    
    // Depth-First Search helper method
    private static void dfs(int[][] image, int row, int col, int originalColor, int newColor) {
        // 1. Boundary Checks: Are we out of bounds?
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return;
        }
        
        // 2. Color Check: Is this pixel a valid target for the flood?
        if (image[row][col] != originalColor) {
            return;
        }
        
        // 3. Process: Paint the current pixel
        image[row][col] = newColor;
        
        // 4. Spread: Recursively paint the 4 adjacent neighbors (Up, Down, Left, Right)
        dfs(image, row - 1, col, originalColor, newColor);
        dfs(image, row + 1, col, originalColor, newColor);
        dfs(image, row, col - 1, originalColor, newColor);
        dfs(image, row, col + 1, originalColor, newColor);
    }

    // Helper method to print the 2D image matrix
    public static void printImage(int[][] image) {
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // --- Test Case 1 ---
        // An image represented by a 3x3 grid
        int[][] image1 = {
            {1, 1, 1},
            {1, 1, 0},
            {1, 0, 1}
        };
        int sr1 = 1, sc1 = 1, color1 = 2;
        
        System.out.println("Test Case 1 - Original Image:");
        printImage(image1);
        
        int[][] result1 = floodFill(image1, sr1, sc1, color1);
        
        System.out.println("Test Case 1 - After Flood Fill (sr=1, sc=1, color=2):");
        printImage(result1);


        // --- Test Case 2 ---
        // An image where the start pixel is already the target color
        int[][] image2 = {
            {0, 0, 0},
            {0, 0, 0}
        };
        int sr2 = 0, sc2 = 0, color2 = 0;
        
        System.out.println("Test Case 2 - Original Image:");
        printImage(image2);
        
        int[][] result2 = floodFill(image2, sr2, sc2, color2);
        
        System.out.println("Test Case 2 - After Flood Fill (sr=0, sc=0, color=0):");
        printImage(result2);
    }
}