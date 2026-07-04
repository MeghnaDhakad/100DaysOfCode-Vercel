import java.util.*;

public class Power_of_two {
    public static boolean isPowerOfTwo(int n) {
        if (n>0 && (n & (n - 1)) == 0) {
            return true;
        } else {
            return false;
        } 
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter value of n");
        int n = sc.nextInt();
        System.out.println(fib(n));
        sc.close();
    }    
}