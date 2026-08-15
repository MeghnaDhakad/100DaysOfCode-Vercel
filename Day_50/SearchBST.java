public class SearchBST {

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

    // Function to search in a BST
    public static TreeNode searchBST(TreeNode root, int val) {
        // Base case: If we hit a dead end (null) or find the target value
        if (root == null || root.val == val) {
            return root;
        }
        
        // If the target value is smaller than the current node's value, search left
        if (val < root.val) {
            return searchBST(root.left, val);
        }
        
        // If the target value is larger than the current node's value, search right
        return searchBST(root.right, val);
    }

    // Helper function to print the tree in Preorder (Root, Left, Right)
    // This helps us verify we got the correct subtree back.
    public static void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        // Creating the initial BST:
        //
        //        4
        //       / \
        //      2   7
        //     / \
        //    1   3

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        int target = 2;
        System.out.println("Searching for: " + target);
        
        // Search the BST
        TreeNode result = searchBST(root, target);

        // Print the results
        if (result != null) {
            System.out.print("Found! Subtree preorder traversal: ");
            printPreorder(result);
            System.out.println();
        } else {
            System.out.println("Target " + target + " not found in the BST.");
        }

        // Test with a value that doesn't exist
        int missingTarget = 5;
        System.out.println("\nSearching for: " + missingTarget);
        TreeNode missingResult = searchBST(root, missingTarget);
        
        if (missingResult != null) {
            printPreorder(missingResult);
        } else {
            System.out.println("Target " + missingTarget + " not found in the BST.");
        }
    }
}