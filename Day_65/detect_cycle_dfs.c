#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Structure to represent a node in the linked list
struct Node {
    int vertex;
    struct Node* next;
};

// Helper function to create a new linked list node
struct Node* createNode(int v) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->vertex = v;
    newNode->next = NULL;
    return newNode;
}

// Function to add an undirected edge to the adjacency list
void addEdge(struct Node** adj, int u, int v) {
    // Add edge from u to v
    struct Node* newNode = createNode(v);
    newNode->next = adj[u];
    adj[u] = newNode;

    // Add edge from v to u
    newNode = createNode(u);
    newNode->next = adj[v];
    adj[v] = newNode;
}

// DFS function to detect a cycle
bool detectCycleDFS(struct Node** adj, int* visited, int current, int parent) {
    // Mark the current node as visited
    visited[current] = 1;
    
    // Traverse all neighbors of the current node
    struct Node* temp = adj[current];
    while (temp != NULL) {
        int neighbor = temp->vertex;
        
        // If the neighbor is not visited, recursively call DFS
        if (!visited[neighbor]) {
            // If the recursive call finds a cycle, propagate it up
            if (detectCycleDFS(adj, visited, neighbor, current)) {
                return true;
            }
        } 
        // If the neighbor is already visited AND it is NOT the parent we just came from
        else if (neighbor != parent) {
            return true; // Cycle detected!
        }
        
        temp = temp->next;
    }
    
    return false;
}

int main() {
    int n, m;

    // Read number of vertices (n) and edges (m)
    if (scanf("%d %d", &n, &m) != 2) {
        return 0;
    }

    // Create the Adjacency List (array of Node pointers)
    struct Node** adj = (struct Node**)malloc(n * sizeof(struct Node*));
    for (int i = 0; i < n; i++) {
        adj[i] = NULL;
    }

    // Read all m edges
    for (int i = 0; i < m; i++) {
        int u, v;
        scanf("%d %d", &u, &v);
        addEdge(adj, u, v); // Using 0-indexed vertices
    }

    // Create the visited array initialized to 0 (false)
    int* visited = (int*)calloc(n, sizeof(int));
    bool hasCycle = false;

    // Iterate through all vertices to handle disconnected components
    for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            // Start DFS with no parent (-1)
            if (detectCycleDFS(adj, visited, i, -1)) {
                hasCycle = true;
                break; // Stop early if a cycle is found
            }
        }
    }

    // Print the final result
    if (hasCycle) {
        printf("YES\n");
    } else {
        printf("NO\n");
    }

    // --- Clean up allocated memory ---
    for (int i = 0; i < n; i++) {
        struct Node* temp = adj[i];
        while (temp != NULL) {
            struct Node* toFree = temp;
            temp = temp->next;
            free(toFree);
        }
    }
    free(adj);
    free(visited);

    return 0;
}