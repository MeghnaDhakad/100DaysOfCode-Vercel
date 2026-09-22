import java.util.Arrays;

public class MagneticForce {

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        
        int left = 1;
        int right = position[position.length - 1] - position[0];
        int ans = 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canPlaceBalls(position, m, mid)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean canPlaceBalls(int[] position, int m, int minForce) {
        int count = 1;
        int lastPlaced = position[0];
        
        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPlaced >= minForce) {
                count++;
                lastPlaced = position[i];
                if (count == m) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MagneticForce solution = new MagneticForce();

        // Test Case 1
        int[] position1 = {1, 2, 3, 4, 7};
        int m1 = 3;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.maxDistance(position1, m1));
        System.out.println();

        // Test Case 2 (Large positions)
        int[] position2 = {5, 4, 3, 2, 1, 1000000000};
        int m2 = 2;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 999999999");
        System.out.println("Actual:   " + solution.maxDistance(position2, m2));
    }
}