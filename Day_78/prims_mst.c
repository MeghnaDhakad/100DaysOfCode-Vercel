#include <stdio.h>
#include <stdlib.h>

// Adjacency list node structure
typedef struct Node {
    int dest;
    int weight;
    struct Node* next;
} Node;

// Function to add an undirected edge
void addEdge(Node** head, int u, int v, int w) {
    Node* newNode1 = (Node*)malloc(sizeof(Node));
    newNode1->dest = v;
    newNode1->weight = w;
    newNode1->next = head[u];
    head[u] = newNode1;

    Node* newNode2 = (Node*)malloc(sizeof(Node));
    newNode2->dest = u;
    newNode2->weight = w;
    newNode2->next = head[v];
    head[v] = newNode2;
}

// Min-Heap node
typedef struct {
    int vertex;
    int weight;
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

    if (left < minHeap->size && minHeap->array[left].weight < minHeap->array[smallest].weight)
        smallest = left;

    if (right < minHeap->size && minHeap->array[right].weight < minHeap->array[smallest].weight)
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
void insertHeap(MinHeap* minHeap, int v, int w) {
    if (minHeap->size == minHeap->capacity) return; 
    int i = minHeap->size++;
    minHeap->array[i].vertex = v;
    minHeap->array[i].weight = w;

    while (i != 0 && minHeap->array[(i - 1) / 2].weight > minHeap->array[i].weight) {
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
    
    // Read all m edges
    for (int i = 0; i < m; i++) {
        int u, v, w;
        scanf("%d %d %d", &u, &v, &w);
        addEdge(head, u, v, w);
    }

    int* inMST = (int*)calloc(n + 1, sizeof(int));

    // Initialize Min-Heap
    MinHeap* minHeap = (MinHeap*)malloc(sizeof(MinHeap));
    // Capacity needs to handle at worst 2*M edge insertions due to lazy deletion
    minHeap->capacity = 2 * m + 10; 
    minHeap->size = 0;
    minHeap->array = (HeapNode*)malloc(minHeap->capacity * sizeof(HeapNode));

    // Start Prim's algorithm from vertex 1 with a weight of 0
    insertHeap(minHeap, 1, 0);

    long long totalWeight = 0;
    int nodesConnected = 0;

    // Loop until we've connected all n nodes or the heap is empty
    while (minHeap->size > 0 && nodesConnected < n) {
        HeapNode minNode = extractMin(minHeap);
        int u = minNode.vertex;

        // Lazy deletion: If the node is already in the MST, skip it
        if (inMST[u]) continue;

        // Add node to MST
        inMST[u] = 1;
        totalWeight += minNode.weight;
        nodesConnected++;

        // Traverse all neighbors of the newly added node
        Node* temp = head[u];
        while (temp != NULL) {
            int v = temp->dest;
            // If the neighbor is not yet in the MST, add its edge to the heap
            if (!inMST[v]) {
                insertHeap(minHeap, v, temp->weight);
            }
            temp = temp->next;
        }
    }

    // Output the total weight of the MST
    printf("%lld\n", totalWeight);

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
    free(inMST);
    free(minHeap->array);
    free(minHeap);

    return 0;
}