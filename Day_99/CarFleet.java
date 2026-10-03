import java.util.Arrays;

public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;
        
        // cars[i][0] = position, cars[i][1] = time to reach target
        double[][] cars = new double[n][2];
        
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double) (target - position[i]) / speed[i];
        }
        
        // Sort cars by position in DESCENDING order (closest to target first)
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));
        
        int fleets = 0;
        double currentSlowestTime = 0.0;
        
        for (int i = 0; i < n; i++) {
            double time = cars[i][1];
            
            // If it takes longer than the car ahead, it forms a new fleet
            if (time > currentSlowestTime) {
                fleets++;
                currentSlowestTime = time; 
            }
        }
        
        return fleets;
    }

    public static void main(String[] args) {
        CarFleet solution = new CarFleet();

        // Test Case 1: Standard overlapping fleets
        int target1 = 12;
        int[] position1 = {10, 8, 0, 5, 3};
        int[] speed1 = {2, 4, 1, 1, 3};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 3");
        System.out.println("Actual:   " + solution.carFleet(target1, position1, speed1));
        System.out.println();

        // Test Case 2: Only one car
        int target2 = 10;
        int[] position2 = {3};
        int[] speed2 = {3};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.carFleet(target2, position2, speed2));
        System.out.println();

        // Test Case 3: Fast car stuck behind a slow car
        int target3 = 100;
        int[] position3 = {0, 2, 4};
        int[] speed3 = {4, 2, 1};
        System.out.println("Test Case 3:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.carFleet(target3, position3, speed3));
    }
}