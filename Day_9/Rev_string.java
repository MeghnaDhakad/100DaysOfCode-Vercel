import java.util.*;

class Rev_string {

    public static void reverseString(char[] s) {

        int left = 0;
        int right = s.length - 1;

        while (left < right) {

            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter length of array:");
        int n = sc.nextInt();

        char[] nums = new char[n];

        System.out.println("Enter elements of array:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.next().charAt(0);
        }

        reverseString(nums);

        System.out.println(Arrays.toString(nums));
    }
}