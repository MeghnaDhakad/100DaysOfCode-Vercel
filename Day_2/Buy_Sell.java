package Day_2;
import java.util.*;
class Buy_Sell {
    public static int maxProfit(int[] prices) {

        int buy = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {

            if (prices[i] < buy) {
                buy = prices[i];
            } else {
                int currentProfit = prices[i] - buy;

                if (currentProfit > profit) {
                    profit = currentProfit;
                }
            }
        }

        return profit;
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
        System.out.println(maxProfit(nums));
    }
}