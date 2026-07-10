#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);

    int matrix[n][n];
    int isIdentity = 1;

    // Input matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            scanf("%d", &matrix[i][j]);
        }
    }

    // Check identity matrix
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {

            if (i == j && matrix[i][j] != 1) {
                isIdentity = 0;
                break;
            }

            if (i != j && matrix[i][j] != 0) {
                isIdentity = 0;
                break;
            }
        }

        if (isIdentity == 0) {
            break;
        }
    }

    if (isIdentity == 1) {
        printf("Identity Matrix");
    } else {
        printf("Not an Identity Matrix");
    }

    return 0;
}