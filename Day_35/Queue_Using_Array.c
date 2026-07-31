#include <stdio.h>

#define MAX 1000

int queue[MAX];
int front = 0;
int rear = -1;

void enqueue(int value) {
    rear++;
    queue[rear] = value;
}

void display() {
    for (int i = front; i <= rear; i++) {
        printf("%d", queue[i]);

        if (i < rear) {
            printf(" ");
        }
    }
}

int main() {

    int n;
    scanf("%d", &n);

    for (int i = 0; i < n; i++) {
        int value;
        scanf("%d", &value);
        enqueue(value);
    }

    display();

    return 0;
}