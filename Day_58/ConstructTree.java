package Day_58;
import java.util.*;

public class ConstructTree {

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

    // Keep track of our current root in the preorder array
    private static int preorderIndex = 0;
    // Map to store the index of each value in the inorder array for O(1) lookups
    private static Map<Integer, Integer> inorderIndexMap;

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();
        preorderIndex = 0; // Reset index in case buildTree is called multiple times
        
        // Build the hash map: Key = Node Value, Value = Index in inorder array
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        
        // Begin the recursive construction
        return arrayToTree(preorder, 0, inorder.length - 1);
    }
    
    private static TreeNode arrayToTree(int[] preorder, int left, int right) {
        // Base case: if there are no elements to construct the tree, return null
        if (left > right) {
            return null;
        }
        
        // The current root is always the next element in the preorder array
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);
        
        // Find where this root sits in the inorder array
        int inorderIndex = inorderIndexMap.get(rootValue);
        
        // Build the left and right subtrees
        root.left = arrayToTree(preorder, left, inorderIndex - 1);
        root.right = arrayToTree(preorder, inorderIndex + 1, right);
        
        return root;
    }

    // Helper function to print Postorder traversal (Left, Right, Root)
    public static void printPostorder(TreeNode node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.val + " ");
    }

    public static void main(String[] args) {
        // Test Case based on the classic LeetCode example:
        // Preorder: [3, 9, 20, 15, 7]
        // Inorder:  [9, 3, 15, 20, 7]
        //
        // Expected Tree Structure:
        //        3
        //       / \
        //      9  20
        //         / \
        //        15  7
        
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        // Construct the tree
        TreeNode root = buildTree(preorder, inorder);

        // Verify by printing the Postorder traversal
        // Expected Output: 9 15 7 20 3
        System.out.print("Postorder Traversal of constructed tree: ");
        printPostorder(root);
        System.out.println();
    }
}