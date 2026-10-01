import java.util.Arrays;

public class MeetingRoomsTwo {

    public int minMeetingRooms(int[] start, int[] end) {
        // Step 1: Sort both arrays independently
        Arrays.sort(start);
        Arrays.sort(end);
        
        int i = 0; // Pointer for start times
        int j = 0; // Pointer for end times
        
        int currentRooms = 0;
        int maxRooms = 0;
        
        // Step 2: Simulate the chronological flow of meetings
        while (i < start.length) {
            
            // A meeting starts before the earliest ending meeting finishes
            if (start[i] < end[j]) {
                currentRooms++;
                maxRooms = Math.max(maxRooms, currentRooms);
                i++;
            } 
            // A meeting finishes, freeing up a room
            // (Notice the >= handles meetings starting exactly as one ends)
            else {
                currentRooms--;
                j++;
            }
        }
        
        return maxRooms;
    }

    public static void main(String[] args) {
        MeetingRoomsTwo solution = new MeetingRoomsTwo();

        // Test Case 1
        int[] start1 = {1, 10, 7};
        int[] end1 = {4, 15, 10};
        System.out.println("Test Case 1:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.minMeetingRooms(start1, end1));
        System.out.println();

        // Test Case 2
        int[] start2 = {2, 9, 6};
        int[] end2 = {4, 12, 10};
        System.out.println("Test Case 2:");
        System.out.println("Expected: 2");
        System.out.println("Actual:   " + solution.minMeetingRooms(start2, end2));
        System.out.println();
        
        // Test Case 3: Edge case where a meeting starts exactly when another ends
        int[] start3 = {1, 2, 3};
        int[] end3 = {2, 3, 4};
        System.out.println("Test Case 3:");
        System.out.println("Expected: 1");
        System.out.println("Actual:   " + solution.minMeetingRooms(start3, end3));
    }
}