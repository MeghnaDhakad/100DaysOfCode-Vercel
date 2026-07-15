#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int compare(const void *a, const void *b) {
    int x = *(int *)a;
    int y = *(int *)b;

    if (x < y)
        return -1;
    if (x > y)
        return 1;

    return 0;
}

int main() {
    int n;
    scanf("%d", &n);

    int arr[n];

    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Sort the array
    qsort(arr, n, sizeof(int), compare);

    int left = 0;
    int right = n - 1;

    int first = arr[left];
    int second = arr[right];

    long long closest = LLONG_MAX;

    while (left < right) {

        long long sum = (long long)arr[left] + arr[right];

        // Check whether the current sum is closer to zero
        if (llabs(sum) < closest) {
            closest = llabs(sum);

            first = arr[left];
            second = arr[right];
        }

        // Move pointers
        if (sum < 0) {
            left++;
        }
        else if (sum > 0) {
            right--;
        }
        else {
            // Sum is exactly zero, so it is the closest possible
            break;
        }
    }

    printf("%d %d", first, second);

    return 0;
}