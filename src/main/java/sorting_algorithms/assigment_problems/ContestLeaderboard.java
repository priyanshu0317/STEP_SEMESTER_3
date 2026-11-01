package sorting_algorithms.assigment_problems;

public class ContestLeaderboard {

    public static class Entry {
        public String name;
        public int marks;

        public Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return name + " " + marks;
        }
    }

    /**
     * Sorts contest entries by marks descending using Counting Sort in O(n + 101) time.
     * Preserves original submission order for equal marks (stable).
     *
     * Time Complexity: O(n + 101) where n is the number of entries and marks range is [0, 100].
     * Extra Space: O(n + 101) for frequency count array and output array.
     * Stability: Stable (walks input from right to left during placement).
     *
     * @param entries array of student Entry objects
     * @return sorted Entry[] array in descending order of marks
     */
    public static Entry[] leaderboard(Entry[] entries) {
        if (entries == null || entries.length <= 1) {
            return entries != null ? entries.clone() : new Entry[0];
        }

        int n = entries.length;
        int maxKey = 100; // marks range from 0 to 100
        int[] count = new int[maxKey + 1];

        // 1. Calculate transformed key: key = 100 - marks (higher marks get smaller keys)
        for (Entry entry : entries) {
            int key = 100 - entry.marks;
            count[key]++;
        }

        // 2. Build cumulative counts
        for (int i = 1; i <= maxKey; i++) {
            count[i] += count[i - 1];
        }

        // 3. Build output array walking input right-to-left to ensure stability
        Entry[] output = new Entry[n];
        for (int i = n - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            output[count[key] - 1] = entries[i];
            count[key]--;
        }

        return output;
    }

    public static void printLeaderboard(Entry[] entries) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            sb.append(entries[i]);
            if (i < entries.length - 1) {
                sb.append(", ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        Entry[] result = leaderboard(entries);
        printLeaderboard(result);
    }
}
