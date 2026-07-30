#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    int data;
    struct Node *next;
};

struct Node *top = NULL;

void push(int value) {
    struct Node *newNode = (struct Node *)malloc(sizeof(struct Node));

    newNode->data = value;
    newNode->next = top;
    top = newNode;
}

int pop() {
    struct Node *temp = top;
    int value = top->data;

    top = top->next;
    free(temp);

    return value;
}

int main() {

    char token[20];

    // Read tokens until end of input
    while (scanf("%19s", token) != EOF) {

        if (strcmp(token, "+") == 0) {
            int b = pop();
            int a = pop();
            push(a + b);
        }
        else if (strcmp(token, "-") == 0) {
            int b = pop();
            int a = pop();
            push(a - b);
        }
        else if (strcmp(token, "*") == 0) {
            int b = pop();
            int a = pop();
            push(a * b);
        }
        else if (strcmp(token, "/") == 0) {
            int b = pop();
            int a = pop();
            push(a / b);
        }
        else {
            push(atoi(token));
        }
    }

    printf("%d", pop());

    return 0;
}