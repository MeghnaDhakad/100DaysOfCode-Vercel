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

// Function to find the Lowest Common Ancestor (LCA) in a Binary Tree
struct Node* findLCA(struct Node* root, int n1, int n2) {
    // Base case: if we reach the end of a branch, return NULL
    if (root == NULL) {
        return NULL;
    }

    // If we find either of the nodes, return that node back up the tree
    if (root->data == n1 || root->data == n2) {
        return root;
    }

    // Recursively search the left and right subtrees
    struct Node* left_lca = findLCA(root->left, n1, n2);
    struct Node* right_lca = findLCA(root->right, n1, n2);

    // If both left and right calls return a non-NULL node, it means one target 
    // is in the left subtree and one is in the right. This node is the LCA!
    if (left_lca != NULL && right_lca != NULL) {
        return root;
    }

    // Otherwise, return the non-NULL node (pass the found target up the chain)
    if (left_lca != NULL) {
        return left_lca;
    } else {
        return right_lca;
    }
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

    int n1, n2;
    // Read the two target nodes
    scanf("%d %d", &n1, &n2);

    // Build the tree
    struct Node* root = buildTree(arr, n);

    // Find and print the LCA
    struct Node* lca = findLCA(root, n1, n2);
    
    if (lca != NULL) {
        printf("%d\n", lca->data);
    } else {
        printf("LCA not found\n");
    }

    // Clean up
    free(arr);

    return 0;
}