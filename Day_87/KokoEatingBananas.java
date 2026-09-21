public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        
        for (int pile : piles) {
            right = Math.max(right, pile);
        }
        
        int bestSpeed = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canFinish(piles, mid, h)) {
                bestSpeed = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return bestSpeed;
    }
    
    private boolean canFinish(int[] piles, int speed, int h) {
        long hours = 0;
        
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed;
        }
        
        return hours <= h;
    }

    public static void main(String[] args) {
        KokoEatingBananas solution = new KokoEatingBananas();

        // Test Case 1: Standard case
        int[] piles1 = {3, 6, 7, 11};
        int h1 = 8;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 4");
        System.out.println("Actual:   " + solution.minEatingSpeed(piles1, h1));
        System.out.println();

        // Test Case 2: h is exactly the number of piles (must eat max pile in 1 hour)
        int[] piles2 = {30, 11, 23, 4, 20};
        int h2 = 5;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 30");
        System.out.println("Actual:   " + solution.minEatingSpeed(piles2, h2));
        System.out.println();
        
        // Test Case 3: Slightly more time allowed than piles
        int[] piles3 = {30, 11, 23, 4, 20};
        int h3 = 6;
        System.out.println("Test Case 3:");
        System.out.println("Expected: 23");
        System.out.println("Actual:   " + solution.minEatingSpeed(piles3, h3));
    }
}