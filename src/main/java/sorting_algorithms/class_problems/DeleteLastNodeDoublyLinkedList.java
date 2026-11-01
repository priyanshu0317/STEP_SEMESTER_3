package sorting_algorithms.class_problems;

public class DeleteLastNodeDoublyLinkedList {

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
     * Removes the last node from a doubly linked list given only the head.
     * Time Complexity: O(n) to traverse to tail.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the doubly linked list
     * @return new head of the doubly linked list
     */
    public static DNode deleteLastNodeDoubly(DNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        DNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        // current is the last node
        current.prev.next = null;
        current.prev = null;

        return head;
    }

    public static void printList(DNode head) {
        if (head == null) {
            System.out.println("(empty list)");
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
        // Sample 1: head = 1 <-> 2 <-> 3
        DNode head1 = buildList(new int[]{1, 2, 3});
        head1 = deleteLastNodeDoubly(head1);
        printList(head1);

        // Sample 2: head = 5
        DNode head2 = buildList(new int[]{5});
        head2 = deleteLastNodeDoubly(head2);
        printList(head2);
    }
}
