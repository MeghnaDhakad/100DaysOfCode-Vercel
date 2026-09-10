#include <stdio.h>
#include <stdlib.h>

// Adjacency list node structure
typedef struct Node {
    int dest;
    struct Node* next;
} Node;

// Function to add an undirected edge
void addEdge(Node** head, int u, int v) {
    // Add edge from u to v
    Node* newNode1 = (Node*)malloc(sizeof(Node));
    newNode1->dest = v;
    newNode1->next = head[u];
    head[u] = newNode1;

    // Add edge from v to u
    Node* newNode2 = (Node*)malloc(sizeof(Node));
    newNode2->dest = u;
    newNode2->next = head[v];
    head[v] = newNode2;
}

// Depth-First Search to visit all nodes in a component
void dfs(int u, Node** head, int* visited) {
    visited[u] = 1; // Mark current node as visited
    
    Node* temp = head[u];
    while (temp != NULL) {
        int v = temp->dest;
        if (!visited[v]) {
            dfs(v, head, visited);
        }
        temp = temp->next;
    }
}

int main() {
    int n, m;
    
    // Read number of vertices (n) and edges (m)
    if (scanf("%d %d", &n, &m) != 2) return 0;

    // Allocate memory for adjacency list and visited array (1-indexed)
    Node** head = (Node**)calloc(n + 1, sizeof(Node*));
    int* visited = (int*)calloc(n + 1, sizeof(int));

    // Read all m edges
    for (int i = 0; i < m; i++) {
        int u, v;
        scanf("%d %d", &u, &v);
        addEdge(head, u, v);
    }

    int components = 0;

    // Iterate through all nodes (1 to n)
    for (int i = 1; i <= n; i++) {
        // If a node hasn't been visited, we found a new component
        if (!visited[i]) {
            components++;
            dfs(i, head, visited); // Explore and mark the entire component
        }
    }

    // Output the total number of connected components
    printf("%d\n", components);

    // Free allocated memory 
    for (int i = 1; i <= n; i++) {
        Node* temp = head[i];
        while (temp != NULL) {
            Node* toFree = temp;
            temp = temp->next;
            free(toFree);
        }
    }
    free(head);
    free(visited);

    return 0;
}