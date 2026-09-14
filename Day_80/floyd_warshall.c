#include <stdio.h>
#include <stdlib.h>

// Define a large value to represent Infinity
#define INF 1000000000 

int main() {
    int n;
    
    // Read the number of vertices
    if (scanf("%d", &n) != 1) return 0;

    // Dynamically allocate memory for the n x n distance matrix
    int** dist = (int**)malloc(n * sizeof(int*));
    for (int i = 0; i < n; i++) {
        dist[i] = (int*)malloc(n * sizeof(int));
    }

    // Read the adjacency matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            scanf("%d", &dist[i][j]);
            
            // The problem uses -1 to represent 'no edge' between distinct vertices
            // Convert it to INF so we can safely use the min() logic
            if (dist[i][j] == -1 && i != j) {
                dist[i][j] = INF;
            }
        }
    }

    // Floyd-Warshall Algorithm
    // k represents the intermediate vertex we are allowed to route through
    for (int k = 0; k < n; k++) {
        // i represents the source vertex
        for (int i = 0; i < n; i++) {
            // j represents the destination vertex
            for (int j = 0; j < n; j++) {
                
                // Only attempt to relax the edge if both i->k and k->j are reachable
                if (dist[i][k] != INF && dist[k][j] != INF) {
                    
                    // If the path routing through 'k' is shorter, update dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }

    // Print the final shortest distance matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (dist[i][j] == INF) {
                printf("-1 "); // Convert unreachable paths back to -1 for output
            } else {
                printf("%d ", dist[i][j]);
            }
        }
        printf("\n");
    }

    // Free the dynamically allocated memory
    for (int i = 0; i < n; i++) {
        free(dist[i]);
    }
    free(dist);

    return 0;
}