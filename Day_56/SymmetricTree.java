public class SymmetricTree {

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

    // Function to check if the tree is symmetric
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }
    
    // Helper function to check if two subtrees are mirrors
    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        
        return (t1.val == t2.val)
            && isMirror(t1.left, t2.right)
            && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        // --- Test Case 1: A Symmetric Tree ---
        //
        //        1
        //       / \
        //      2   2
        //     / \ / \
        //    3  4 4  3

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);

        System.out.println("Tree 1 is symmetric: " + isSymmetric(root1));


        // --- Test Case 2: An Asymmetric Tree ---
        //
        //        1
        //       / \
        //      2   2
        //       \   \
        //        3   3

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(3);

        System.out.println("Tree 2 is symmetric: " + isSymmetric(root2));
    }
}