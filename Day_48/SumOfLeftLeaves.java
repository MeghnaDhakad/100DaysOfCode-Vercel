public class SumOfLeftLeaves {

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

    // Function to calculate the sum of left leaves
    public static int sumOfLeftLeaves(TreeNode root) {
        // Base case: if the tree is empty
        if (root == null) {
            return 0;
        }
        
        int sum = 0;
        
        // Check if the current node has a left child, AND if that left child is a leaf
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        } else {
            // Otherwise, keep searching down the left subtree
            sum += sumOfLeftLeaves(root.left);
        }
        
        // Always search the right subtree (right children can have left leaves too)
        sum += sumOfLeftLeaves(root.right);
        
        return sum;
    }

    public static void main(String[] args) {
        // Creating the tree:
        //
        //        3
        //       / \
        //      9  20
        //         / \
        //        15  7

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Call the method and print the result
        // The left leaves are 9 and 15. (9 + 15 = 24)
        System.out.println("Sum of Left Leaves: " + sumOfLeftLeaves(root));
    }
}