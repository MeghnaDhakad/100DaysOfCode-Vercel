#include <stdio.h>
#include <stdlib.h>

void bubbleSort(int arr[], int n) {
    // Outer loop dictates the number of passes
    for (int i = 0; i < n - 1; i++) {
        int swapped = 0; // Flag to detect if any swap happened
        
        // Inner loop compares adjacent elements.
        // The last 'i' elements are already sorted and in place, 
        // so we only need to iterate up to n - i - 1.
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j + 1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                
                swapped = 1; // Mark that a swap occurred
            }
        }
        
        // If no elements were swapped in this pass, the array is already sorted!
        if (swapped == 0) {
            break;
        }
    }
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    // Dynamically allocate memory for the array
    int *arr = (int *)malloc(n * sizeof(int));
    
    // Read the array elements
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    
    // Sort the array using Bubble Sort
    bubbleSort(arr, n);
    
    // Output the sorted array
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    // Free allocated memory
    free(arr);
    return 0;
}