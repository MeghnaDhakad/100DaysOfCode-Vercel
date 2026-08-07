#include <stdio.h>

#define MAX 100

int queue[MAX];
int stack[MAX];

int front = 0;
int rear = -1;
int top = -1;

// Enqueue
void enqueue(int value) {
    queue[++rear] = value;
}

// Dequeue
int dequeue() {
    return queue[front++];
}

// Push
void push(int value) {
    stack[++top] = value;
}

// Pop
int pop() {
    return stack[top--];
}

int main() {

    int n;
    scanf("%d", &n);

    // Input queue elements
    for (int i = 0; i < n; i++) {
        int x;
        scanf("%d", &x);
        enqueue(x);
    }

    // Move queue -> stack
    while (front <= rear) {
        push(dequeue());
    }

    // Reset queue
    front = 0;
    rear = -1;

    // Move stack -> queue
    while (top != -1) {
        enqueue(pop());
    }

    // Print reversed queue
    for (int i = front; i <= rear; i++) {
        printf("%d ", queue[i]);
    }

    return 0;
}