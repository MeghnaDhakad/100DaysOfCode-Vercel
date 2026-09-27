public class InsertionSortList {

    // Definition for singly-linked list
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        while (curr != null) {
            ListNode prev = dummy;
            
            while (prev.next != null && prev.next.val < curr.val) {
                prev = prev.next;
            }
            
            ListNode nextNode = curr.next;
            
            curr.next = prev.next;
            prev.next = curr;
            
            curr = nextNode;
        }

        return dummy.next;
    }

    // Helper method to print the linked list
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        InsertionSortList solution = new InsertionSortList();

        // Test Case 1: [4, 2, 1, 3]
        ListNode head1 = new ListNode(4);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(3);
        
        System.out.println("Test Case 1:");
        System.out.print("Original: ");
        printList(head1);
        ListNode sorted1 = solution.insertionSortList(head1);
        System.out.print("Sorted:   ");
        printList(sorted1);
        System.out.println();

        // Test Case 2: [-1, 5, 3, 4, 0]
        ListNode head2 = new ListNode(-1);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(0);

        System.out.println("Test Case 2:");
        System.out.print("Original: ");
        printList(head2);
        ListNode sorted2 = solution.insertionSortList(head2);
        System.out.print("Sorted:   ");
        printList(sorted2);
    }
}