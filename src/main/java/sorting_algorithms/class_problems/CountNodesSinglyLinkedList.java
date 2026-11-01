package sorting_algorithms.class_problems;

public class CountNodesSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Counts the total number of nodes present in a singly linked list.
     * Time Complexity: O(n) where n is the number of nodes.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @return total node count, 0 if empty
     */
    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
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
        // Sample 1: head = 5 -> 10 -> 15
        Node head1 = buildList(new int[]{5, 10, 15});
        System.out.println(countNodes(head1));

        // Sample 2: head = null
        Node head2 = null;
        System.out.println(countNodes(head2));
    }
}
