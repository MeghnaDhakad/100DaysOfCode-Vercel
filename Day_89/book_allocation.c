#include <stdio.h>
#include <stdlib.h>

// Helper function to check if a given max_pages limit is valid
int isValid(int arr[], int n, int m, long long max_pages) {
    int students_needed = 1;
    long long current_pages_sum = 0;

    for (int i = 0; i < n; i++) {
        // If adding this book exceeds the allowed limit for the current student
        if (current_pages_sum + arr[i] > max_pages) {
            students_needed++;           // Allocate to a new student
            current_pages_sum = arr[i];  // The new student starts with this book
            
            // If we require more students than we have, this limit is invalid
            if (students_needed > m) {
                return 0;
            }
        } else {
            current_pages_sum += arr[i]; // Give book to current student
        }
    }
    
    return 1; // Successfully allocated to <= m students
}

int main() {
    int n, m;
    
    // Read number of books and number of students
    if (scanf("%d %d", &n, &m) != 2) return 0;
    
    // Dynamically allocate memory for books
    int *arr = (int *)malloc(n * sizeof(int));
    
    long long sum = 0;
    int max_val = 0;
    
    // Read pages and calculate sum and max_val for our search space
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
        sum += arr[i];
        if (arr[i] > max_val) {
            max_val = arr[i];
        }
    }
    
    // Edge case: Impossible to allocate if students outnumber books
    // (since every student must get at least one book)
    if (m > n) {
        printf("-1\n");
        free(arr);
        return 0;
    }
    
    // Define the Binary Search space using long long to prevent overflow
    long long left = max_val;
    long long right = sum;
    long long ans = -1;
    
    // Binary Search on the answer
    while (left <= right) {
        long long mid = left + (right - left) / 2;
        
        if (isValid(arr, n, m, mid)) {
            ans = mid;       // We can allocate with this max limit, save it
            right = mid - 1; // Try to MINIMIZE this maximum limit further
        } else {
            left = mid + 1;  // Limit is too strict, we need to allow more pages
        }
    }
    
    // Output the minimum possible value of the maximum pages
    printf("%lld\n", ans);
    
    // Clean up
    free(arr);
    return 0;
}