#include <stdio.h>
#include <limits.h>

#define MAX_V 100    // Max vertices
#define MAX_E 1000   // Max edges (and max heap size)

// 1. Compact Graph Representation (Forward Star)
int head[MAX_V], dest[MAX_E], weight[MAX_E], nxt[MAX_E], edge_cnt = 0;

void addEdge(int u, int v, int w) {
    dest[edge_cnt] = v; weight[edge_cnt] = w;
    nxt[edge_cnt] = head[u]; head[u] = edge_cnt++;
}

// 2. Minimal Priority Queue (Min-Heap)
typedef struct { int v, d; } Node;
Node pq[MAX_E];
int pq_sz = 0;

void push(int v, int d) {
    int i = pq_sz++;
    // Bubble up
    while (i && d < pq[(i - 1) / 2].d) {
        pq[i] = pq[(i - 1) / 2];
        i = (i - 1) / 2;
    }
    pq[i] = (Node){v, d};
}

Node pop() {
    Node res = pq[0];
    Node last = pq[--pq_sz];
    int i = 0, child;
    // Bubble down
    while ((child = 2 * i + 1) < pq_sz) {
        if (child + 1 < pq_sz && pq[child + 1].d < pq[child].d) child++;
        if (last.d <= pq[child].d) break;
        pq[i] = pq[child];
        i = child;
    }
    pq[i] = last;
    return res;
}

// 3. Dijkstra's Algorithm
int dist[MAX_V];

void dijkstra(int src, int V) {
    for (int i = 0; i < V; i++) dist[i] = INT_MAX;
    dist[src] = 0;
    push(src, 0);

    while (pq_sz > 0) {
        Node curr = pop();
        int u = curr.v;
        
        if (curr.d > dist[u]) continue; // Lazy deletion

        // Traverse neighbors
        for (int e = head[u]; e != -1; e = nxt[e]) {
            int v = dest[e], w = weight[e];
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                push(v, dist[v]);
            }
        }
    }
}

// 4. Main Method
int main() {
    int V = 5;
    
    // Initialize head array to -1 (empty)
    for (int i = 0; i < V; i++) head[i] = -1; 

    // Add edges
    addEdge(0, 1, 9); addEdge(0, 2, 6); addEdge(0, 3, 5); addEdge(0, 4, 3);
    addEdge(2, 1, 2); addEdge(2, 3, 4);

    dijkstra(0, V);

    printf("Vertex\tDistance\n");
    for (int i = 0; i < V; i++) 
        printf("%d\t%d\n", i, dist[i]);
        
    return 0;
}