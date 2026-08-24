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
struct Node* buildTree(int in[], int post[], int inStart, int inEnd, int* postIndex) {
    // Base case: If start index is greater than end index, we've hit a dead end
    if (inStart > inEnd) {
        return NULL;
    }

    // The current root is the element at the current postIndex
    struct Node* root = createNode(post[*postIndex]);
    (*postIndex)--; // Decrement the index for the next recursive call

    // If this node has no children, return it immediately
    if (inStart == inEnd) {
        return root;
    }

    // Find the index of this root in the inorder array
    int inIndex = search(in, inStart, inEnd, root->data);

    // CRUCIAL: Because we are reading postorder backwards (Root, Right, Left),
    // we MUST construct the RIGHT subtree before the LEFT subtree.
    root->right = buildTree(in, post, inIndex + 1, inEnd, postIndex);
    root->left = buildTree(in, post, inStart, inIndex - 1, postIndex);

    return root;
}

// Function to print the Preorder traversal (Root, Left, Right)
void printPreorder(struct Node* node) {
    if (node == NULL) {
        return;
    }
    printf("%d ", node->data);
    printPreorder(node->left);
    printPreorder(node->right);
}

int main() {
    int n;
    
    // Read the number of elements
    if (scanf("%d", &n) != 1 || n <= 0) {
        return 0;
    }

    // Allocate memory for inorder and postorder arrays
    int *in = (int *)malloc(n * sizeof(int));
    int *post = (int *)malloc(n * sizeof(int));

    // Read the inorder traversal
    for (int i = 0; i < n; i++) {
        scanf("%d", &in[i]);
    }

    // Read the postorder traversal
    for (int i = 0; i < n; i++) {
        scanf("%d", &post[i]);
    }

    // Initialize the postorder index to the last element of the array
    int postIndex = n - 1;

    // Build the tree
    struct Node* root = buildTree(in, post, 0, n - 1, &postIndex);

    // Print the Preorder traversal
    printPreorder(root);
    printf("\n");

    // Clean up
    free(in);
    free(post);

    return 0;
}