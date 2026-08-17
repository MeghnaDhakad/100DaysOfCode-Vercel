public class LowestCommonAncestorBT {

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
        // Base case: if we hit the bottom of a branch, or we find one of the targets
        if (root == null || root == p || root == q) {
            return root;
        }
        
        // Recursively search the left and right subtrees for p and q
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);
        
        // If both left and right return a node, p and q are split across 
        // the two branches. This makes the current node the LCA!
        if (leftLCA != null && rightLCA != null) {
            return root;
        }
        
        // If only one side found a target (or both found nothing), 
        // return the non-null result to pass it up the chain.
        return leftLCA != null ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {
        // Creating the BT from the classic LeetCode example:
        //
        //          3
        //        /   \
        //       5     1
        //      / \   / \
        //     6   2 0   8
        //        / \
        //       7   4

        TreeNode root = new TreeNode(3);
        
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        // Test Case 1: p = 5, q = 1
        TreeNode p1 = root.left;  // Node 5
        TreeNode q1 = root.right; // Node 1
        TreeNode lca1 = lowestCommonAncestor(root, p1, q1);
        System.out.println("LCA of " + p1.val + " and " + q1.val + " is: " + lca1.val);

        // Test Case 2: p = 5, q = 4
        // (Testing when one node is a descendant of the other)
        TreeNode p2 = root.left;              // Node 5
        TreeNode q2 = root.left.right.right;  // Node 4
        TreeNode lca2 = lowestCommonAncestor(root, p2, q2);
        System.out.println("LCA of " + p2.val + " and " + q2.val + " is: " + lca2.val);
    }
}
