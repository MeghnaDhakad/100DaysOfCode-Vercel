package Day_1;
import java.util.*;

class Two_sum {
    public static int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{};
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
        System.out.println("enter target value");
        int target = sc.nextInt();
        try{
            System.out.println(Arrays.toString(twoSum(nums,target)));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}