#include <stdio.h>
#include <stdlib.h>

// --------------------------------------------------------
// 1. INTERVAL STRUCTURE & SORTING LOGIC
// --------------------------------------------------------
typedef struct {
    int start;
    int end;
} Interval;

// Comparator to sort intervals by start time for qsort
int compareIntervals(const void* a, const void* b) {
    return ((Interval*)a)->start - ((Interval*)b)->start;
}

// --------------------------------------------------------
// 2. CORE ALGORITHM
// --------------------------------------------------------
// Returns a dynamically allocated array of merged intervals.
// Modifies 'returnSize' to indicate how many intervals are in the result.
Interval* mergeIntervals(Interval* intervals, int n, int* returnSize) {
    if (n <= 0) {
        *returnSize = 0;
        return NULL;
    }

    // Step 1: Sort the intervals by start time
    qsort(intervals, n, sizeof(Interval), compareIntervals);

    // Step 2: Allocate memory for the result (worst case is 'n' intervals)
    Interval* merged = (Interval*)malloc(n * sizeof(Interval));
    int count = 0; // Index of the current active interval in 'merged'

    // Initialize with the first interval
    merged[0] = intervals[0];

    // Step 3: Iterate and merge
    for (int i = 1; i < n; i++) {
        // If the current interval overlaps with the active merged interval
        if (intervals[i].start <= merged[count].end) {
            // Update the end time to the maximum of both
            if (intervals[i].end > merged[count].end) {
                merged[count].end = intervals[i].end;
            }
        } else {
            // No overlap: move to the next slot and make this the new active interval
            count++;
            merged[count] = intervals[i];
        }
    }

    // The number of merged intervals is index + 1
    *returnSize = count + 1;
    return merged;
}

// --------------------------------------------------------
// 3. MAIN EXECUTION
// --------------------------------------------------------
int main() {
    int n;
    
    // Read the number of intervals
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    // Dynamically allocate the intervals array
    Interval* intervals = (Interval*)malloc(n * sizeof(Interval));
    
    // Read the intervals (start end)
    for (int i = 0; i < n; i++) {
        scanf("%d %d", &intervals[i].start, &intervals[i].end);
    }
    
    int mergedSize;
    Interval* merged = mergeIntervals(intervals, n, &mergedSize);
    
    // Output the merged intervals
    printf("[\n");
    for (int i = 0; i < mergedSize; i++) {
        printf("  [%d, %d]%s\n", merged[i].start, merged[i].end, (i < mergedSize - 1) ? "," : "");
    }
    printf("]\n");
    
    // Clean up memory
    free(intervals);
    free(merged);
    
    return 0;
}