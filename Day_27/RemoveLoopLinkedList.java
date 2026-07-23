import java.util.*;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class RemoveLoopLinkedList {

    // Remove loop using Floyd's Cycle Detection
    public static void removeLoop(Node head) {

        if (head == null || head.next == null)
            return;

        Node slow = head;
        Node fast = head;

        // Detect loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                break;
        }

        // No loop found
        if (fast == null || fast.next == null)
            return;

        // Loop starts at head
        if (slow == head) {
            while (fast.next != head) {
                fast = fast.next;
            }
            fast.next = null;
            return;
        }

        // Find the start of the loop
        slow = head;
        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove loop
        fast.next = null;
    }

    // Print linked list safely
    public static void printList(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        if (n == 0) {
            System.out.println("Linked List is empty.");
            return;
        }

        Node[] nodes = new Node[n];

        System.out.println("Enter node values:");

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(sc.nextInt());
        }

        // Connect nodes
        for (int i = 0; i < n - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }

        System.out.print("Enter index where loop starts (-1 for no loop): ");
        int pos = sc.nextInt();

        if (pos >= 0 && pos < n) {
            nodes[n - 1].next = nodes[pos];
        }

        Node head = nodes[0];

        removeLoop(head);

        System.out.println("Linked List after removing loop:");
        printList(head);

        sc.close();
    }
}