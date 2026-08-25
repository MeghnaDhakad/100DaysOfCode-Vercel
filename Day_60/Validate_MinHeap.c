#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Function to check if the given array represents a valid Min-Heap
bool isMinHeap(int arr[], int n) {
    // We only need to check nodes that have children.
    // The last parent node is located at index (n - 2) / 2.
    for (int i = 0; i <= (n - 2) / 2; i++) {
        
        // Check the left child (index 2*i + 1)
        if (2 * i + 1 < n && arr[i] > arr[2 * i + 1]) {
            return false;
        }
        
        // Check the right child (index 2*i + 2)
        if (2 * i + 2 < n && arr[i] > arr[2 * i + 2]) {
            return false;
        }
    }
    
    return true; // If no parent is greater than its children, it's a Min-Heap
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1 || n <= 0) {
        return 0;
    }

    // Allocate memory for the level-order traversal array
    int *arr = (int *)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Check if it satisfies the Min-Heap property and print the result
    if (isMinHeap(arr, n)) {
        printf("YES\n");
    } else {
        printf("NO\n");
    }

    // Clean up
    free(arr);

    return 0;
}