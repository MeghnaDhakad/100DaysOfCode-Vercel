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
    if (root == NULL) {
        return createNode(value);
    }

    if (value < root->data) {
        root->left = insert(root->left, value);
    } else if (value > root->data) {
        root->right = insert(root->right, value);
    }
    
    return root;
}

// Function to search for a target value in the BST
struct Node* search(struct Node* root, int target) {
    // Base Cases: root is null (target not found) or target is present at root
    if (root == NULL || root->data == target) {
        return root;
    }
    
    // Target is greater than root's data, search right subtree
    if (root->data < target) {
        return search(root->right, target);
    }
    
    // Target is smaller than root's data, search left subtree
    return search(root->left, target);
}

int main() {
    int n, val, target;
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

    // Read the target value to search for
    scanf("%d", &target);

    // Search the BST for the target
    struct Node* result = search(root, target);

    // Print the outcome
    if (result != NULL) {
        printf("Found\n");
    } else {
        printf("Not Found\n");
    }

    return 0;
}