#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);

    int stack[n];
    int top = -1;

    // Push elements
    for (int i = 0; i < n; i++) {
        int value;
        scanf("%d", &value);

        top++;
        stack[top] = value;
    }

    int m;
    scanf("%d", &m);

    // Pop m elements
    for (int i = 0; i < m; i++) {
        if (top != -1) {
            top--;
        }
    }

    // Print from top to bottom
    for (int i = top; i >= 0; i--) {
        printf("%d", stack[i]);

        if (i != 0) {
            printf(" ");
        }
    }

    return 0;
}