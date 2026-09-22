#include <stdio.h>
#include <stdlib.h>

// Comparator for sorting the stalls
int compare(const void *a, const void *b) {
    return (*(int *)a - *(int *)b);
}

// Helper function to check if we can place k cows with at least 'dist' between them
int canPlaceCows(int stalls[], int n, int k, int dist) {
    int count = 1;             // Place the first cow...
    int last_pos = stalls[0];  // ...in the very first stall

    for (int i = 1; i < n; i++) {
        // If the current stall is far enough from the last placed cow
        if (stalls[i] - last_pos >= dist) {
            count++;                // Place another cow
            last_pos = stalls[i];   // Update the last placed position
            
            // If we successfully placed all k cows, this distance is valid
            if (count == k) {
                return 1;
            }
        }
    }
    
    // We ran out of stalls before placing all k cows
    return 0; 
}

int main() {
    int n, k;
    
    // Read number of stalls and cows
    if (scanf("%d %d", &n, &k) != 2) return 0;
    
    // Allocate memory for stall positions
    int *stalls = (int *)malloc(n * sizeof(int));
    
    // Read stall positions
    for (int i = 0; i < n; i++) {
        scanf("%d", &stalls[i]);
    }
    
    // Step 1: Sort the stalls
    qsort(stalls, n, sizeof(int), compare);
    
    // Step 2: Define Binary Search space
    int left = 1;
    int right = stalls[n - 1] - stalls[0];
    int ans = -1;
    
    // Step 3: Binary Search on the answer
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        if (canPlaceCows(stalls, n, k, mid)) {
            ans = mid;       // This distance works, save it
            left = mid + 1;  // Try to find a LARGER minimum distance
        } else {
            right = mid - 1; // Distance is too large, reduce it
        }
    }
    
    // Print the maximum possible minimum distance
    printf("%d\n", ans);
    
    // Clean up
    free(stalls);
    return 0;
}