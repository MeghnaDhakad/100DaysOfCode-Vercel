#include <stdio.h>
#include <stdlib.h>

// Definition for a binary tree node.
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

// Helper function to create a new node
struct TreeNode* createNode(int value) {
    struct TreeNode* newNode = (struct TreeNode*)malloc(sizeof(struct TreeNode));
    newNode->val = value;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

// Perform level order traversal
// returnSize: will hold the number of levels (rows)
// returnColumnSizes: will hold an array of sizes for each level (columns)
int** levelOrder(struct TreeNode* root, int* returnSize, int** returnColumnSizes) {
    *returnSize = 0;
    
    if (root == NULL) {
        *returnColumnSizes = NULL;
        return NULL;
    }

    // Allocate memory for the 2D array and column sizes array
    // (Assuming max 2000 levels for standard LeetCode constraints)
    int** result = (int**)malloc(2000 * sizeof(int*));
    *returnColumnSizes = (int*)malloc(2000 * sizeof(int));

    // Simulate a queue using an array of Node pointers
    struct TreeNode** queue = (struct TreeNode**)malloc(2000 * sizeof(struct TreeNode*));
    int front = 0;
    int rear = 0;

    // Enqueue root
    queue[rear++] = root;

    while (front < rear) {
        int levelSize = rear - front; // Number of nodes at the current level
        
        // Allocate memory for the current level's array
        result[*returnSize] = (int*)malloc(levelSize * sizeof(int));
        (*returnColumnSizes)[*returnSize] = levelSize;

        // Process all nodes at the current level
        for (int i = 0; i < levelSize; i++) {
            struct TreeNode* currentNode = queue[front++]; // Dequeue
            
            result[*returnSize][i] = currentNode->val;

            // Enqueue children for the next level
            if (currentNode->left != NULL) {
                queue[rear++] = currentNode->left;
            }
            if (currentNode->right != NULL) {
                queue[rear++] = currentNode->right;
            }
        }
        (*returnSize)++; // Move to the next level
    }

    free(queue); // Clean up the queue
    return result;
}

int main() {
    // Creating the tree:
    //
    //        3
    //       / \
    //      9  20
    //         / \
    //        15  7

    struct TreeNode* root = createNode(3);
    
    root->left = createNode(9);
    root->right = createNode(20);
    
    root->right->left = createNode(15);
    root->right->right = createNode(7);

    // Variables required by the LeetCode signature
    int returnSize;
    int* returnColumnSizes;

    int** output = levelOrder(root, &returnSize, &returnColumnSizes);

    // Print the result in a 2D list format
    printf("Output: [");
    for (int i = 0; i < returnSize; i++) {
        printf("[");
        for (int j = 0; j < returnColumnSizes[i]; j++) {
            printf("%d", output[i][j]);
            if (j < returnColumnSizes[i] - 1) {
                printf(", ");
            }
        }
        printf("]");
        if (i < returnSize - 1) {
            printf(", ");
        }
    }
    printf("]\n");

    // Clean up allocated memory
    for (int i = 0; i < returnSize; i++) {
        free(output[i]);
    }
    free(output);
    free(returnColumnSizes);

    return 0;
}