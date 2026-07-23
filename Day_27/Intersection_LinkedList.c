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

// Create a linked list
struct Node* createList(int n) {
    struct Node *head = NULL, *temp = NULL;

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

    return head;
}

int main() {

    int n, m;

    scanf("%d", &n);
    struct Node *head1 = createList(n);

    scanf("%d", &m);
    struct Node *head2 = createList(m);

    struct Node *p1 = head1;
    struct Node *p2 = head2;

    while (p1 != NULL && p2 != NULL) {

        if (p1->data == p2->data) {
            printf("%d", p1->data);
            return 0;
        }

        if (p1->data < p2->data)
            p1 = p1->next;
        else
            p2 = p2->next;
    }

    printf("No Intersection");

    return 0;
}