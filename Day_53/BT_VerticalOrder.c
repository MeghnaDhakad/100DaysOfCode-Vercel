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

        if (i < n && arr[i] != -1) {
            current->left = createNode(arr[i]);
            queue[rear++] = current->left;
        }
        i++;

        if (i < n && arr[i] != -1) {
            current->right = createNode(arr[i]);
            queue[rear++] = current->right;
        }
        i++;
    }
    free(queue);
    return root;
}

// Linked list node to store multiple tree node values at the same HD
struct ListNode {
    int data;
    struct ListNode* next;
};

// Queue node for BFS traversal containing the tree node and its HD
struct QNode {
    struct Node* node;
    int hd;
};

// Function to print vertical order traversal
void printVerticalOrder(struct Node* root, int n) {
    if (root == NULL) return;

    // The maximum possible horizontal spread is n (all left or all right)
    // We use an offset of n so that an HD of -n maps to index 0.
    int offset = n;
    int min_hd = offset;
    int max_hd = offset;

    // Array of linked list pointers to store nodes at each HD
    struct ListNode** head = (struct ListNode**)calloc(2 * n + 1, sizeof(struct ListNode*));
    struct ListNode** tail = (struct ListNode**)calloc(2 * n + 1, sizeof(struct ListNode*));

    // Queue for Level Order Traversal (BFS)
    struct QNode* queue = (struct QNode*)malloc(n * sizeof(struct QNode));
    int front = 0, rear = 0;

    // Enqueue root with HD 0
    queue[rear].node = root;
    queue[rear].hd = 0;
    rear++;

    // Process nodes using BFS
    while (front < rear) {
        struct Node* curr = queue[front].node;
        int hd = queue[front].hd;
        front++;

        // Shift HD by offset to get a positive array index
        int idx = hd + offset;

        // Track the min and max bounds to print efficiently later
        if (idx < min_hd) min_hd = idx;
        if (idx > max_hd) max_hd = idx;

        // Create a new list node for this data
        struct ListNode* new_ln = (struct ListNode*)malloc(sizeof(struct ListNode));
        new_ln->data = curr->data;
        new_ln->next = NULL;

        // Append it to the linked list for this specific HD
        if (head[idx] == NULL) {
            head[idx] = new_ln;
            tail[idx] = new_ln;
        } else {
            tail[idx]->next = new_ln;
            tail[idx] = new_ln;
        }

        // Enqueue left and right children with their respective HDs
        if (curr->left != NULL) {
            queue[rear].node = curr->left;
            queue[rear].hd = hd - 1;
            rear++;
        }
        if (curr->right != NULL) {
            queue[rear].node = curr->right;
            queue[rear].hd = hd + 1;
            rear++;
        }
    }

    // Print the results column by column
    for (int i = min_hd; i <= max_hd; i++) {
        struct ListNode* temp = head[i];
        if (temp != NULL) {
            while (temp != NULL) {
                printf("%d ", temp->data);
                temp = temp->next;
            }
            printf("\n");
        }
    }

    // Clean up
    free(head);
    free(tail);
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

    // Print Vertical Order
    printVerticalOrder(root, n);

    free(arr);
    return 0;
}