public class SqrtX {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        
        int left = 1;
        int right = x;
        int ans = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            
            if (square == x) {
                return mid;
            }
            
            if (square < x) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        SqrtX solution = new SqrtX();

        // Test Case 1: Perfect square
        int x1 = 4;
        System.out.println("Test Case 1:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.mySqrt(x1));
        System.out.println();

        // Test Case 2: Non-perfect square
        int x2 = 8;
        System.out.println("Test Case 2:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.mySqrt(x2));
        System.out.println();
        
        // Test Case 3: Large input (checks for integer overflow)
        int x3 = 2147395599; // Close to Integer.MAX_VALUE
        System.out.println("Test Case 3 (Large Input):");
        System.out.println("Expected: 46339");
        System.out.println("Actual:   " + solution.mySqrt(x3));
    }
}