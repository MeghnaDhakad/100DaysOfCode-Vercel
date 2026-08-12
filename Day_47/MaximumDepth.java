public class MaximumDepth {

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

    // Find maximum depth
    public static int maxDepth(TreeNode root) {
        // Base case: an empty tree has a depth of 0
        if (root == null) {
            return 0;
        }

        // Recursively calculate the depth of the left and right subtrees
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        // The max depth is the greater of the two heights, plus 1 for the current node
        return 1 + Math.max(leftHeight, rightHeight);
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
        System.out.println("Maximum Depth: " + maxDepth(root));
    }
}