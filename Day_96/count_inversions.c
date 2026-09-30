#include <stdio.h>
#include <stdlib.h>

// Merges two sorted halves and counts inversions crossing the halves
long long mergeAndCount(int arr[], int temp[], int left, int mid, int right) {
    int i = left;    // Starting index for left subarray
    int j = mid + 1; // Starting index for right subarray
    int k = left;    // Starting index to be sorted
    long long inv_count = 0;

    // Conditions are checked to ensure that i and j don't exceed their subarray limits
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            // arr[i] > arr[j] means all elements from arr[i] to arr[mid] are > arr[j]
            // because the left subarray is already sorted.
            temp[k++] = arr[j++];
            inv_count += (mid - i + 1); // Core logic for counting inversions
        }
    }

    // Copy the remaining elements of left subarray (if any)
    while (i <= mid) {
        temp[k++] = arr[i++];
    }

    // Copy the remaining elements of right subarray (if any)
    while (j <= right) {
        temp[k++] = arr[j++];
    }

    // Copy the sorted subarray back into the original array
    for (i = left; i <= right; i++) {
        arr[i] = temp[i];
    }

    return inv_count;
}

// Recursive function to sort the array and count inversions
long long mergeSortAndCount(int arr[], int temp[], int left, int right) {
    long long inv_count = 0;
    
    if (left < right) {
        int mid = left + (right - left) / 2;

        // Total inversions = inversions in left half + right half + cross inversions
        inv_count += mergeSortAndCount(arr, temp, left, mid);
        inv_count += mergeSortAndCount(arr, temp, mid + 1, right);
        inv_count += mergeAndCount(arr, temp, left, mid, right);
    }
    
    return inv_count;
}

int main() {
    int n;
    
    // Read the size of the array
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    // Dynamically allocate memory for the array and the temporary array
    int *arr = (int *)malloc(n * sizeof(int));
    int *temp = (int *)malloc(n * sizeof(int));
    
    // Read the array elements
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    
    // Calculate total inversions
    long long total_inversions = mergeSortAndCount(arr, temp, 0, n - 1);
    
    // Output the result
    printf("Total Inversions: %lld\n", total_inversions);
    
    // Free allocated memory
    free(arr);
    free(temp);
    
    return 0;
}