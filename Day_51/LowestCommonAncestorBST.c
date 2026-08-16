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

// Function to find the Lowest Common Ancestor (LCA) in a BST
struct Node* findLCA(struct Node* root, int n1, int n2) {
    if (root == NULL) {
        return NULL;
    }

    // If both n1 and n2 are strictly smaller than root, LCA must be in the left subtree
    if (root->data > n1 && root->data > n2) {
        return findLCA(root->left, n1, n2);
    }
    
    // If both n1 and n2 are strictly greater than root, LCA must be in the right subtree
    if (root->data < n1 && root->data < n2) {
        return findLCA(root->right, n1, n2);
    }
    
    // If one is smaller and one is greater (or one is equal to root), 
    // the current root is the Lowest Common Ancestor.
    return root;
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1) {
        return 0;
    }

    struct Node* root = NULL;
    int val;

    // Read the elements and insert them into the BST
    for (int i = 0; i < n; i++) {
        scanf("%d", &val);
        root = insert(root, val);
    }

    int n1, n2;
    // Read the two target nodes
    scanf("%d %d", &n1, &n2);

    // Find and print the LCA
    struct Node* lca = findLCA(root, n1, n2);
    
    if (lca != NULL) {
        printf("%d\n", lca->data);
    } else {
        printf("LCA not found\n");
    }

    return 0;
}