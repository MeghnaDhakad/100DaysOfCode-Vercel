package Day_3;
import java.util.*;

public class Missing_in_array {
    public static int missingNum(int[] arr) {
        int ans = arr.length + 1;

        for (int i = 0; i < arr.length; i++) {
            ans ^= (i + 1);
            ans ^= arr[i];
        }

        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter length of array:");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("enter elements of array:");
        for(int i = 0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(missingNum(arr));
    }
}
