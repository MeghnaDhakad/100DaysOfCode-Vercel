import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. Initialize the graph and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] indegree = new int[numCourses];

        // 2. Build the graph (prerequisite -> course)
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prerequisite = pre[1];
            
            adj.get(prerequisite).add(course);
            indegree[course]++;
        }

        // 3. Add all courses with 0 in-degree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. Process the queue and build the output array
        int[] order = new int[numCourses];
        int index = 0; 

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current; // Add the free course to our schedule

            for (int nextCourse : adj.get(current)) {
                indegree[nextCourse]--; 
                
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 5. Check for cycles
        if (index == numCourses) {
            return order;
        } else {
            return new int[0];
        }
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        CourseScheduleII solution = new CourseScheduleII();

        // Test Case 1: Simple chain
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("Test Case 1:");
        System.out.println("Expected: [0, 1]");
        System.out.println("Actual:   " + Arrays.toString(solution.findOrder(numCourses1, prerequisites1)));
        System.out.println();

        // Test Case 2: Multiple prerequisites
        int numCourses2 = 4;
        int[][] prerequisites2 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Test Case 2:");
        // Note: Multiple valid topological sorts can exist (e.g., [0, 1, 2, 3] or [0, 2, 1, 3])
        System.out.println("Expected: [0, 1, 2, 3] or [0, 2, 1, 3]");
        System.out.println("Actual:   " + Arrays.toString(solution.findOrder(numCourses2, prerequisites2)));
        System.out.println();
        
        // Test Case 3: Impossible schedule (Cycle)
        int numCourses3 = 2;
        int[][] prerequisites3 = {{1, 0}, {0, 1}};
        System.out.println("Test Case 3 (Cycle):");
        System.out.println("Expected: []");
        System.out.println("Actual:   " + Arrays.toString(solution.findOrder(numCourses3, prerequisites3)));
    }
}