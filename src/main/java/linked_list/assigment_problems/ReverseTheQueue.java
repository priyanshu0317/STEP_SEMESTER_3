package linked_list.assigment_problems;

public class ReverseTheQueue {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Reverses the singly linked list in place using three pointers.
     * Time Complexity: O(n) where n is the number of nodes.
     * Space Complexity: O(1) auxiliary space (no new nodes created).
     *
     * @param head head of the singly linked list
     * @return new head of the reversed list
     */
    public static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
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
        // Sample 1: queue = 1 -> 2 -> 3 -> 4
        Node sample1 = buildList(new int[]{1, 2, 3, 4});
        Node rev1 = reverse(sample1);
        printList(rev1);

        // Sample 2: queue = 9
        Node sample2 = buildList(new int[]{9});
        Node rev2 = reverse(sample2);
        printList(rev2);
    }
}
