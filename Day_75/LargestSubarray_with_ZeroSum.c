#include <stdio.h>
#include <stdlib.h>

// Structure to hold the prefix sum and its original index
typedef struct {
    long long sum;
    int index;
} Pair;

// Comparator function for qsort
int compare(const void* a, const void* b) {
    Pair* p1 = (Pair*)a;
    Pair* p2 = (Pair*)b;
    
    // Sort primarily by sum (ascending)
    if (p1->sum < p2->sum) return -1;
    if (p1->sum > p2->sum) return 1;
    
    // If sums are equal, sort by index (ascending)
    return p1->index - p2->index;
}

int main() {
    int arr[100005];
    int n = 0;
    
    // Read integers from standard input until EOF
    while (scanf("%d", &arr[n]) == 1) {
        n++;
    }
    
    if (n == 0) {
        printf("0\n");
        return 0;
    }

    // Allocate memory for prefix sums. Size is n + 1 to handle cases 
    // where the subarray starts from the very first element (index 0).
    Pair* pref = (Pair*)malloc((n + 1) * sizeof(Pair));
    
    // Base case: a sum of 0 at an imaginary index -1 before the array starts
    pref[0].sum = 0;
    pref[0].index = -1;
    
    long long current_sum = 0;
    for (int i = 0; i < n; i++) {
        current_sum += arr[i];
        pref[i + 1].sum = current_sum;
        pref[i + 1].index = i;
    }

    // Sort the prefix sums array
    qsort(pref, n + 1, sizeof(Pair), compare);

    int max_len = 0;
    int current_min_idx = pref[0].index;
    
    // Iterate to find the maximum distance between identical sums
    for (int i = 1; i <= n; i++) {
        if (pref[i].sum == pref[i - 1].sum) {
            // Because they are sorted by index as a secondary rule,
            // current_min_idx remains the earliest index we saw for this sum.
            int len = pref[i].index - current_min_idx;
            if (len > max_len) {
                max_len = len;
            }
        } else {
            // Sum changed, reset the earliest index marker
            current_min_idx = pref[i].index;
        }
    }

    printf("%d\n", max_len);

    free(pref);
    return 0;
}