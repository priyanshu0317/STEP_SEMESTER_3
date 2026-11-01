package sorting_algorithms.class_problems;

public class InsertAtEndCircularSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Inserts a new node at the logical end of a circular singly linked list.
     * The new node's next points to head, maintaining circularity.
     *
     * Time Complexity: O(n) where n is the number of nodes.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head  head of the circular singly linked list
     * @param value integer value to insert at the end
     * @return head of the circular list
     */
    public static Node insertAtEndCircular(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }

        Node current = head;
        while (current.next != head) {
            current = current.next;
        }

        current.next = newNode;
        newNode.next = head;

        return head;
    }

    public static void printCircular(Node head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        do {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        } while (curr != head);
        sb.append("(back to ").append(head.data).append(")");
        System.out.println(sb.toString());
    }

    public static Node buildCircularList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        curr.next = head;
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: head = 1 -> 2 -> 3 -> (back to 1), value = 4
        Node head1 = buildCircularList(new int[]{1, 2, 3});
        head1 = insertAtEndCircular(head1, 4);
        printCircular(head1);

        // Sample 2: head = null, value = 9
        Node head2 = null;
        head2 = insertAtEndCircular(head2, 9);
        printCircular(head2);
    }
}
