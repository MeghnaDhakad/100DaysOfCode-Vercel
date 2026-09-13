#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

// Adjacency list node structure
typedef struct Node {
    int dest;
    int weight;
    struct Node* next;
} Node;

// Function to add a directed edge
void addEdge(Node** head, int u, int v, int w) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->dest = v;
    newNode->weight = w;
    newNode->next = head[u];
    head[u] = newNode;
}

// Min-Heap node to store (vertex, distance)
typedef struct {
    int vertex;
    int dist;
} HeapNode;

// Min-Heap structure
typedef struct {
    HeapNode* array;
    int size;
    int capacity;
} MinHeap;

void swap(HeapNode* a, HeapNode* b) {
    HeapNode temp = *a;
    *a = *b;
    *b = temp;
}

// Heapify down
void minHeapify(MinHeap* minHeap, int idx) {
    int smallest = idx;
    int left = 2 * idx + 1;
    int right = 2 * idx + 2;

    if (left < minHeap->size && minHeap->array[left].dist < minHeap->array[smallest].dist)
        smallest = left;

    if (right < minHeap->size && minHeap->array[right].dist < minHeap->Here is the complete, runnable C code to solve the Single-Source Shortest Path problem using **Dijkstra's Algorithm** with a Min-Heap (Priority Queue).

*Note: Based on your sample output where the distance to vertex 4 is `9`, this implies the graph is **directed**. If it were undirected, the path `1 -> 2 -> 3 -> 5 -> 4` would yield a shorter distance of `7`. Therefore, this implementation processes directed edges.*

### 1. Suggested File Name
Save this file in VS Code as: **`dijkstra.c`**

### 2. C Implementation

```c
#include <stdio.h>
#include <stdlib.h>

#define INF 1000000000 // 1e9 to represent infinity

// Adjacency list node structure
typedef struct Node {
    int dest;
    int weight;
    struct Node* next;
} Node;

// Function to add a directed edge
void addEdge(Node** head, int u, int v, int w) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->dest = v;
    newNode->weight = w;
    newNode->next = head[u];
    head[u] = newNode;
}

// Min-Heap node
typedef struct {
    int vertex;
    int distance;
} HeapNode;

// Min-Heap structure
typedef struct {
    HeapNode* array;
    int size;
    int capacity;
} MinHeap;

void swap(HeapNode* a, HeapNode* b) {
    HeapNode temp = *a;
    *a = *b;
    *b = temp;
}

// Heapify down
void minHeapify(MinHeap* minHeap, int idx) {
    int smallest = idx;
    int left = 2 * idx + 1;
    int right = 2 * idx + 2;

    if (left < minHeap->size && minHeap->array[left].distance < minHeap->array[smallest].distance)
        smallest = left;

    if (right < minHeap->size && minHeap->array[right].distance < minHeap->array[smallest].distance)
        smallest = right;

    if (smallest != idx) {
        swap(&minHeap->array[smallest], &minHeap->array[idx]);
        minHeapify(minHeap, smallest);
    }
}

// Extract the minimum element from the heap
HeapNode extractMin(MinHeap* minHeap) {
    HeapNode root = minHeap->array[0];
    minHeap->array[0] = minHeap->array[minHeap->size - 1];
    minHeap->size--;
    minHeapify(minHeap, 0);
    return root;
}

// Insert a new element into the heap (Heapify up)
void insertHeap(MinHeap* minHeap, int v, int dist) {
    if (minHeap->size == minHeap->capacity) return; 
    int i = minHeap->size++;
    minHeap->array[i].vertex = v;
    minHeap->array[i].distance = dist;

    while (i != 0 && minHeap->array[(i - 1) / 2].distance > minHeap->array[i].distance) {
        swap(&minHeap->array[i], &minHeap->array[(i - 1) / 2]);
        i = (i - 1) / 2;
    }
}

int main() {
    int n, m;
    
    // Read number of vertices (n) and edges (m)
    if (scanf("%d %d", &n, &m) != 2) return 0;

    // Allocate memory for adjacency list (1-indexed)
    Node** head = (Node**)calloc(n + 1, sizeof(Node*));
    
    // Read all m directed edges
    for (int i = 0; i < m; i++) {
        int u, v, w;
        scanf("%d %d %d", &u, &v, &w);
        addEdge(head, u, v, w);
    }

    int source;
    scanf("%d", &source);

    // Distance array initialized to INF
    int* dist = (int*)malloc((n + 1) * sizeof(int));
    for (int i = 1; i <= n; i++) {
        dist[i] = INF;
    }
    dist[source] = 0;

    // Initialize Min-Heap
    MinHeap* minHeap = (MinHeap*)malloc(sizeof(MinHeap));
    // Capacity handles at worst 'm' edge relaxations due to lazy deletion
    minHeap->capacity = m + 10; 
    minHeap->size = 0;
    minHeap->array = (HeapNode*)malloc(minHeap->capacity * sizeof(HeapNode));

    // Start Dijkstra's from the source node
    insertHeap(minHeap, source, 0);

    // Process the priority queue
    while (minHeap->size > 0) {
        HeapNode minNode = extractMin(minHeap);
        int u = minNode.vertex;
        int d = minNode.distance;

        // Lazy deletion: If we already found a shorter path to 'u', ignore this outdated entry
        if (d > dist[u]) continue;

        // Traverse all neighbors of the current node
        Node* temp = head[u];
        while (temp != NULL) {
            int v = temp->dest;
            int weight = temp->weight;

            // Relaxation step
            if (dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                insertHeap(minHeap, v, dist[v]);
            }
            temp = temp->next;
        }
    }

    // Output the shortest distances
    for (int i = 1; i <= n; i++) {
        if (dist[i] == INF) {
            printf("-1 "); // Convention for unreachable nodes
        } else {
            printf("%d ", dist[i]);
        }
    }
    printf("\n");

    // Clean up allocated memory 
    for (int i = 1; i <= n; i++) {
        Node* temp = head[i];
        while (temp != NULL) {
            Node* toFree = temp;
            temp = temp->next;
            free(toFree);
        }
    }
    free(head);
    free(dist);
    free(minHeap->array);
    free(minHeap);

    return 0;
}