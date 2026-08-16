public class LowestCommonAncestorBST {

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { 
            val = x; 
        }
    }

    // Function to find the Lowest Common Ancestor
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If both p and q are smaller than root, LCA must be in the left subtree
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        
        // If both p and q are greater than root, LCA must be in the right subtree
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        // If they split, or one equals the root, the current root is the LCA
        return root;
    }

    public static void main(String[] args) {
        // Creating the BST from the classic LeetCode example:
        //
        //          6
        //        /   \
        //       2     8
        //      / \   / \
        //     0   4 7   9
        //        / \
        //       3   5

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        // Test Case 1: p = 2, q = 8
        TreeNode p1 = root.left;  // Node 2
        TreeNode q1 = root.right; // Node 8
        TreeNode lca1 = lowestCommonAncestor(root, p1, q1);
        System.out.println("LCA of " + p1.val + " and " + q1.val + " is: " + lca1.val);

        // Test Case 2: p = 2, q = 4
        TreeNode p2 = root.left;        // Node 2
        TreeNode q2 = root.left.right;  // Node 4
        TreeNode lca2 = lowestCommonAncestor(root, p2, q2);
        System.out.println("LCA of " + p2.val + " and " + q2.val + " is: " + lca2.val);
    }
}