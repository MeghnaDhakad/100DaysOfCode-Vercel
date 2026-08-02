#include <stdio.h>
#include <string.h>

#define MAX 100

int pq[MAX];
int size = 0;

// Insert element
void insert(int value) {
    pq[size++] = value;
}

// Delete highest priority (smallest element)
int deleteElement() {
    if (size == 0)
        return -1;

    int minIndex = 0;

    for (int i = 1; i < size; i++) {
        if (pq[i] < pq[minIndex]) {
            minIndex = i;
        }
    }

    int value = pq[minIndex];

    // Shift elements
    for (int i = minIndex; i < size - 1; i++) {
        pq[i] = pq[i + 1];
    }

    size--;

    return value;
}

// Peek highest priority
int peek() {
    if (size == 0)
        return -1;

    int min = pq[0];

    for (int i = 1; i < size; i++) {
        if (pq[i] < min) {
            min = pq[i];
        }
    }

    return min;
}

int main() {

    int n;
    scanf("%d", &n);

    char operation[10];

    while (n--) {

        scanf("%s", operation);

        if (strcmp(operation, "insert") == 0) {
            int x;
            scanf("%d", &x);
            insert(x);
        }
        else if (strcmp(operation, "delete") == 0) {
            printf("%d\n", deleteElement());
        }
        else if (strcmp(operation, "peek") == 0) {
            printf("%d\n", peek());
        }
    }

    return 0;
}