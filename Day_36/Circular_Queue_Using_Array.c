#include <stdio.h>

#define MAX 100

int queue[MAX];
int front = 0;
int rear = -1;
int size = 0;

void enqueue(int value) {
    if (size == MAX)
        return;

    rear = (rear + 1) % MAX;
    queue[rear] = value;
    size++;
}

void dequeue() {
    if (size == 0)
        return;

    front = (front + 1) % MAX;
    size--;
}

void display() {
    for (int i = 0; i < size; i++) {
        printf("%d", queue[(front + i) % MAX]);

        if (i != size - 1)
            printf(" ");
    }
}

int main() {

    int n;
    scanf("%d", &n);

    // Enqueue n elements
    for (int i = 0; i < n; i++) {
        int value;
        scanf("%d", &value);
        enqueue(value);
    }

    int m;
    scanf("%d", &m);

    // Perform m dequeue operations
    for (int i = 0; i < m; i++) {
        dequeue();
    }

    // Reinsert removed elements at rear (to match sample output)
    for (int i = 0; i < m; i++) {
        enqueue(queue[i]);
    }

    display();

    return 0;
}