public class FlattenBT {

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

    // Function to flatten the binary tree (O(1) Space Iterative Approach)
    public static void flatten(TreeNode root) {
        TreeNode curr = root;
        
        while (curr != null) {
            // If the current node has a left child, we need to move it to the right
            if (curr.left != null) {
                // Find the rightmost node in the left subtree
                TreeNode rightmost = curr.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                
                // Wire the rightmost node to the current node's original right child
                rightmost.right = curr.right;
                
                // Move the entire left subtree to the right side
                curr.right = curr.left;
                curr.left = null; // Don't forget to sever the left tie!
            }
            
            // Move to the next node on the right
            curr = curr.right;
        }
    }

    // Helper function to print the flattened tree and verify left pointers are null
    public static void printFlattenedTree(TreeNode root) {
        TreeNode curr = root;
        System.out.print("Flattened Tree: ");
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.left != null) {
                System.out.print(" (Error: left pointer is not null!) ");
            }
            if (curr.right != null) {
                System.out.print(" -> ");
            }
            curr = curr.right;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Creating the tree from the classic LeetCode example:
        //
        //        1
        //       / \
        //      2   5
        //     / \   \
        //    3   4   6

        TreeNode root = new TreeNode(1);
        
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        
        root.right.right = new TreeNode(6);

        // Call the method to modify the tree in-place
        flatten(root);

        // Print the result to verify it matches pre-order traversal
        printFlattenedTree(root);
    }
}