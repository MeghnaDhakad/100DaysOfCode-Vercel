#include <stdio.h>
#include <stdlib.h>

// Definition for a binary tree node
struct Node {
    int data;
    struct Node *left;
    struct Node *right;
};

// Helper function to create a new tree node
struct Node* createNode(int value) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

// Function to find the index of a value in the inorder array
int search(int arr[], int start, int end, int value) {
    for (int i = start; i <= end; i++) {
        if (arr[i] == value) {
            return i;
        }
    }
    return -1;
}

// Recursive function to construct the binary tree
struct Node* buildTree(int in[], int pre[], int inStart, int inEnd, int* preIndex) {
    // Base case: If start index is greater than end index, we've hit a dead end
    if (inStart > inEnd) {
        return NULL;
    }

    // The current root is the next element in the preorder array
    struct Node* root = createNode(pre[*preIndex]);
    (*preIndex)++; // Increment the preorder index for the next recursive call

    // If this node has no children (start == end), return it immediately
    if (inStart == inEnd) {
        return root;
    }

    // Find the index of this root in the inorder array
    int inIndex = search(in, inStart, inEnd, root->data);

    // Recursively build the left and right subtrees
    // Elements to the left of inIndex in inorder[] form the left subtree
    root->left = buildTree(in, pre, inStart, inIndex - 1, preIndex);
    // Elements to the right of inIndex in inorder[] form the right subtree
    root->right = buildTree(in, pre, inIndex + 1, inEnd, preIndex);

    return root;
}

// Function to print the Postorder traversal (Left, Right, Root)
void printPostorder(struct Node* node) {
    if (node == NULL) {
        return;
    }
    printPostorder(node->left);
    printPostorder(node->right);
    printf("%d ", node->data);
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1 || n <= 0) {
        return 0;
    }

    // Allocate memory for preorder and inorder arrays
    int *pre = (int *)malloc(n * sizeof(int));
    int *in = (int *)malloc(n * sizeof(int));

    // Read the preorder traversal
    for (int i = 0; i < n; i++) {
        scanf("%d", &pre[i]);
    }

    // Read the inorder traversal
    for (int i = 0; i < n; i++) {
        scanf("%d", &in[i]);
    }

    // Initialize the preorder index to 0
    int preIndex = 0;

    // Build the tree
    struct Node* root = buildTree(in, pre, 0, n - 1, &preIndex);

    // Print the Postorder traversal
    printPostorder(root);
    printf("\n");

    // Clean up
    free(pre);
    free(in);

    return 0;
}