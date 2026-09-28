import java.util.Arrays;

public class RelativeSort {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];
        for (int num : arr1) {
            count[num]++;
        }
        
        int[] result = new int[arr1.length];
        int index = 0;
        
        for (int num : arr2) {
            while (count[num] > 0) {
                result[index++] = num;
                count[num]--;
            }
        }
        
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[index++] = i;
                count[i]--;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        RelativeSort solution = new RelativeSort();

        // Test Case 1: Standard case
        int[] arr1_1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2_1 = {2, 1, 4, 3, 9, 6};
        System.out.println("Test Case 1:");
        System.out.println("Expected: [2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19]");
        System.out.println("Actual:   " + Arrays.toString(solution.relativeSortArray(arr1_1, arr2_1)));
        System.out.println();

        // Test Case 2: Elements in arr1 that are completely missing from arr2
        int[] arr1_2 = {28, 6, 22, 8, 44, 17};
        int[] arr2_2 = {22, 28, 8, 6};
        System.out.println("Test Case 2:");
        System.out.println("Expected: [22, 28, 8, 6, 17, 44]");
        System.out.println("Actual:   " + Arrays.toString(solution.relativeSortArray(arr1_2, arr2_2)));
    }
}