#include <stdio.h>
#include <stdlib.h>

struct Node {
    int coeff;
    int exp;
    struct Node *next;
};

struct Node* createNode(int coeff, int exp) {
    struct Node *newNode = (struct Node*)malloc(sizeof(struct Node));

    newNode->coeff = coeff;
    newNode->exp = exp;
    newNode->next = NULL;

    return newNode;
}

int main() {

    int n;
    scanf("%d", &n);

    struct Node *head = NULL;
    struct Node *tail = NULL;

    // Create linked list
    for (int i = 0; i < n; i++) {

        int coeff, exp;
        scanf("%d %d", &coeff, &exp);

        struct Node *newNode = createNode(coeff, exp);

        if (head == NULL) {
            head = newNode;
            tail = newNode;
        } else {
            tail->next = newNode;
            tail = newNode;
        }
    }

    // Print polynomial
    struct Node *temp = head;

    while (temp != NULL) {

        if (temp->exp == 0) {
            printf("%d", temp->coeff);
        }
        else if (temp->exp == 1) {
            printf("%dx", temp->coeff);
        }
        else {
            printf("%dx^%d", temp->coeff, temp->exp);
        }

        if (temp->next != NULL) {
            printf(" + ");
        }

        temp = temp->next;
    }

    return 0;
}