#include <stdio.h>
#include <stdlib.h>

int main() {
    int n, m;

    // Read number of vertices (n) and edges (m)
    if (scanf("%d %d", &n, &m) != 2) {
        return 0;
    }

    // Dynamically allocate an n x n adjacency matrix.
    // We use calloc to automatically initialize all values to 0.
    int **matrix = (int **)malloc(n * sizeof(int *));
    for (int i = 0; i < n; i++) {
        matrix[i] = (int *)calloc(n, sizeof(int));
    }

    // Toggle this flag based on your graph type
    // 0 = Undirected graph, 1 = Directed graph
    int isDirected = 0; 

    // Read all m edges
    for (int i = 0; i < m; i++) {
        int u, v;
        scanf("%d %d", &u, &v);

        // Add edge from u to v
        // Note: Assuming 0-indexed vertices (0 to n-1). 
        // If your input is 1-indexed (1 to n), use matrix[u-1][v-1] = 1
        matrix[u][v] = 1;

        // If the graph is undirected, also add the reverse edge from v to u
        if (!isDirected) {
            matrix[v][u] = 1;
        }
    }

    // Print the adjacency matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            printf("%d ", matrix[i][j]);
        }
        printf("\n");
    }

    // Clean up allocated memory
    for (int i = 0; i < n; i++) {
        free(matrix[i]);
    }
    free(matrix);

    return 0;
}