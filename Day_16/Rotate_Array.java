package Day_16;
import java.util.*;

public class Rotate_Array {

    public static void rotate(int[] nums, int k) {

        int n = nums.length;

        // Prevent division by zero for an empty array
        if (n == 0) {
            return;
        }

        k = k % n;

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {

            int newIndex = (i + k) % n;

            ans[newIndex] = nums[i];
        }

        // Copy rotated elements back into nums
        for (int i = 0; i < n; i++) {
            nums[i] = ans[i];
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.print("Enter number of rotations: ");
        int k = sc.nextInt();

        rotate(nums, k);

        System.out.println("Rotated array:");

        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }

        sc.close();
    }
}