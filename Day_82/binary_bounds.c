#include <stdio.h>
#include <stdlib.h>

// Finds the index of the first element >= x
int getLowerBound(int arr[], int n, int x) {
    int left = 0;
    int right = n - 1;
    
    // Default answer is 'n' in case all elements in the array are strictly less than 'x'
    int ans = n; 

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] >= x) {
            ans = mid;       // We found a potential answer
            right = mid - 1; // But keep looking left for an even earlier occurrence
        } else {
            left = mid + 1;  // The element is too small, look right
        }
    }
    return ans;
}

// Finds the index of the first element > x
int getUpperBound(int arr[], int n, int x) {
    int left = 0;
    int right = n - 1;
    
    // Default answer is 'n' in case no elements in the array are strictly greater than 'x'
    int ans = n; 

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (arr[mid] > x) {
            ans = mid;       // We found a potential answer
            right = mid - 1; // But keep looking left for an even earlier occurrence
        } else {
            left = mid + 1;  // The element is <= x, look right
        }
    }
    return ans;
}

int main() {
    int n;
    
    // Read the size of the array
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    // Dynamically allocate memory for the array
    int *arr = (int *)malloc(n * sizeof(int));
    
    // Read the sorted array elements
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    
    int x;
    
    // Read the target value
    if (scanf("%d", &x) != 1) {
        free(arr);
        return 0;
    }
    
    // Calculate bounds
    int lower_bound_index = getLowerBound(arr, n, x);
    int upper_bound_index = getUpperBound(arr, n, x);
    
    // Print the results
    printf("%d %d\n", lower_bound_index, upper_bound_index);
    
    // Free allocated memory
    free(arr);
    return 0;
}