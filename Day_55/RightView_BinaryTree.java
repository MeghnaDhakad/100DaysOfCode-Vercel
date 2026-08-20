import java.util.*;

public class RightView_BinaryTree {

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

    // Function to get the right side view of the binary tree
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                // If this is the last node in the current level, add it to our result
                if (i == levelSize - 1) {
                    result.add(currentNode.val);
                }
                
                // Queue up the children for the next level
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Creating the tree from your earlier example:
        //
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   6

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        // Node 3 has no left child, only a right child
        root.right.right = new TreeNode(6);

        // Call the method and print the result
        List<Integer> output = rightSideView(root);
        
        System.out.println("Right Side View: " + output);
    }
}