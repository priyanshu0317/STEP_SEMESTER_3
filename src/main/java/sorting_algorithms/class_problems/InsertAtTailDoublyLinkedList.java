package sorting_algorithms.class_problems;

public class InsertAtTailDoublyLinkedList {

    public static class DNode {
        public int data;
        public DNode prev;
        public DNode next;

        public DNode(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Inserts a new node at the tail of a doubly linked list given the head.
     * Time Complexity: O(n) to traverse to tail.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head  head reference of the doubly linked list
     * @param value integer value to insert
     * @return head of the updated doubly linked list
     */
    public static DNode insertAtTailDoubly(DNode head, int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            return newNode;
        }

        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        newNode.prev = current;

        return head;
    }

    public static void printList(DNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        StringBuilder sb = new StringBuilder();
        DNode curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static DNode buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        DNode head = new DNode(values[0]);
        DNode curr = head;
        for (int i = 1; i < values.length; i++) {
            DNode node = new DNode(values[i]);
            curr.next = node;
            node.prev = curr;
            curr = node;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: head = 1 <-> 2 <-> 3, value = 4
        DNode head1 = buildList(new int[]{1, 2, 3});
        head1 = insertAtTailDoubly(head1, 4);
        printList(head1);

        // Sample 2: head = null, value = 7
        DNode head2 = null;
        head2 = insertAtTailDoubly(head2, 7);
        printList(head2);
    }
}
