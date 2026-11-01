package sorting_algorithms.class_problems;

public class SplitSinglyLinkedListInHalf {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Splits a singly linked list into two halves using fast and slow pointers.
     * If the list has an odd number of nodes, the first half contains the extra middle node.
     * The first half is terminated with null.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) auxiliary space.
     *
     * @param head head of the singly linked list
     * @return head of the second half
     */
    public static Node splitListInHalf(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;

        // Move fast by two steps and slow by one step
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalfHead = slow.next;
        slow.next = null; // properly terminate first half

        return secondHalfHead;
    }

    public static String formatList(Node head) {
        if (head == null) return "(empty list)";
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
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
        // Sample 1: head = 1 -> 2 -> 3 -> 4 -> 5
        Node head1 = buildList(new int[]{1, 2, 3, 4, 5});
        Node second1 = splitListInHalf(head1);
        System.out.println("First half: " + formatList(head1) + " Returned: " + formatList(second1));

        // Sample 2: head = 1 -> 2 -> 3 -> 4
        Node head2 = buildList(new int[]{1, 2, 3, 4});
        Node second2 = splitListInHalf(head2);
        System.out.println("First half: " + formatList(head2) + " Returned: " + formatList(second2));
    }
}
