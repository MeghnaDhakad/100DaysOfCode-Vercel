#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

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

        // Left child
        if (i < n && arr[i] != -1) {
            current->left = createNode(arr[i]);
            queue[rear++] = current->left;
        }
        i++;

        // Right child
        if (i < n && arr[i] != -1) {
            current->right = createNode(arr[i]);
            queue[rear++] = current->right;
        }
        i++;
    }

    free(queue);
    return root;
}

// Recursive helper function to check if two subtrees are mirrors of each other
bool isMirror(struct Node* t1, struct Node* t2) {
    // If both nodes are NULL, they are mirrors
    if (t1 == NULL && t2 == NULL) {
        return true;
    }
    
    // If only one is NULL, they are NOT mirrors
    if (t1 == NULL || t2 == NULL) {
        return false;
    }
    
    // They are mirrors if:
    // 1. Their values match
    // 2. t1's left child is a mirror of t2's right child
    // 3. t1's right child is a mirror of t2's left child
    return (t1->data == t2->data) 
        && isMirror(t1->left, t2->right) 
        && isMirror(t1->right, t2->left);
}

// Main function to check if the tree is symmetric
bool isSymmetric(struct Node* root) {
    // An empty tree is symmetric
    if (root == NULL) {
        return true;
    }
    // Check if the left and right subtrees are mirrors
    return isMirror(root->left, root->right);
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1) {
        return 0;
    }

    // Read the level-order traversal array
    int *arr = (int *)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Build the tree
    struct Node* root = buildTree(arr, n);

    // Check symmetry and print result
    if (isSymmetric(root)) {
        printf("YES\n");
    } else {
        printf("NO\n");
    }

    // Clean up
    free(arr);

    return 0;
}