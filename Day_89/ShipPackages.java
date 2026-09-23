public class ShipPackages {

    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }
        
        int ans = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canShip(weights, days, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
    
    private boolean canShip(int[] weights, int daysLimit, int capacity) {
        int daysNeeded = 1;
        int currentLoad = 0;
        
        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                daysNeeded++;
                currentLoad = weight;
                if (daysNeeded > daysLimit) {
                    return false;
                }
            } else {
                currentLoad += weight;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ShipPackages solution = new ShipPackages();

        // Test Case 1: Standard case
        int[] weights1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days1 = 5;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 15");
        System.out.println("Actual:   " + solution.shipWithinDays(weights1, days1));
        System.out.println();

        // Test Case 2: Days equals number of packages (must carry max weight)
        int[] weights2 = {3, 2, 2, 4, 1, 4};
        int days2 = 3;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 6");
        System.out.println("Actual:   " + solution.shipWithinDays(weights2, days2));
        System.out.println();

        // Test Case 3: 1 day allowed (must carry total weight)
        int[] weights3 = {1, 2, 3, 1, 1};
        int days3 = 4;
        System.out.println("Test Case 3:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.shipWithinDays(weights3, days3));
    }
}