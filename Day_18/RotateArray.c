#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);

    int arr[n];
    int rotated[n];

    // Input array
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    int k;
    scanf("%d", &k);

    // Handle k greater than array size
    k = k % n;

    // Place each element at its new position
    for (int i = 0; i < n; i++) {
        int newIndex = (i + k) % n;
        rotated[newIndex] = arr[i];
    }

    // Print rotated array
    for (int i = 0; i < n; i++) {
        printf("%d ", rotated[i]);
    }

    return 0;
}