import java.util.*;

public class ZigzagLevelOrder {

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

    // Function to perform zigzag level order traversal
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true; // Flag to track the traversal direction
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // Using a LinkedList allows us to add elements to the front or back efficiently
            LinkedList<Integer> currentLevel = new LinkedList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                // If left to right, append to the end. If right to left, push to the front.
                if (leftToRight) {
                    currentLevel.addLast(currentNode.val);
                } else {
                    currentLevel.addFirst(currentNode.val);
                }
                
                // Queue up the next level normally
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            result.add(currentLevel);
            // Flip the direction for the next level
            leftToRight = !leftToRight;
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Creating the tree from the LeetCode example:
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
        List<List<Integer>> output = zigzagLevelOrder(root);
        
        System.out.println("Zigzag Level Order Traversal: " + output);
    }
}