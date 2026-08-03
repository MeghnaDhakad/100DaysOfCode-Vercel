#include <stdio.h>
#include <string.h>

#define MAX 100

int deque[MAX];
int front = -1, rear = -1;

// Check if deque is empty
int empty() {
    return (front == -1);
}

// Return size
int size() {
    if (empty())
        return 0;
    return rear - front + 1;
}

// Insert at front
void push_front(int value) {

    if (front == -1) {
        front = rear = 0;
    } else if (front > 0) {
        front--;
    } else {
        printf("Deque Overflow\n");
        return;
    }

    deque[front] = value;
}

// Insert at rear
void push_back(int value) {

    if (front == -1) {
        front = rear = 0;
    } else if (rear < MAX - 1) {
        rear++;
    } else {
        printf("Deque Overflow\n");
        return;
    }

    deque[rear] = value;
}

// Delete from front
void pop_front() {

    if (empty()) {
        printf("Deque Underflow\n");
        return;
    }

    if (front == rear)
        front = rear = -1;
    else
        front++;
}

// Delete from rear
void pop_back() {

    if (empty()) {
        printf("Deque Underflow\n");
        return;
    }

    if (front == rear)
        front = rear = -1;
    else
        rear--;
}

// Front element
int getFront() {
    if (empty())
        return -1;
    return deque[front];
}

// Rear element
int getBack() {
    if (empty())
        return -1;
    return deque[rear];
}

// Display deque
void display() {

    if (empty()) {
        printf("Deque is Empty\n");
        return;
    }

    for (int i = front; i <= rear; i++) {
        printf("%d ", deque[i]);
    }

    printf("\n");
}

int main() {

    int n;
    scanf("%d", &n);

    while (n--) {

        char op[20];
        scanf("%s", op);

        if (strcmp(op, "push_front") == 0) {
            int x;
            scanf("%d", &x);
            push_front(x);
        }
        else if (strcmp(op, "push_back") == 0) {
            int x;
            scanf("%d", &x);
            push_back(x);
        }
        else if (strcmp(op, "pop_front") == 0) {
            pop_front();
        }
        else if (strcmp(op, "pop_back") == 0) {
            pop_back();
        }
        else if (strcmp(op, "front") == 0) {
            printf("%d\n", getFront());
        }
        else if (strcmp(op, "back") == 0) {
            printf("%d\n", getBack());
        }
        else if (strcmp(op, "size") == 0) {
            printf("%d\n", size());
        }
        else if (strcmp(op, "empty") == 0) {
            printf("%s\n", empty() ? "true" : "false");
        }
    }

    printf("Final Deque: ");
    display();

    return 0;
}