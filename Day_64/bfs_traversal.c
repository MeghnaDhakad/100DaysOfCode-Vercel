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

// Breadth-First Search function
void bfs(struct Node** adj, int n, int startNode) {
    // Create the visited array initialized to 0 (false)
    int* visited = (int*)calloc(n, sizeof(int));
    
    // Create a simple array-based queue of size n
    int* queue = (int*)malloc(n * sizeof(int));
    int front = 0;
    int rear = 0;

    // 1. Initialize the start node
    visited[startNode] = 1;
    queue[rear++] = startNode; // Enqueue

    printf("BFS Traversal Order: ");

    // 2. Loop until the queue is empty (front catches up to rear)
    while (front < rear) {
        // Dequeue the front element
        int current = queue[front++];
        printf("%d ", current);

        // 3. Visit all unvisited neighbors of the current node
        struct Node* temp = adj[current];
        while (temp != NULL) {
            int neighbor = temp->vertex;
            if (!visited[neighbor]) {
                // Mark as visited immediately to prevent duplicate additions
                visited[neighbor] = 1;
                // Enqueue the neighbor
                queue[rear++] = neighbor; 
            }
            temp = temp->next;
        }
    }
    printf("\n");

    // Free the memory used by the traversal
    free(visited);
    free(queue);
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
        addEdge(adj, u, v);
    }

    // Read the starting vertex (s)
    int s;
    scanf("%d", &s);

    // Execute the BFS traversal
    bfs(adj, n, s);

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

    return 0;
}