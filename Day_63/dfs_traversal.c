#include <stdio.h>
#include <stdlib.h>

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
    // Add edge from u to v (Insert at the head of u's list)
    struct Node* newNode = createNode(v);
    newNode->next = adj[u];
    adj[u] = newNode;

    // Add edge from v to u (Insert at the head of v's list)
    newNode = createNode(u);
    newNode->next = adj[v];
    adj[v] = newNode;
}

// Recursive Depth-First Search function
void dfs(struct Node** adj, int* visited, int current) {
    // 1. Mark the current node as visited
    visited[current] = 1;
    
    // 2. Process the current node (print it)
    printf("%d ", current);
    
    // 3. Visit all unvisited neighbors
    struct Node* temp = adj[current];
    while (temp != NULL) {
        int neighbor = temp->vertex;
        if (!visited[neighbor]) {
            dfs(adj, visited, neighbor);
        }
        temp = temp->next;
    }
}

int main() {
    int n, m;

    // Read number of vertices (n) and edges (m)
    if (scanf("%d %d", &n, &m) != 2) {
        return 0;
    }

    // Create the Adjacency List (array of Node pointers)
    struct Node** adj = (struct Node**)malloc(n * sizeof(struct Node*));
    
    // Create the visited array initialized to 0 (false)
    int* visited = (int*)calloc(n, sizeof(int));
    
    // Initialize all adjacency list heads to NULL
    for (int i = 0; i < n; i++) {
        adj[i] = NULL;
    }

    // Read all m edges
    for (int i = 0; i < m; i++) {
        int u, v;
        scanf("%d %d", &u, &v);
        addEdge(adj, u, v);
    }

    // Read the starting vertex (s)
    int s;
    scanf("%d", &s);

    printf("DFS Traversal Order: ");
    
    // Initiate the recursive DFS from the starting vertex
    dfs(adj, visited, s);
    
    printf("\n");

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