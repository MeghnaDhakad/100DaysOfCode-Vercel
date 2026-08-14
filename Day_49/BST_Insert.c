#include <stdio.h>
#include <stdlib.h>

// Definition for a BST node
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

// Function to insert a value into the BST
struct Node* insert(struct Node* root, int value) {
    // Base case: if the tree/subtree is empty, return a new node
    if (root == NULL) {
        return createNode(value);
    }

    // Otherwise, recur down the tree
    if (value < root->data) {
        root->left = insert(root->left, value); // Go left if value is smaller
    } else if (value > root->data) {
        root->right = insert(root->right, value); // Go right if value is greater
    }
    
    // Note: If value == root->data, we do nothing (no duplicates allowed in this BST)

    // Return the (unchanged) node pointer
    return root;
}

// Function to print the inorder traversal of the BST
void inorder(struct Node* root) {
    if (root != NULL) {
        inorder(root->left);
        printf("%d ", root->data);
        inorder(root->right);
    }
}

int main() {
    int n, val;
    struct Node* root = NULL;

    // Read the number of elements
    if (scanf("%d", &n) != 1) {
        return 0;
    }

    // Read the elements and insert them into the BST
    for (int i = 0; i < n; i++) {
        scanf("%d", &val);
        root = insert(root, val);
    }

    // Print the inorder traversal to verify the BST
    inorder(root);
    printf("\n");

    return 0;
}