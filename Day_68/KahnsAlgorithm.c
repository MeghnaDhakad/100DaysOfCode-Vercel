#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// --- Data Structures ---

// Structure for an adjacency list node
struct Node {
    int dest;
    struct Node* next;
};

// Structure for the graph
struct Graph {
    int numVertices;
    struct Node** adjList;
    int* indegree; // Array to keep track of in-degrees
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
    graph->indegree = (int*)calloc(vertices, sizeof(int)); // Initializes all in-degrees to 0

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
    
    // Increment the in-degree of the destination node
    graph->indegree[dest]++;
}

// --- Kahn's Algorithm ---

void printTopologicalSort(struct Graph* graph) {
    int V = graph->numVertices;
    
    // Simple Array-based Queue
    int* queue = (int*)malloc(V * sizeof(int));
    int front = 0, rear = 0;

    // Array to store the final topological order
    int* order = (int*)malloc(V * sizeof(int));
    int orderIndex = 0;

    // 1. Enqueue all vertices that have an in-degree of 0
    for (int i = 0; i < V; i++) {
        if (graph->indegree[i] == 0) {
            queue[rear++] = i;
        }
    }

    // 2. Process the queue
    while (front < rear) {
        // Dequeue a vertex
        int current = queue[front++];
        order[orderIndex++] = current;

        // Iterate through all neighbors of the dequeued vertex
        struct Node* temp = graph->adjList[current];
        while (temp != NULL) {
            int neighbor = temp->dest;
            
            // Decrease the in-degree of the neighbor by 1
            graph->indegree[neighbor]--;

            // If in-degree becomes 0, add it to the queue
            if (graph->indegree[neighbor] == 0) {
                queue[rear++] = neighbor;
            }
            temp = temp->next;
        }
    }

    // 3. Check for cycles
    // If orderIndex != V, it means some nodes couldn't be processed due to a cycle
    if (orderIndex == V) {
        printf("Topological Order: ");
        for (int i = 0; i < V; i++) {
            printf("%d ", order[i]);
        }
        printf("\n");
    } else {
        printf("Error: Graph contains a cycle! Topological sort is impossible.\n");
    }

    // Clean up local arrays
    free(queue);
    free(order);
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
    free(graph->indegree);
    free(graph->adjList);
    free(graph);
}

// --- Main Method ---

int main() {
    // Test Case 1: Valid DAG
    // Graph: 5->0, 5->2, 4->0, 4->1, 2->3, 3->1
    struct Graph* graph1 = createGraph(6);
    addEdge(graph1, 5, 2);
    addEdge(graph1, 5, 0);
    addEdge(graph1, 4, 0);
    addEdge(graph1, 4, 1);
    addEdge(graph1, 2, 3);
    addEdge(graph1, 3, 1);

    printf("Test Case 1 (Valid DAG):\n");
    printTopologicalSort(graph1); 
    freeGraph(graph1);
    
    printf("\n-------------------------\n\n");

    // Test Case 2: Graph with a Cycle
    // Graph: 0->1, 1->2, 2->0 (Cycle)
    struct Graph* graph2 = createGraph(3);
    addEdge(graph2, 0, 1);
    addEdge(graph2, 1, 2);
    addEdge(graph2, 2, 0);

    printf("Test Case 2 (Cycle Detection):\n");
    printTopologicalSort(graph2); 
    freeGraph(graph2);

    return 0;
}