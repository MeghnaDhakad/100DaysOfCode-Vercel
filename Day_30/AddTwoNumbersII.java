package Day_30;
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class AddTwoNumbersII {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        // Store first list in stack
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }

        // Store second list in stack
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;

        while (!s1.isEmpty() || !s2.isEmpty() || carry != 0) {

            int sum = carry;

            if (!s1.isEmpty()) {
                sum += s1.pop();
            }

            if (!s2.isEmpty()) {
                sum += s2.pop();
            }

            carry = sum / 10;

            ListNode newNode = new ListNode(sum % 10);

            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    // Create linked list from input
    public static ListNode createList(Scanner sc, int n) {

        if (n == 0) {
            return null;
        }

        ListNode head = new ListNode(sc.nextInt());
        ListNode temp = head;

        for (int i = 1; i < n; i++) {
            temp.next = new ListNode(sc.nextInt());
            temp = temp.next;
        }

        return head;
    }

    // Print linked list
    public static void printList(ListNode head) {

        while (head != null) {
            System.out.print(head.val);

            if (head.next != null) {
                System.out.print(" ");
            }

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of nodes in first list: ");
        int n = sc.nextInt();

        System.out.println("Enter first list:");
        ListNode l1 = createList(sc, n);

        System.out.print("Enter number of nodes in second list: ");
        int m = sc.nextInt();

        System.out.println("Enter second list:");
        ListNode l2 = createList(sc, m);

        ListNode result = addTwoNumbers(l1, l2);

        System.out.println("Result:");
        printList(result);

        sc.close();
    }
}