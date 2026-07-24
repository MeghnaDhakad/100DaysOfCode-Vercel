#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

// Create a new node
struct Node* createNode(int data) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

int main() {

    int n;
    scanf("%d", &n);

    struct Node *head = NULL, *temp = NULL;

    // Create Circular Linked List
    for (int i = 0; i < n; i++) {

        int x;
        scanf("%d", &x);

        struct Node *newNode = createNode(x);

        if (head == NULL) {
            head = newNode;
            temp = newNode;
        } else {
            temp->next = newNode;
            temp = newNode;
        }
    }

    // Make the list circular
    if (temp != NULL) {
        temp->next = head;
    }

    // Traverse and print the circular linked list
    if (head != NULL) {
        struct Node *current = head;

        do {
            printf("%d ", current->data);
            current = current->next;
        } while (current != head);
    }

    return 0;
}