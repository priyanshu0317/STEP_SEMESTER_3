package sorting_algorithms.class_problems;

public class DeleteNthNodeSinglyLinkedList {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Deletes the node at the 1-based index n from a singly linked list.
     * Time Complexity: O(n)
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @param n    1-based index of node to delete (1 <= n <= length)
     * @return new head of the modified list
     */
    public static Node deleteNthNode(Node head, int n) {
        if (head == null) {
            return null;
        }

        if (n == 1) {
            return head.next;
        }

        Node current = head;
        // Move to the (n-1)-th node
        for (int i = 0; i < n - 2; i++) {
            if (current == null) {
                return head;
            }
            current = current.next;
        }

        if (current != null && current.next != null) {
            current.next = current.next.next;
        }

        return head;
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
        // Sample 1: head = 10 -> 20 -> 30 -> 40, n = 3
        Node head1 = buildList(new int[]{10, 20, 30, 40});
        head1 = deleteNthNode(head1, 3);
        printList(head1);

        // Sample 2: head = 10 -> 20 -> 30 -> 40, n = 1
        Node head2 = buildList(new int[]{10, 20, 30, 40});
        head2 = deleteNthNode(head2, 1);
        printList(head2);
    }
}
