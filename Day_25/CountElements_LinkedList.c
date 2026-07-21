#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

// Function to create a new node
struct Node* createNode(int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

int main() {

    int n;
    scanf("%d", &n);

    struct Node *head = NULL, *temp = NULL;

    // Create linked list
    for (int i = 0; i < n; i++) {

        int x;
        scanf("%d", &x);

        struct Node* newNode = createNode(x);

        if (head == NULL) {
            head = newNode;
            temp = newNode;
        } else {
            temp->next = newNode;
            temp = newNode;
        }
    }

    int key;
    scanf("%d", &key);

    // Count occurrences
    int count = 0;
    temp = head;

    while (temp != NULL) {

        if (temp->data == key) {
            count++;
        }

        temp = temp->next;
    }

    printf("%d", count);

    return 0;
}