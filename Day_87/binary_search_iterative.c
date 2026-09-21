#include <stdio.h>
#include <stdlib.h>

// Comparator function used to sort the array before searching
int compare(const void *a, const void *b) {
    return (*(int *)a - *(int *)b);
}

// Iterative Binary Search Algorithm
int binarySearch(int arr[], int n, int target) {
    int left = 0;
    int right = n - 1;

    while (left <= right) {
        // Safely calculate mid to prevent integer overflow
        int mid = left + (right - left) / 2;

        // Check if target is present at mid
        if (arr[mid] == target) {
            return mid;
        }
        
        // If target is greater, ignore the left half
        if (arr[mid] < target) {
            left = mid + 1;
        } 
        // If target is smaller, ignore the right half
        else {
            right = mid - 1;
        }
    }

    // Target was not found
    return -1;
}

int main() {
    int n;
    
    // Read the size of the array
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    // Dynamically allocate memory for the array
    int *arr = (int *)malloc(n * sizeof(int));
    
    // Read the array elements
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    
    // Binary search requires the array to be sorted first.
    // This will sort the array and match your example output!
    qsort(arr, n, sizeof(int), compare);
    
    // Output the sorted array
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    // Check if a target value was provided to search for
    int target;
    if (scanf("%d", &target) == 1) {
        int index = binarySearch(arr, n, target);
        if (index != -1) {
            printf("Target %d found at index %d\n", target, index);
        } else {
            printf("Target %d not found\n", target);
        }
    }
    
    // Free allocated memory
    free(arr);
    return 0;
}