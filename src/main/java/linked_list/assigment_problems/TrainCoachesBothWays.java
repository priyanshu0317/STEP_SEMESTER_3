package linked_list.assigment_problems;

public class TrainCoachesBothWays {

    public static class Node {
        public String name;
        public Node prev;
        public Node next;

        public Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    public static class DoublyLinkedList {
        private Node head;
        private Node tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addLast(String name) {
            Node newNode = new Node(name);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        /**
         * Removes first occurrence of coach with the given name.
         * Finding coach: O(n)
         * Unlinking once found: O(1)
         */
        public boolean remove(String name) {
            Node curr = head;
            while (curr != null) {
                if (curr.name.equals(name)) {
                    // Unlink node
                    if (curr == head && curr == tail) {
                        head = null;
                        tail = null;
                    } else if (curr == head) {
                        head = head.next;
                        head.prev = null;
                    } else if (curr == tail) {
                        tail = tail.prev;
                        tail.next = null;
                    } else {
                        curr.prev.next = curr.next;
                        curr.next.prev = curr.prev;
                    }
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        public void printForward() {
            StringBuilder sb = new StringBuilder("Forward: ");
            Node curr = head;
            while (curr != null) {
                sb.append(curr.name);
                if (curr.next != null) {
                    sb.append(" <-> ");
                }
                curr = curr.next;
            }
            System.out.println(sb.toString());
        }

        public void printBackward() {
            StringBuilder sb = new StringBuilder("Backward: ");
            Node curr = tail;
            while (curr != null) {
                sb.append(curr.name);
                if (curr.prev != null) {
                    sb.append(" <-> ");
                }
                curr = curr.prev;
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList train = new DoublyLinkedList();
        String[] initialCoaches = {"Engine", "A1", "B1", "B2", "Guard"};
        for (String c : initialCoaches) {
            train.addLast(c);
        }

        // Remove coach B1
        train.remove("B1");

        // Expected Output:
        // Forward: Engine <-> A1 <-> B2 <-> Guard
        // Backward: Guard <-> B2 <-> A1 <-> Engine
        train.printForward();
        train.printBackward();
    }
}
