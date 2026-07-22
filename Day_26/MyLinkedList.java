import java.util.*;

public class MyLinkedList {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    public int get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.val;
    }

    public void addAtHead(int val) {

        ListNode newNode = new ListNode(val);

        newNode.next = head;
        head = newNode;

        size++;
    }

    public void addAtTail(int val) {

        ListNode newNode = new ListNode(val);

        if (head == null) {
            head = newNode;
        } else {
            ListNode curr = head;

            while (curr.next != null) {
                curr = curr.next;
            }

            curr.next = newNode;
        }

        size++;
    }

    public void addAtIndex(int index, int val) {

        if (index < 0 || index > size) {
            return;
        }

        if (index == 0) {
            addAtHead(val);
            return;
        }

        ListNode curr = head;

        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        ListNode newNode = new ListNode(val);

        newNode.next = curr.next;
        curr.next = newNode;

        size++;
    }

    public void deleteAtIndex(int index) {

        if (index < 0 || index >= size) {
            return;
        }

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        ListNode curr = head;

        for (int i = 0; i < index - 1; i++) {
            curr = curr.next;
        }

        curr.next = curr.next.next;

        size--;
    }

    public void printList() {

        ListNode curr = head;

        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        MyLinkedList list = new MyLinkedList();

        System.out.print("Enter number of operations: ");
        int n = sc.nextInt();

        System.out.println("\nOperations:");
        System.out.println("1 val           -> Add at Head");
        System.out.println("2 val           -> Add at Tail");
        System.out.println("3 index val     -> Add at Index");
        System.out.println("4 index         -> Delete at Index");
        System.out.println("5 index         -> Get Value");
        System.out.println();

        for (int i = 0; i < n; i++) {

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    list.addAtHead(sc.nextInt());
                    break;

                case 2:
                    list.addAtTail(sc.nextInt());
                    break;

                case 3:
                    list.addAtIndex(sc.nextInt(), sc.nextInt());
                    break;

                case 4:
                    list.deleteAtIndex(sc.nextInt());
                    break;

                case 5:
                    System.out.println(list.get(sc.nextInt()));
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }

        System.out.print("\nFinal Linked List: ");
        list.printList();

        sc.close();
    }
}