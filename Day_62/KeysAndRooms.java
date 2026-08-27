import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class KeysAndRooms {

    // Function to check if all rooms can be visited
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        
        // Start our DFS from room 0
        dfs(rooms, visited, 0);
        
        // Check if there are any rooms we couldn't reach
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false;
            }
        }
        
        return true;
    }
    
    // Depth-First Search helper method
    private static void dfs(List<List<Integer>> rooms, boolean[] visited, int currentRoom) {
        // Mark the current room as visited
        visited[currentRoom] = true;
        
        // Pick up all the keys in this room and try to open their corresponding doors
        for (int nextRoomKey : rooms.get(currentRoom)) {
            // Only visit the next room if we haven't been there yet
            if (!visited[nextRoomKey]) {
                dfs(rooms, visited, nextRoomKey);
            }
        }
    }

    public static void main(String[] args) {
        // --- Test Case 1 ---
        // Room 0 has key for 1
        // Room 1 has key for 2
        // Room 2 has key for 3
        // Room 3 is empty
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(Arrays.asList(1));
        rooms1.add(Arrays.asList(2));
        rooms1.add(Arrays.asList(3));
        rooms1.add(Arrays.asList()); // Room 3 has no keys

        System.out.println("Test Case 1 Output: " + canVisitAllRooms(rooms1)); 
        // Expected Output: true

        // --- Test Case 2 ---
        // Room 0 has keys for 1, 3
        // Room 1 has keys for 3, 0, 1
        // Room 2 has key for 2 (which means you can never enter room 2 from the outside)
        // Room 3 has key for 0
        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));
        rooms2.add(Arrays.asList(3, 0, 1));
        rooms2.add(Arrays.asList(2));
        rooms2.add(Arrays.asList(0));

        System.out.println("Test Case 2 Output: " + canVisitAllRooms(rooms2)); 
        // Expected Output: false
    }
}