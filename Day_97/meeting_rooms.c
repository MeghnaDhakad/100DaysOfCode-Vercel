#include <stdio.h>
#include <stdlib.h>

// --------------------------------------------------------
// 1. INTERVAL STRUCTURE & SORTING LOGIC
// --------------------------------------------------------
typedef struct {
    int start;
    int end;
} Interval;

// Comparator to sort intervals by start time for qsort
int compareIntervals(const void* a, const void* b) {
    return ((Interval*)a)->start - ((Interval*)b)->start;
}

// --------------------------------------------------------
// 2. MIN-HEAP IMPLEMENTATION (To track end times)
// --------------------------------------------------------
typedef struct {
    int* data;
    int size;
    int capacity;
} MinHeap;

MinHeap* createMinHeap(int capacity) {
    MinHeap* heap = (MinHeap*)malloc(sizeof(MinHeap));
    heap->data = (int*)malloc(capacity * sizeof(int));
    heap->size = 0;
    heap->capacity = capacity;
    return heap;
}

void swap(int* a, int* b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Bubble up to maintain heap property after insertion
void bubbleUp(MinHeap* heap, int index) {
    int parent = (index - 1) / 2;
    if (index > 0 && heap->data[index] < heap->data[parent]) {
        swap(&heap->data[index], &heap->data[parent]);
        bubbleUp(heap, parent);
    }
}

// Insert a new end time into the heap
void insertMinHeap(MinHeap* heap, int val) {
    if (heap->size == heap->capacity) return;
    heap->data[heap->size] = val;
    bubbleUp(heap, heap->size);
    heap->size++;
}

// Bubble down to maintain heap property after extraction
void bubbleDown(MinHeap* heap, int index) {
    int smallest = index;
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left < heap->size && heap->data[left] < heap->data[smallest])
        smallest = left;
    if (right < heap->size && heap->data[right] < heap->data[smallest])
        smallest = right;

    if (smallest != index) {
        swap(&heap->data[index], &heap->data[smallest]);
        bubbleDown(heap, smallest);
    }
}

// Remove and return the smallest end time
int extractMin(MinHeap* heap) {
    if (heap->size <= 0) return -1;
    int root = heap->data[0];
    heap->data[0] = heap->data[heap->size - 1];
    heap->size--;
    bubbleDown(heap, 0);
    return root;
}

// Look at the smallest end time without removing it
int peekMinHeap(MinHeap* heap) {
    if (heap->size <= 0) return -1;
    return heap->data[0];
}

void freeMinHeap(MinHeap* heap) {
    free(heap->data);
    free(heap);
}

// --------------------------------------------------------
// 3. CORE ALGORITHM
// --------------------------------------------------------
int minMeetingRooms(Interval* intervals, int n) {
    if (n == 0) return 0;

    // Step 1: Sort the intervals by start time
    qsort(intervals, n, sizeof(Interval), compareIntervals);

    // Step 2: Initialize a Min-Heap to track meeting end times
    MinHeap* minHeap = createMinHeap(n);

    // Add the first meeting's end time
    insertMinHeap(minHeap, intervals[0].end);

    // Step 3: Iterate through the remaining meetings
    for (int i = 1; i < n; i++) {
        // If the room freeing up earliest is ready before the current meeting starts
        if (intervals[i].start >= peekMinHeap(minHeap)) {
            extractMin(minHeap); // Free up that room
        }
        
        // Occupy the room (or a new one) with the current meeting's end time
        insertMinHeap(minHeap, intervals[i].end);
    }

    // Step 4: The number of active rooms is the size of the heap
    int roomsRequired = minHeap->size;
    
    freeMinHeap(minHeap);
    return roomsRequired;
}

// --------------------------------------------------------
// 4. MAIN EXECUTION
// --------------------------------------------------------
int main() {
    int n;
    
    // Read the number of meetings
    if (scanf("%d", &n) != 1 || n <= 0) return 0;
    
    Interval* intervals = (Interval*)malloc(n * sizeof(Interval));
    
    // Read the intervals (start end)
    for (int i = 0; i < n; i++) {
        scanf("%d %d", &intervals[i].start, &intervals[i].end);
    }
    
    int rooms = minMeetingRooms(intervals, n);
    printf("%d\n", rooms);
    
    free(intervals);
    return 0;
}