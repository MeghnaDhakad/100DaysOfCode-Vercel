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

    // Queue to keep track of nodes
    struct Node** queue = (struct Node**)malloc(n * sizeof(struct Node*));
    int front = 0;
    int rear = 0;

    struct Node* root = createNode(arr[0]);
    queue[rear++] = root;

    int i = 1;

    while (i < n && front < rear) {
        struct Node* current = queue[front++];

        // Process the left child
        if (i < n && arr[i] != -1) {
            current->left = createNode(arr[i]);
            queue[rear++] = current->left;
        }
        i++;

        // Process the right child
        if (i < n && arr[i] != -1) {
            current->right = createNode(arr[i]);
            queue[rear++] = current->right;
        }
        i++;
    }

    free(queue);
    return root;
}

// Function to count the leaf nodes in the tree
int countLeaves(struct Node* root) {
    // Base case: If the tree is empty
    if (root == NULL) {
        return 0;
    }
    
    // If both left and right children are NULL, this is a leaf node
    if (root->left == NULL && root->right == NULL) {
        return 1;
    }
    
    // Recursively count the leaves in the left and right subtrees
    return countLeaves(root->left) + countLeaves(root->right);
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1) {
        return 0;
    }

    // Allocate memory for the array and read the elements
    int *arr = (int *)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Build the tree
    struct Node* root = buildTree(arr, n);

    // Calculate and print the leaf node count
    printf("%d\n", countLeaves(root));

    // Clean up memory
    free(arr);

    return 0;
}