package Day_12;
import java.util.*;

public class Toeplitz_matrix {
    public static boolean isToeplitzMatrix(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int m = sc.nextInt();

        System.out.print("Enter number of columns: ");
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];

        System.out.println("Enter the matrix elements:");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        if (isToeplitzMatrix(matrix)) {
            System.out.println("Toeplitz Matrix");
        } else {
            System.out.println("Not a Toeplitz Matrix");
        }

        sc.close();
    }
}
