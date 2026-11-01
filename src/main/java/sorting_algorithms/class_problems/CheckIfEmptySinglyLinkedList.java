package sorting_algorithms.class_problems;

public class CheckIfEmptySinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Checks if a singly linked list is empty.
     * Time Complexity: O(1)
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @return true if empty (head is null), false otherwise
     */
    public static boolean checkIfEmpty(Node head) {
        return head == null;
    }

    public static void main(String[] args) {
        // Sample 1: head = null
        Node head1 = null;
        System.out.println(checkIfEmpty(head1));

        // Sample 2: head = 8
        Node head2 = new Node(8);
        System.out.println(checkIfEmpty(head2));
    }
}
