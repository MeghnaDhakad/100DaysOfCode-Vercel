public class InsertIntoBST {

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

    // Function to insert a value into the BST
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        // Base case: If we find an empty spot, create and return the new node
        if (root == null) {
            return new TreeNode(val);
        }
        
        // If the value to insert is less than the current node's value, go left
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } 
        // If the value to insert is greater than the current node's value, go right
        else {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }

    // Helper function to print the tree in Inorder (Left, Root, Right)
    // A correct BST will print in strictly ascending order.
    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
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

        System.out.print("Inorder before insertion: ");
        inorderTraversal(root);
        System.out.println(); // New line

        // Insert the value 5
        // It should drop to the left child of 7
        //
        //        4
        //       / \
        //      2   7
        //     / \ /
        //    1  3 5   <-- New node
        
        int valToInsert = 5;
        root = insertIntoBST(root, valToInsert);

        System.out.print("Inorder after inserting 5: ");
        inorderTraversal(root);
        System.out.println(); // New line
    }
}