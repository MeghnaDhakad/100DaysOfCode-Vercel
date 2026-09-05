#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    int m, n;
    
    // Read hash table size (m) and number of operations (n)
    if (scanf("%d", &m) != 1) return 0;
    if (scanf("%d", &n) != 1) return 0;

    // Allocate memory for the hash table
    int* table = (int*)malloc(m * sizeof(int));
    
    // Initialize the table with -1 to indicate empty slots
    // (Assuming all valid keys inserted are non-negative)
    for (int i = 0; i < m; i++) {
        table[i] = -1; 
    }

    char op[10];
    int key;

    // Process all 'n' operations
    for (int k = 0; k < n; k++) {
        scanf("%s %d", op, &key);
        
        if (strcmp(op, "INSERT") == 0) {
            // Quadratic probing for insertion: h(k, i) = (h(k) + i*i) % m
            for (int i = 0; i < m; i++) {
                int idx = ((key % m) + (i * i)) % m;
                
                // If we find an empty slot or the key already exists, insert and stop
                if (table[idx] == -1 || table[idx] == key) {
                    table[idx] = key;
                    break;
                }
            }
        } 
        else if (strcmp(op, "SEARCH") == 0) {
            int found = 0;
            
            // Quadratic probing for search
            for (int i = 0; i < m; i++) {
                int idx = ((key % m) + (i * i)) % m;
                
                if (table[idx] == key) {
                    found = 1;     // Key found
                    break;
                } else if (table[idx] == -1) {
                    break;         // Hit an empty slot, meaning key is definitely not in the table
                }
            }
            
            if (found) {
                printf("FOUND\n");
            } else {
                printf("NOT FOUND\n");
            }
        }
    }

    // Clean up memory
    free(table);
    return 0;
}