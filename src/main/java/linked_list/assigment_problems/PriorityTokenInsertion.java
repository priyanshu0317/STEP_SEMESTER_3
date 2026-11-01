package linked_list.assigment_problems;

public class PriorityTokenInsertion {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class SinglyLinkedList {
        private Node head;
        private int size;

        public SinglyLinkedList() {
            this.head = null;
            this.size = 0;
        }

        public Node getHead() {
            return head;
        }

        public int size() {
            return size;
        }

        /**
         * Adds a token at the front of the list (priority).
         * Time Complexity: O(1)
         */
        public void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
            size++;
        }

        /**
         * Adds a token at the end of the list (normal).
         * Time Complexity: O(n)
         */
        public void addLast(int token) {
            Node newNode = new Node(token);
            if (head == null) {
                head = newNode;
            } else {
                Node curr = head;
                while (curr.next != null) {
                    curr = curr.next;
                }
                curr.next = newNode;
            }
            size++;
        }

        /**
         * Inserts a token at a given 0-based index (rescheduled).
         * Time Complexity: O(n)
         */
        public void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Invalid index: " + index + ", size: " + size);
            }

            if (index == 0) {
                addFirst(token);
                return;
            }

            Node newNode = new Node(token);
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            newNode.next = prev.next;
            prev.next = newNode;
            size++;
        }

        public void printList() {
            Node curr = head;
            StringBuilder sb = new StringBuilder();
            while (curr != null) {
                sb.append(curr.data).append(" -> ");
                curr = curr.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        // start = 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        // operations: addFirst(100), addLast(104), insertAt(2, 150)
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        // Expected Output: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null
        list.printList();
    }
}
