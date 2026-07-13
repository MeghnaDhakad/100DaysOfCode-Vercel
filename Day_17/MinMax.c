#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);

    int arr[n];

    // Input array
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Assume first element is both maximum and minimum
    int max = arr[0];
    int min = arr[0];

    // Find maximum and minimum
    for (int i = 1; i < n; i++) {

        if (arr[i] > max) {
            max = arr[i];
        }

        if (arr[i] < min) {
            min = arr[i];
        }
    }

    printf("Max: %d\n", max);
    printf("Min: %d", min);

    return 0;
}