#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

int main() {
    int n;
    scanf("%d", &n);

    struct Node *head = NULL;
    struct Node *temp = NULL;
    struct Node *newNode = NULL;

    // Create linked list
    for (int i = 0; i < n; i++) {
        newNode = (struct Node *)malloc(sizeof(struct Node));

        scanf("%d", &newNode->data);
        newNode->next = NULL;

        if (head == NULL) {
            head = newNode;
            temp = newNode;
        } else {
            temp->next = newNode;
            temp = newNode;
        }
    }

    // Count nodes
    int count = 0;
    temp = head;

    while (temp != NULL) {
        count++;
        temp = temp->next;
    }

    printf("%d", count);

    return 0;
}