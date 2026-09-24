#include <stdio.h>
#include <stdlib.h>

// Helper function to check if all boards can be painted within max_time
int canPaint(int boards[], int n, int k, long long max_time) {
    int painters_needed = 1;
    long long current_time = 0;

    for (int i = 0; i < n; i++) {
        // If assigning this board exceeds the max time allowed for the current painter
        if (current_time + boards[i] > max_time) {
            painters_needed++;           // Bring in a new painter
            current_time = boards[i];    // Assign the current board to the new painter
            
            // If we require more painters than we have available, this time limit is impossible
            if (painters_needed > k) {
                return 0;
            }
        } else {
            current_time += boards[i];   // Assign the board to the current painter
        }
    }
    
    return 1; // Successfully painted all boards with <= k painters
}

int main() {
    int n, k;
    
    // Read number of boards and number of painters
    if (scanf("%d %d", &n, &k) != 2) return 0;
    
    // Dynamically allocate memory for the boards array
    int *boards = (int *)malloc(n * sizeof(int));
    
    long long sum = 0;
    int max_val = 0;
    
    // Read the lengths of the boards
    for (int i = 0; i < n; i++) {
        scanf("%d", &boards[i]);
        sum += boards[i];
        if (boards[i] > max_val) {
            max_val = boards[i];
        }
    }
    
    // Define the Binary Search space using long long to prevent integer overflow
    // The minimum possible time is the length of the longest single board
    long long left = max_val;
    // The maximum possible time is the sum of all boards (if 1 painter does everything)
    long long right = sum; 
    long long ans = -1;
    
    // Binary Search on the Answer
    while (left <= right) {
        long long mid = left + (right - left) / 2;
        
        if (canPaint(boards, n, k, mid)) {
            ans = mid;       // We can finish within this time, save it
            right = mid - 1; // Try to find an even smaller maximum time
        } else {
            left = mid + 1;  // Time is too tight, we need to allow more time
        }
    }
    
    // Output the minimum time required
    printf("%lld\n", ans);
    
    // Clean up allocated memory
    free(boards);
    return 0;
}