package Day_66;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Initialize the graph and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] indegree = new int[numCourses];

        // 2. Build the graph and populate in-degrees
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

        // 4. Process the queue (BFS)
        int completedCourses = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            completedCourses++;

            for (int nextCourse : adj.get(current)) {
                indegree[nextCourse]--; 
                
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 5. If we took all courses, return true (no cycle)
        return completedCourses == numCourses;
    }

    // Main method to run and test the code in VS Code
    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();

        // Test Case 1: Possible to finish
        // Explanation: To take course 1 you should have finished course 0.
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println("Test Case 1 (Expect true): " + solution.canFinish(numCourses1, prerequisites1));

        // Test Case 2: Impossible to finish (Cycle)
        // Explanation: To take course 1 you should have finished course 0, 
        // and to take course 0 you should also have finished course 1.
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Test Case 2 (Expect false): " + solution.canFinish(numCourses2, prerequisites2));
        
        // Test Case 3: More complex graph, possible to finish
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Test Case 3 (Expect true): " + solution.canFinish(numCourses3, prerequisites3));
    }
}