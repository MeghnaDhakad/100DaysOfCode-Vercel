import java.util.*;

public class ConstructTreeInPost {

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // Keep track of our current root, starting from the end of the postorder array
    private static int postorderIndex;
    // Map to store the index of each value in the inorder array for O(1) lookups
    private static Map<Integer, Integer> inorderIndexMap;

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderIndexMap = new HashMap<>();
        postorderIndex = postorder.length - 1; // Reset index to the end of postorder array
        
        // Build the hash map: Key = Node Value, Value = Index in inorder array
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        // Begin the recursive construction
        return arrayToTree(postorder, 0, inorder.length - 1);
    }
    
    private static TreeNode arrayToTree(int[] postorder, int left, int right) {
        // Base case: if there are no elements to construct the tree, return null
        if (left > right) {
            return null;
        }
        
        // The current root is the element at the current postorderIndex
        int rootValue = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootValue);
        
        // Find where this root sits in the inorder array
        int inorderIndex = inorderIndexMap.get(rootValue);
        
        // CRUCIAL: Because we are reading the postorder array backwards,
        // we MUST construct the RIGHT subtree before the LEFT subtree.
        root.right = arrayToTree(postorder, inorderIndex + 1, right);
        root.left  = arrayToTree(postorder, left, inorderIndex - 1);
        
        return root;
    }

    // Helper function to print Preorder traversal (Root, Left, Right)
    public static void printPreorder(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public static void main(String[] args) {
        // Test Case based on the classic LeetCode example:
        // Inorder:   [9, 3, 15, 20, 7]
        // Postorder: [9, 15, 7, 20, 3]
        //
        // Expected Tree Structure:
        //        3
        //       / \
        //      9  20
        //         / \
        //        15  7
        
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        // Construct the tree
        TreeNode root = buildTree(inorder, postorder);

        // Verify by printing the Preorder traversal
        // Expected Output: 3 9 20 15 7
        System.out.print("Preorder Traversal of constructed tree: ");
        printPreorder(root);
        System.out.println();
    }
}