#include <stdio.h>
#include <stdlib.h>

// --------------------------------------------------------
// 1. DATA STRUCTURE & SORTING LOGIC
// --------------------------------------------------------
typedef struct {
    int position;
    double time;
} Car;

// Comparator to sort cars by position in DESCENDING order (closest to target first)
int compareCars(const void* a, const void* b) {
    Car* carA = (Car*)a;
    Car* carB = (Car*)b;
    // For descending order, return (B - A)
    return carB->position - carA->position;
}

// --------------------------------------------------------
// 2. CORE ALGORITHM
// --------------------------------------------------------
int carFleet(int target, int* position, int positionSize, int* speed, int speedSize) {
    if (positionSize == 0) return 0;

    // Step 1: Allocate an array of Car structs
    Car* cars = (Car*)malloc(positionSize * sizeof(Car));
    if (cars == NULL) return 0;

    // Step 2: Populate the array and calculate time to target
    for (int i = 0; i < positionSize; i++) {
        cars[i].position = position[i];
        // Time = Distance / Speed (cast to double to preserve decimals)
        cars[i].time = (double)(target - position[i]) / speed[i];
    }

    // Step 3: Sort cars descending by position
    qsort(cars, positionSize, sizeof(Car), compareCars);

    int fleets = 0;
    double currentSlowestTime = 0.0;

    // Step 4: Iterate to count bottlenecks
    for (int i = 0; i < positionSize; i++) {
        // If this car strictly takes longer than the bottleneck ahead of it,
        // it cannot catch up. It forms its own new fleet.
        if (cars[i].time > currentSlowestTime) {
            fleets++;
            currentSlowestTime = cars[i].time;
        }
    }

    // Clean up memory
    free(cars);
    
    return fleets;
}

// --------------------------------------------------------
// 3. MAIN EXECUTION
// --------------------------------------------------------
int main() {
    // Test Case 1: Standard overlapping fleets
    int target1 = 12;
    int pos1[] = {10, 8, 0, 5, 3};
    int speed1[] = {2, 4, 1, 1, 3};
    int size1 = sizeof(pos1) / sizeof(pos1[0]);
    
    printf("Test Case 1:\n");
    printf("Expected: 3\n");
    printf("Actual:   %d\n\n", carFleet(target1, pos1, size1, speed1, size1));

    // Test Case 2: Only one car
    int target2 = 10;
    int pos2[] = {3};
    int speed2[] = {3};
    int size2 = sizeof(pos2) / sizeof(pos2[0]);
    
    printf("Test Case 2:\n");
    printf("Expected: 1\n");
    printf("Actual:   %d\n\n", carFleet(target2, pos2, size2, speed2, size2));

    // Test Case 3: Fast car stuck behind a slow car
    int target3 = 100;
    int pos3[] = {0, 2, 4};
    int speed3[] = {4, 2, 1};
    int size3 = sizeof(pos3) / sizeof(pos3[0]);
    
    printf("Test Case 3:\n");
    printf("Expected: 1\n");
    printf("Actual:   %d\n", carFleet(target3, pos3, size3, speed3, size3));

    return 0;
}