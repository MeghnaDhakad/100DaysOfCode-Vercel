package Day_4;

import java.util.*;

public class Remove_element {
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                count++;
            }
        }

        return count;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter length of array:");
        int n = sc.nextInt();
        int[] nums = new int[n];
        System.out.println("enter elements of array:");
        for(int i = 0;i<n;i++){
            nums[i] = sc.nextInt();
        }
        System.out.println("enter value:");
        int val = sc.nextInt();
        System.out.println(removeElement(nums,val));
    }
}
