package linked_list.assigment_problems;

public class CancelledOrdersCleanup {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Removes all nodes with the given value from the singly linked list.
     * Time Complexity: O(n) where n is the length of the list.
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @param code value to be removed
     * @return new head of the cleaned list
     */
    public static Node removeAll(Node head, int code) {
        // 1. Remove matching nodes from the front by advancing head
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // 2. Traverse rest of list and unlink matching nodes
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.data == code) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
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
        // Sample 1: orders = 5 -> 3 -> 5 -> 8 -> 5, code = 5
        Node sample1 = buildList(new int[]{5, 3, 5, 8, 5});
        Node result1 = removeAll(sample1, 5);
        printList(result1);

        // Sample 2: orders = 5 -> 5 -> 5, code = 5
        Node sample2 = buildList(new int[]{5, 5, 5});
        Node result2 = removeAll(sample2, 5);
        printList(result2);
    }
}
