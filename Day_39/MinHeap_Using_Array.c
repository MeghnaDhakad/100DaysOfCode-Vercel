#include <stdio.h>
#include <string.h>

#define MAX 100

int heap[MAX];
int size = 0;

// Swap two elements
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Insert into Min Heap
void insert(int value) {
    int i = size;
    heap[size++] = value;

    // Heapify Up
    while (i > 0) {
        int parent = (i - 1) / 2;

        if (heap[parent] <= heap[i])
            break;

        swap(&heap[parent], &heap[i]);
        i = parent;
    }
}

// Peek minimum element
int peek() {
    if (size == 0)
        return -1;

    return heap[0];
}

// Extract minimum element
int extractMin() {

    if (size == 0)
        return -1;

    int min = heap[0];

    heap[0] = heap[size - 1];
    size--;

    // Heapify Down
    int i = 0;

    while (1) {

        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && heap[left] < heap[smallest])
            smallest = left;

        if (right < size && heap[right] < heap[smallest])
            smallest = right;

        if (smallest == i)
            break;

        swap(&heap[i], &heap[smallest]);
        i = smallest;
    }

    return min;
}

int main() {

    int n;
    scanf("%d", &n);

    char operation[20];

    while (n--) {

        scanf("%s", operation);

        if (strcmp(operation, "insert") == 0) {

            int x;
            scanf("%d", &x);
            insert(x);

        } else if (strcmp(operation, "peek") == 0) {

            printf("%d\n", peek());

        } else if (strcmp(operation, "extractMin") == 0) {

            printf("%d\n", extractMin());
        }
    }

    return 0;
}