#include <stdio.h>
#include <stdlib.h>

// A utility function to swap two elements
void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

/* 
 * This function takes the last element as pivot, places the pivot element at its 
 * correct position in the sorted array, and places all smaller elements to the left 
 * of the pivot and all greater elements to the right of the pivot.
 */
int partition(int arr[], int low, int high) {
    int pivot = arr[high]; // Choosing the last element as the pivot
    int i = (low - 1);     // Index of the smaller element

    for (int j = low; j <= high - 1; j++) {
        // If the current element is smaller than the pivot
        if (arr[j] < pivot) {
            i++; // Increment index of the smaller element
            swap(&arr[i], &arr[j]);
        }
    }
    
    // Place the pivot strictly after the smaller elements
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

// The main function that implements Quick Sort
void quickSort(int arr[], int low, int high) {
    if (low < high) {
        // pi is the partitioning index; arr[pi] is now at its final sorted position
        int pi = partition(arr, low, high);

        // Recursively sort elements before partition and after partition
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
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
    
    // Sort the array using Quick Sort
    quickSort(arr, 0, n - 1);
    
    // Output the sorted array
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    // Free allocated memory
    free(arr);
    return 0;
}