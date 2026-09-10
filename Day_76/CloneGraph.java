import java.util.*;

public class CloneGraph {

    // 1. Definition for a Node
    static class Node {
        public int val;
        public List<Node> neighbors;
        
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // 2. The Algorithm
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (visited.containsKey(node)) return visited.get(node);

        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    // 3. Helper to build and print the graph for testing
    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();

        // Create a cyclic graph: 1 -- 2
        //                        |    |
        //                        4 -- 3
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.addAll(Arrays.asList(node2, node4));
        node2.neighbors.addAll(Arrays.asList(node1, node3));
        node3.neighbors.addAll(Arrays.asList(node2, node4));
        node4.neighbors.addAll(Arrays.asList(node1, node3));

        // Clone it
        Node clonedGraph = solution.cloneGraph(node1);

        // Verification
        System.out.println("Are the original and clone the exact same object in memory? " + (node1 == clonedGraph));
        System.out.println("Do they have the same value? " + (node1.val == clonedGraph.val));
        System.out.print("Cloned Node 1 Neighbors: ");
        for (Node n : clonedGraph.neighbors) {
            System.out.print(n.val + " ");
        }
        System.out.println();
    }
}