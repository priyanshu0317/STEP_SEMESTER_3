package sorting_algorithms.class_problems;

public class ReverseFirstKNodesSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Reverses only the first k nodes of a singly linked list in place.
     * The remaining nodes remain in their original relative order.
     *
     * Time Complexity: O(k)
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @param k    number of nodes to reverse from front (1 <= k <= length)
     * @return new head of the modified list
     */
    public static Node reverseFirstKNodes(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node prev = null;
        Node curr = head;

        for (int i = 0; i < k && curr != null; i++) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Connect the tail of the reversed portion (original head) to the (k+1)-th node
        head.next = curr;

        return prev;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("(empty list)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: head = 1 -> 2 -> 3 -> 4 -> 5, k = 3
        Node head1 = buildList(new int[]{1, 2, 3, 4, 5});
        head1 = reverseFirstKNodes(head1, 3);
        printList(head1);

        // Sample 2: head = 1 -> 2 -> 3 -> 4 -> 5, k = 5
        Node head2 = buildList(new int[]{1, 2, 3, 4, 5});
        head2 = reverseFirstKNodes(head2, 5);
        printList(head2);
    }
}
