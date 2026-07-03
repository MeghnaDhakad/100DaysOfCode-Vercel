package Day_7;
import java.util.*;
import java.util.Scanner;

public class Fibo {
    public static int fib(int n) {
    if (n <= 1) {
        return n;
    }
    int a = 0;
    int b = 1;
    for (int i = 2; i <= n; i++) {
        int c = a + b;
        a = b;
        b = c;
    }
    return b;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter value of n");
        int n = sc.nextInt();
        System.out.println(fib(n));
        sc.close();
    }
}
