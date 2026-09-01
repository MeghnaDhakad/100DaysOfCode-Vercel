#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// 1. Structure for an adjacency list node
struct Node {
    int dest;
    struct Node* next;
};

// 2. Structure for the graph
struct Graph {
    int numVertices;
    struct Node** adjList;
};

// Function to create a new adjacency list node
struct Node* createNode(int dest) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->dest = dest;
    newNode->next = NULL;
    return newNode;
}

// Function to create a graph with V vertices
struct Graph* createGraph(int vertices) {
    struct Graph* graph = (struct Graph*)malloc(sizeof(struct Graph));
    graph->numVertices = vertices;
    graph->adjList = (struct Node**)malloc(vertices * sizeof(struct Node*));

    for (int i = 0; i < vertices; i++) {
        graph->adjList[i] = NULL;
    }
    return graph;
}

// Function to add a directed edge to the graph
void addEdge(struct Graph* graph, int src, int dest) {
    struct Node* newNode = createNode(dest);
    // Insert at the beginning of the list for O(1) insertion
    newNode->next = graph->adjList[src];
    graph->adjList[src] = newNode;
}

// DFS Helper function
void dfs(struct Graph* graph, int vertex, bool visited[], int stack[], int* top) {
    // Mark the current node as visited
    visited[vertex] = true;

    // Traverse all adjacent vertices
    struct Node* temp = graph->adjList[vertex];
    while (temp != NULL) {
        int connectedVertex = temp->dest;
        if (!visited[connectedVertex]) {
            dfs(graph, connectedVertex, visited, stack, top);
        }
        temp = temp->next;
    }

    // Once all neighbors are explored, push the node to the stack
    stack[++(*top)] = vertex;
}

// Main function to perform topological sort
void printTopologicalSort(struct Graph* graph) {
    int V = graph->numVertices;
    bool* visited = (bool*)calloc(V, sizeof(bool)); // Initializes to false
    int* stack = (int*)malloc(V * sizeof(int));
    int top = -1;

    // Perform DFS for every node
    for (int i = 0; i < V; i++) {
        if (!visited[i]) {
            dfs(graph, i, visited, stack, &top);
        }
    }

    // Print the topological order by popping from the stack
    printf("Topological Order: ");
    while (top >= 0) {
        printf("%d ", stack[top--]);
    }
    printf("\n");

    // Clean up allocated memory
    free(visited);
    free(stack);
}

// Helper to free graph memory
void freeGraph(struct Graph* graph) {
    for (int i = 0; i < graph->numVertices; i++) {
        struct Node* temp = graph->adjList[i];
        while (temp != NULL) {
            struct Node* toFree = temp;
            temp = temp->next;
            free(toFree);
        }
    }
    free(graph->adjList);
    free(graph);
}

// Main method to test the code
int main() {
    // Test Case 1: Simple linear chain (0 -> 1 -> 2)
    struct Graph* graph1 = createGraph(3);
    addEdge(graph1, 0, 1);
    addEdge(graph1, 1, 2);
    
    printf("Test Case 1:\n");
    printTopologicalSort(graph1); 
    // Expected Output: 0 1 2
    freeGraph(graph1);

    // Test Case 2: Complex DAG with multiple starting points
    // 5 -> 2, 5 -> 0, 4 -> 0, 4 -> 1, 2 -> 3, 3 -> 1
    struct Graph* graph2 = createGraph(6);
    // Note: Because addEdge inserts at the head of the linked list,
    // adding (5,2) then (5,0) will result in visiting 0 before 2.
    // The topological order remains valid but might look slightly different 
    // depending on the edge insertion order.
    addEdge(graph2, 5, 2);
    addEdge(graph2, 5, 0);
    addEdge(graph2, 4, 0);
    addEdge(graph2, 4, 1);
    addEdge(graph2, 2, 3);
    addEdge(graph2, 3, 1);

    printf("\nTest Case 2:\n");
    printTopologicalSort(graph2); 
    // Expected Output will be a valid topological sort (e.g., 5 4 2 3 1 0 or 4 5 0 2 3 1)
    freeGraph(graph2);

    return 0;
}