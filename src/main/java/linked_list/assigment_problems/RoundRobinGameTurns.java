package linked_list.assigment_problems;

public class RoundRobinGameTurns {

    public static class Node {
        public String name;
        public Node next;

        public Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    public static class CircularLinkedList {
        private Node tail;

        public CircularLinkedList() {
            this.tail = null;
        }

        /**
         * Adds a player to the circular singly linked list using the tail pointer.
         * Time Complexity: O(1)
         */
        public void addLast(String name) {
            Node newNode = new Node(name);
            if (tail == null) {
                tail = newNode;
                tail.next = tail;
            } else {
                newNode.next = tail.next; // New node points to head
                tail.next = newNode;      // Old tail points to new node
                tail = newNode;           // Update tail to new node
            }
        }

        /**
         * Prints the player who takes each turn for the given number of turns.
         * Time Complexity: O(turns)
         */
        public void printTurns(int turns) {
            if (tail == null || turns <= 0) {
                return;
            }

            Node curr = tail.next; // head of the circular list
            StringBuilder sb = new StringBuilder();
            for (int t = 0; t < turns; t++) {
                sb.append(curr.name);
                if (t < turns - 1) {
                    sb.append(" ");
                }
                curr = curr.next;
            }
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) {
        CircularLinkedList game = new CircularLinkedList();
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        // turns = 7
        // Expected Output: Asha Ravi Neha Asha Ravi Neha Asha
        game.printTurns(7);
    }
}
