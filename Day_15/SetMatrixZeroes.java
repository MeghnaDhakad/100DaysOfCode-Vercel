import java.util.*;

public class SetMatrixZeroes {

    public static void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] rows = new boolean[m];
        boolean[] columns = new boolean[n];

        // Find zeroes and mark their rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        // Set marked rows and columns to zero
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (rows[i] || columns[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int m = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];

        System.out.println("Enter matrix elements:");

        // Input matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Call the function
        setZeroes(matrix);

        // Print the modified matrix
        System.out.println("Matrix after setting zeroes:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}