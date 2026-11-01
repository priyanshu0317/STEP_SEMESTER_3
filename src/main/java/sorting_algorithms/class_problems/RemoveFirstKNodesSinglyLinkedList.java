package sorting_algorithms.class_problems;

public class RemoveFirstKNodesSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Removes the first k nodes from a singly linked list.
     * Time Complexity: O(min(k, n)) where n is the number of nodes.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @param k    number of nodes to remove from front
     * @return new head of the modified list, or null if empty
     */
    public static Node removeFirstKNodes(Node head, int k) {
        if (head == null || k <= 0) {
            return head;
        }

        Node current = head;
        while (current != null && k > 0) {
            current = current.next;
            k--;
        }

        return current;
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
        // Sample 1: head = 1 -> 2 -> 3 -> 4 -> 5, k = 2
        Node head1 = buildList(new int[]{1, 2, 3, 4, 5});
        head1 = removeFirstKNodes(head1, 2);
        printList(head1);

        // Sample 2: head = 1 -> 2, k = 5
        Node head2 = buildList(new int[]{1, 2});
        head2 = removeFirstKNodes(head2, 5);
        printList(head2);
    }
}
