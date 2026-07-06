package Day_10;
import java.util.*;

public class Intersection_of_array {

    public static int[] intersect(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] ans = new int[Math.min(nums1.length, nums2.length)];

        int i = 0, j = 0, k = 0;

        while (i < nums1.length && j < nums2.length) {

            if (nums1[i] == nums2[j]) {
                ans[k++] = nums1[i];
                i++;
                j++;
            }
            else if (nums1[i] < nums2[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        return Arrays.copyOf(ans, k);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of first array: ");
        int n = sc.nextInt();

        int[] nums1 = new int[n];

        System.out.println("Enter elements of first array:");
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }

        System.out.print("Enter size of second array: ");
        int m = sc.nextInt();

        int[] nums2 = new int[m];

        System.out.println("Enter elements of second array:");
        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextInt();
        }

        int[] result = intersect(nums1, nums2);

        System.out.println("Intersection: " + Arrays.toString(result));

        sc.close();
    }
}