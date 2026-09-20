#include <stdio.h>

long long integerSquareRoot(long long n) {
    // Base cases for 0 and 1
    if (n == 0 || n == 1) {
        return n;
    }

    long long left = 1;
    // The square root of any number >= 2 will never exceed n / 2
    long long right = n / 2; 
    long long ans = 0;

    while (left <= right) {
        long long mid = left + (right - left) / 2;
        long long square = mid * mid;

        if (square == n) {
            return mid; // Perfect square found
        }

        if (square < n) {
            ans = mid;      // Store mid as a potential answer
            left = mid + 1; // Look for a larger valid integer
        } else {
            right = mid - 1; // Square is too large, search the left half
        }
    }

    return ans;
}

int main() {
    long long n;
    
    // Read the non-negative integer
    if (scanf("%lld", &n) != 1 || n < 0) return 0;
    
    // Calculate and print the integer square root
    long long result = integerSquareRoot(n);
    printf("%lld\n", result);
    
    return 0;
}