#include <stdio.h>
#include <stdlib.h>

// Definition for a binary tree node
struct Node {
    int data;
    struct Node *left;
    struct Node *right;
};

// Helper function to create a new node
struct Node* createNode(int value) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

// Function to build the tree from level-order input
struct Node* buildTree(int arr[], int n) {
    if (n == 0 || arr[0] == -1) {
        return NULL;
    }

    struct Node** queue = (struct Node**)malloc(n * sizeof(struct Node*));
    int front = 0, rear = 0;

    struct Node* root = createNode(arr[0]);
    queue[rear++] = root;

    int i = 1;
    while (i < n && front < rear) {
        struct Node* current = queue[front++];

        // Process left child
        if (i < n && arr[i] != -1) {
            current->left = createNode(arr[i]);
            queue[rear++] = current->left;
        }
        i++;

        // Process right child
        if (i < n && arr[i] != -1) {
            current->right = createNode(arr[i]);
            queue[rear++] = current->right;
        }
        i++;
    }

    free(queue);
    return root;
}

// Function to print the zigzag level order traversal
void printZigzag(struct Node* root, int n) {
    if (root == NULL) {
        return;
    }

    // Allocate a queue for BFS (max size n)
    struct Node** queue = (struct Node**)malloc(n * sizeof(struct Node*));
    int front = 0, rear = 0;

    queue[rear++] = root;
    int leftToRight = 1; // 1 means left-to-right, 0 means right-to-left

    while (front < rear) {
        int levelSize = rear - front;
        
        // Array to temporarily hold the values of the current level
        int* tempLevel = (int*)malloc(levelSize * sizeof(int));
        
        // Process all nodes at the current level
        for (int i = 0; i < levelSize; i++) {
            struct Node* current = queue[front++];
            tempLevel[i] = current->data;
            
            if (current->left != NULL) {
                queue[rear++] = current->left;
            }
            if (current->right != NULL) {
                queue[rear++] = current->right;
            }
        }
        
        // Print the current level depending on the flag direction
        if (leftToRight) {
            for (int i = 0; i < levelSize; i++) {
                printf("%d ", tempLevel[i]);
            }
        } else {
            for (int i = levelSize - 1; i >= 0; i--) {
                printf("%d ", tempLevel[i]);
            }
        }
        
        // Toggle the direction for the next level
        leftToRight = !leftToRight;
        free(tempLevel);
    }
    
    printf("\n");
    free(queue);
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1) {
        return 0;
    }

    // Allocate memory and read the level-order traversal array
    int *arr = (int *)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Build the tree
    struct Node* root = buildTree(arr, n);

    // Print zigzag traversal
    printZigzag(root, n);

    free(arr);
    return 0;
}