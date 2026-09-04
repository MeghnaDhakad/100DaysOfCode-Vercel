#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

// 1. Edge representation
typedef struct {
    int u;
    int v;
    int w;
} Edge;

// 2. Bellman-Ford Algorithm
void bellmanFord(int n, int m, Edge edges[], int src) {
    // Use long long to avoid overflow when adding large weights
    long long* dist = (long long*)malloc(n * sizeof(long long));

    // Initialize distances to "Infinity"
    for (int i = 0; i < n; i++) {
        dist[i] = LLONG_MAX;
    }
    dist[src] = 0;

    // Step 1: Relax all edges (n - 1) times
    for (int i = 1; i <= n - 1; i++) {
        for (int j = 0; j < m; j++) {
            int u = edges[j].u;
            int v = edges[j].v;
            int w = edges[j].w;

            if (dist[u] != LLONG_MAX && dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
            }
        }
    }

    // Step 2: Check for negative-weight cycles
    // If an edge can still be relaxed, a negative cycle exists.
    for (int j = 0; j < m; j++) {
        int u = edges[j].u;
        int v = edges[j].v;
        int w = edges[j].w;

        if (dist[u] != LLONG_MAX && dist[u] + w < dist[v]) {
            printf("NEGATIVE CYCLE\n");
            free(dist);
            return; // Terminate early
        }
    }

    // Step 3: Print shortest distances
    for (int i = 0; i < n; i++) {
        if (dist[i] == LLONG_MAX) {
            printf("INF "); // Unreachable nodes
        } else {
            printf("%lld ", dist[i]);
        }
    }
    printf("\n");

    free(dist);
}

int main() {
    int n, m;
    
    // Read number of vertices (n) and edges (m)
    if (scanf("%d %d", &n, &m) != 2) return 0;

    Edge* edges = (Edge*)malloc(m * sizeof(Edge));

    // Read all m edges (u, v, w)
    for (int i = 0; i < m; i++) {
        scanf("%d %d %d", &edges[i].u, &edges[i].v, &edges[i].w);
    }

    // Assuming the source vertex is 0. 
    // (If your problem specifies a different source or provides it as input, change this)
    int source = 0; 
    
    bellmanFord(n, m, edges, source);

    free(edges);
    return 0;
}