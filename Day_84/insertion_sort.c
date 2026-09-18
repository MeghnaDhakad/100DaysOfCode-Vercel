#include <stdio.h>
#include <stdlib.h>

void insertionSort(int arr[], int n) {
    // We start from the second element (index 1) because a single 
    // element array (index 0) is already trivially sorted.
    for (int i = 1; i < n; i++) {
        int key = arr[i];
        int j = i - 1;

        // Compare the key with elements to its left.
        // Shift elements that are greater than the key one position to the right.
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        
        // Place the key at its correct position in the sorted part of the array
        arr[j + 1] = key;
    }
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
    
    // Sort the array using Insertion Sort
    insertionSort(arr, n);
    
    // Output the sorted array
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    // Free allocated memory
    free(arr);
    return 0;
}