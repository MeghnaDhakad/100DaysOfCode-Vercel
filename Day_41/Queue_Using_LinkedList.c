#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    int data;
    struct Node *next;
};

struct Node *front = NULL;
struct Node *rear = NULL;

// Enqueue operation
void enqueue(int value) {

    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->next = NULL;

    if (rear == NULL) {
        front = rear = newNode;
    } else {
        rear->next = newNode;
        rear = newNode;
    }
}

// Dequeue operation
int dequeue() {

    if (front == NULL)
        return -1;

    struct Node *temp = front;
    int value = temp->data;

    front = front->next;

    if (front == NULL)
        rear = NULL;

    free(temp);

    return value;
}

int main() {

    int N;
    scanf("%d", &N);

    char operation[20];

    while (N--) {

        scanf("%s", operation);

        if (strcmp(operation, "enqueue") == 0) {
            int x;
            scanf("%d", &x);
            enqueue(x);
        }
        else if (strcmp(operation, "dequeue") == 0) {
            printf("%d\n", dequeue());
        }
    }

    return 0;
}