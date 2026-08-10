import java.util.*;

public class MaximumDepth {

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

        if (root == null) {
            return 0;
        }

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {

        // Creating the tree:
        //
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("Maximum Depth: " + maxDepth(root));
    }
}