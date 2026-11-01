package sorting_algorithms.class_problems;

public class PrintCircularSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Prints all elements of a circular singly linked list exactly once.
     * Uses a do-while loop to correctly handle single-node and multi-node cycles.
     * Handles empty list by printing only a newline.
     *
     * Time Complexity: O(n) where n is the number of nodes.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the circular singly linked list
     */
    public static void printCircular(Node head) {
        if (head == null) {
            System.out.println();
            return;
        }

        Node current = head;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(current.data);
            current = current.next;
            if (current != head) {
                sb.append(" ");
            }
        } while (current != head);

        System.out.println(sb.toString());
    }

    public static Node buildCircularList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node tail = head;
        for (int i = 1; i < values.length; i++) {
            tail.next = new Node(values[i]);
            tail = tail.next;
        }
        tail.next = head; // close the circle
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: head = 10 -> 20 -> 30 -> (back to 10)
        Node head1 = buildCircularList(new int[]{10, 20, 30});
        printCircular(head1);

        // Sample 2: head = null
        Node head2 = null;
        printCircular(head2);
    }
}
