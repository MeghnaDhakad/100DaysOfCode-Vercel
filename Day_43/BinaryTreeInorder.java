import java.util.*;

public class BinaryTreeInorder {

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

    // Inorder: Left -> Root -> Right
    public static void inorder(TreeNode root) {

        if (root == null) {
            return;
        }

        inorder(root.left);

        System.out.print(root.val + " ");

        inorder(root.right);
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

        System.out.print("Inorder Traversal: ");

        inorder(root);
    }
}