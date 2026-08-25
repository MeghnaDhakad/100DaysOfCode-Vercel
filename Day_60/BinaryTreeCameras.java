public class BinaryTreeCameras {

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

    // Global variable to keep track of the number of cameras placed
    private int cameraCount = 0;

    public int minCameraCover(TreeNode root) {
        cameraCount = 0; // Reset for each new test case
        
        // If the absolute root of the tree is left uncovered (State 2),
        // we are forced to place one final camera on it.
        if (dfs(root) == 2) {
            cameraCount++;
        }
        return cameraCount;
    }

    // State definitions:
    // 0 -> Node HAS a camera
    // 1 -> Node is COVERED by a child's camera
    // 2 -> Node is NOT COVERED (needs the parent to have a camera)
    private int dfs(TreeNode node) {
        // Base case: Null nodes don't need coverage, pretend they are "covered"
        if (node == null) {
            return 1;
        }

        // Post-order traversal: evaluate children first
        int left = dfs(node.left);
        int right = dfs(node.right);

        // Case 1: If either child is NOT COVERED, we MUST place a camera here.
        if (left == 2 || right == 2) {
            cameraCount++;
            return 0; // State 0: this node now has a camera
        }

        // Case 2: If either child HAS a camera, this node is safely COVERED.
        if (left == 0 || right == 0) {
            return 1; // State 1: covered, but no camera here
        }

        // Case 3: Both children are COVERED but have NO cameras.
        // Therefore, this node is NOT COVERED. We ask its parent to place a camera.
        return 2; 
    }

    public static void main(String[] args) {
        BinaryTreeCameras solution = new BinaryTreeCameras();

        // --- Test Case 1 ---
        //
        //       0
        //      /
        //     0 (Camera goes here)
        //    / \
        //   0   0
        
        TreeNode root1 = new TreeNode(0);
        root1.left = new TreeNode(0);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(0);

        System.out.println("Test Case 1 Minimum cameras: " + solution.minCameraCover(root1)); 


        // --- Test Case 2 ---
        //
        //       0 (Camera goes here)
        //      /
        //     0 
        //    /
        //   0 (Camera goes here)
        //  /
        // 0 

        TreeNode root2 = new TreeNode(0);
        root2.left = new TreeNode(0);
        root2.left.left = new TreeNode(0);
        root2.left.left.left = new TreeNode(0);

        System.out.println("Test Case 2 Minimum cameras: " + solution.minCameraCover(root2)); 
    }
}