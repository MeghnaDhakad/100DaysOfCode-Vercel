import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AlienDictionary {

    public String findOrder(String[] words) {
        // Step 1: Initialize adjacency list and in-degree map for all unique characters
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                inDegree.putIfAbsent(c, 0);
            }
        }
        
        // Step 2: Build the graph
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int len = Math.min(word1.length(), word2.length());
            boolean mismatchFound = false;
            
            for (int j = 0; j < len; j++) {
                char u = word1.charAt(j);
                char v = word2.charAt(j);
                
                // If we find the first mismatch, we establish a directed edge
                if (u != v) {
                    if (!adj.get(u).contains(v)) {
                        adj.get(u).add(v);
                        inDegree.put(v, inDegree.get(v) + 1);
                    }
                    mismatchFound = true;
                    break; 
                }
            }
            
            // Edge Case: If no mismatch was found but word1 is longer than word2
            // e.g., ["abcd", "abc"]. This is an invalid dictionary order.
            if (!mismatchFound && word1.length() > word2.length()) {
                return "";
            }
        }
        
        // Step 3: Topological Sort using Kahn's Algorithm (BFS)
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        
        StringBuilder alienOrder = new StringBuilder();
        
        while (!queue.isEmpty()) {
            char current = queue.poll();
            alienOrder.append(current);
            
            for (char neighbor : adj.get(current)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // Step 4: Check for cyclic dependencies
        // If the resulting string length is less than unique characters, a cycle exists
        if (alienOrder.length() < inDegree.size()) {
            return "";
        }
        
        return alienOrder.toString();
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        AlienDictionary solution = new AlienDictionary();

        // Test Case 1: Valid dictionary
        String[] words1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println("Test Case 1:");
        System.out.println("Words: [\"baa\", \"abcd\", \"abca\", \"cab\", \"cad\"]");
        System.out.println("Output: " + solution.findOrder(words1)); 
        // Note: Multiple valid topological sorts exist, e.g., "bdac"
        System.out.println();

        // Test Case 2: Valid dictionary
        String[] words2 = {"caa", "aaa", "aab"};
        System.out.println("Test Case 2:");
        System.out.println("Words: [\"caa\", \"aaa\", \"aab\"]");
        System.out.println("Output: " + solution.findOrder(words2)); 
        // Output should be "cab"
        System.out.println();
        
        // Test Case 3: Impossible schedule (Contradiction/Cycle)
        // 'a' before 'e' and 'e' before 'a'
        String[] words3 = {"ab", "cd", "ef", "ad"};
        System.out.println("Test Case 3 (Contradiction):");
        System.out.println("Words: [\"ab\", \"cd\", \"ef\", \"ad\"]");
        System.out.println("Output: \"" + solution.findOrder(words3) + "\" (Expected empty string)");
        System.out.println();

        // Test Case 4: Impossible schedule (Prefix order violation)
        // A longer word cannot appear before a shorter word if the shorter is a prefix
        String[] words4 = {"abcd", "abc"};
        System.out.println("Test Case 4 (Prefix Violation):");
        System.out.println("Words: [\"abcd\", \"abc\"]");
        System.out.println("Output: \"" + solution.findOrder(words4) + "\" (Expected empty string)");
    }
}