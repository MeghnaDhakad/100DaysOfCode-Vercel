#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// Helper DFS function
bool dfs(int node, int** adj, int* adjSize, bool* visited, bool* recStack) {
    visited[node] = true;
    recStack[node] = true; // Add to current path

    // Traverse all neighbors of the current node
    for (int i = 0; i < adjSize[node]; i++) {
        int neighbor = adj[node][i];
        
        // If not visited, recurse deeply
        if (!visited[neighbor]) {
            if (dfs(neighbor, adj, adjSize, visited, recStack)) {
                return true;
            }
        } 
        // If visited AND in the current recursion stack, a cycle is detected
        else if (recStack[neighbor]) {
            return true;
        }
    }

    // Backtrack: remove the node from the current path
    recStack[node] = false;
    return false;
}

// Main function to detect cycle
bool isCyclic(int V, int** adj, int* adjSize) {
    // Allocate memory and initialize to false (calloc handles the 0 initialization)
    bool* visited = (bool*)calloc(V, sizeof(bool));
    bool* recStack = (bool*)calloc(V, sizeof(bool));

    // Check every node (handles disconnected graphs)
    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            if (dfs(i, adj, adjSize, visited, recStack)) {
                printf("YES\n"); // Output YES as requested
                free(visited);
                free(recStack);
                return true;
            }
        }
    }
    
    // Free dynamically allocated memory
    free(visited);
    free(recStack);
    
    return false;
}
