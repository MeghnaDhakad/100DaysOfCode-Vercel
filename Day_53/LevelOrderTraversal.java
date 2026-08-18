import java.util.*;

public class LevelOrderTraversal {

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

    // Perform level order traversal
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }

        // Initialize a queue to keep track of nodes at the current level
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                // Add left and right children to the queue for the next level
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            
            // Add the current level's values to our final result array
            result.add(currentLevel);
        }

        return result;
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

        // Call the method and store the result
        List<List<Integer>> output = levelOrder(root);
        
        // Print the result
        System.out.println("Level Order Traversal: " + output);
    }
}