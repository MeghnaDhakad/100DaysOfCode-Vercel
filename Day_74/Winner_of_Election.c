#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Comparator function for qsort to sort strings lexicographically
int compareStrings(const void *a, const void *b) {
    // a and b are pointers to the string pointers in the array
    return strcmp(*(const char **)a, *(const char **)b);
}

int main() {
    int n;
    
    // Read number of votes
    if (scanf("%d", &n) != 1 || n <= 0) return 0;

    // Allocate an array of string pointers
    char **votes = (char **)malloc(n * sizeof(char *));
    for (int i = 0; i < n; i++) {
        // Assume maximum name length is 50 characters
        votes[i] = (char *)malloc(55 * sizeof(char)); 
        scanf("%54s", votes[i]);
    }

    // Sort the array of names alphabetically
    // Time Complexity: O(N log N)
    qsort(votes, n, sizeof(char *), compareStrings);

    int max_votes = 0;
    char winner[55] = "";

    int current_count = 1;
    
    // Iterate through the sorted array to count votes
    for (int i = 1; i < n; i++) {
        if (strcmp(votes[i], votes[i - 1]) == 0) {
            // Same candidate, increment vote count
            current_count++;
        } else {
            // Candidate changed, check if the previous candidate won
            // We strictly use '>' so that in case of a tie, the 
            // lexicographically smaller one (which comes first) is kept.
            if (current_count > max_votes) {
                max_votes = current_count;
                strcpy(winner, votes[i - 1]);
            }
            // Reset count for the new candidate
            current_count = 1;
        }
    }

    // Final check for the last candidate in the array
    if (current_count > max_votes) {
        max_votes = current_count;
        strcpy(winner, votes[n - 1]);
    }

    // Output the result
    printf("%s %d\n", winner, max_votes);

    // Free allocated memory
    for (int i = 0; i < n; i++) {
        free(votes[i]);
    }
    free(votes);

    return 0;
}