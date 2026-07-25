#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

struct Node* createNode(int data) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = NULL;
    return newNode;
}

int main() {
    int n;
    scanf("%d", &n);

    if (n <= 0) {
        return 0;
    }

    struct Node *head = NULL;
    struct Node *tail = NULL;

    // Create linked list
    for (int i = 0; i < n; i++) {
        int value;
        scanf("%d", &value);

        struct Node *newNode = createNode(value);

        if (head == NULL) {
            head = newNode;
            tail = newNode;
        } else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    int k;
    scanf("%d", &k);

    // Handle k greater than n
    k = k % n;

    if (k != 0) {

        // Make list circular
        tail->next = head;

        // New tail is at position n-k
        struct Node *newTail = head;

        for (int i = 1; i < n - k; i++) {
            newTail = newTail->next;
        }

        // Node after newTail becomes new head
        head = newTail->next;

        // Break circular list
        newTail->next = NULL;
    }

    // Print result
    struct Node *temp = head;

    while (temp != NULL) {
        printf("%d", temp->data);

        if (temp->next != NULL) {
            printf(" ");
        }

        temp = temp->next;
    }

    return 0;
}