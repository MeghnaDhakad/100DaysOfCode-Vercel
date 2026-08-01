package Day_36;
import java.util.*;

public class MyCircularDeque {

    int[] deque;
    int front;
    int rear;
    int size;
    int capacity;

    public MyCircularDeque(int k) {
        deque = new int[k];
        capacity = k;
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean insertFront(int value) {

        if (isFull())
            return false;

        front = (front - 1 + capacity) % capacity;
        deque[front] = value;

        if (size == 0)
            rear = front;

        size++;
        return true;
    }

    public boolean insertLast(int value) {

        if (isFull())
            return false;

        rear = (rear + 1) % capacity;
        deque[rear] = value;

        if (size == 0)
            front = rear;

        size++;
        return true;
    }

    public boolean deleteFront() {

        if (isEmpty())
            return false;

        front = (front + 1) % capacity;
        size--;

        return true;
    }

    public boolean deleteLast() {

        if (isEmpty())
            return false;

        rear = (rear - 1 + capacity) % capacity;
        size--;

        return true;
    }

    public int getFront() {

        if (isEmpty())
            return -1;

        return deque[front];
    }

    public int getRear() {

        if (isEmpty())
            return -1;

        return deque[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter capacity of deque: ");
        int k = sc.nextInt();

        MyCircularDeque deque = new MyCircularDeque(k);

        System.out.print("Enter number of operations: ");
        int n = sc.nextInt();

        System.out.println("\nOperations:");
        System.out.println("1 x -> Insert Front");
        System.out.println("2 x -> Insert Last");
        System.out.println("3   -> Delete Front");
        System.out.println("4   -> Delete Last");
        System.out.println("5   -> Get Front");
        System.out.println("6   -> Get Rear");
        System.out.println("7   -> Is Empty");
        System.out.println("8   -> Is Full");

        for (int i = 0; i < n; i++) {

            int op = sc.nextInt();

            switch (op) {

                case 1:
                    int frontValue = sc.nextInt();
                    System.out.println(deque.insertFront(frontValue));
                    break;

                case 2:
                    int rearValue = sc.nextInt();
                    System.out.println(deque.insertLast(rearValue));
                    break;

                case 3:
                    System.out.println(deque.deleteFront());
                    break;

                case 4:
                    System.out.println(deque.deleteLast());
                    break;

                case 5:
                    System.out.println(deque.getFront());
                    break;

                case 6:
                    System.out.println(deque.getRear());
                    break;

                case 7:
                    System.out.println(deque.isEmpty());
                    break;

                case 8:
                    System.out.println(deque.isFull());
                    break;

                default:
                    System.out.println("Invalid Operation");
            }
        }

        sc.close();
    }
}