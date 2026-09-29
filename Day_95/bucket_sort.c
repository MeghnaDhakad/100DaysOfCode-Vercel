#include <stdio.h>
#include <stdlib.h>

// Node structure for the Linked List (Bucket)
struct Node {
    float data;
    struct Node* next;
};

// Helper function to insert a value into a linked list in strictly sorted order
// (This is exactly the Insertion Sort List logic you learned earlier!)
void insertSorted(struct Node** head_ref, float val) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = val;
    newNode->next = NULL;

    // If the list is empty or the new value is smaller than the head
    if (*head_ref == NULL || (*head_ref)->data >= val) {
        newNode->next = *head_ref;
        *head_ref = newNode;
    } else {
        // Traverse to find the correct insertion point
        struct Node* current = *head_ref;
        while (current->next != NULL && current->next->data < val) {
            current = current->next;
        }
        newNode->next = current->next;
        current->next = newNode;
    }
}

// Function to sort an array using Bucket Sort
void bucketSort(float arr[], int n) {
    if (n <= 0) return;

    // Step 1: Create 'n' empty buckets (Array of Node pointers)
    struct Node** buckets = (struct Node**)calloc(n, sizeof(struct Node*));
    if (buckets == NULL) return;

    // Step 2: Distribute elements into buckets
    for (int i = 0; i < n; i++) {
        // Calculate the bucket index (n * arr[i] guarantees an index from 0 to n-1)
        int bucketIndex = n * arr[i];
        
        // Edge case safety constraint
        if (bucketIndex >= n) bucketIndex = n - 1;
        
        // Insert into the bucket while maintaining sorted order
        insertSorted(&buckets[bucketIndex], arr[i]);
    }

    // Step 3: Concatenate all sorted buckets back into the original array
    int index = 0;
    for (int i = 0; i < n; i++) {
        struct Node* current = buckets[i];
        while (current != NULL) {
            arr[index++] = current->data;
            
            // Keep a reference to free the memory
            struct Node* temp = current;
            current = current->next;
            free(temp);
        }
    }
    
    // Clean up the bucket array
    free(buckets);
}

int main() {
    int n;
    
    // Read the size of the array
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    // Dynamically allocate memory for the array of floats
    float *arr = (float *)malloc(n * sizeof(float));
    if (arr == NULL) return 1;
    
    // Read the floating point array elements [0, 1)
    for (int i = 0; i < n; i++) {
        scanf("%f", &arr[i]);
    }
    
    // Sort the array using Bucket Sort
    bucketSort(arr, n);
    
    // Output the sorted array
    for (int i = 0; i < n; i++) {
        printf("%.4f ", arr[i]);
    }
    printf("\n");
    
    // Free allocated memory
    free(arr);
    return 0;
}