package data_structures.assigment_problems;

public class ClassTopperFinder {

    public static class TopperResult {
        private int rowIndex;
        private int total;

        public TopperResult(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    /**
     * Finds the student (row) with the highest total marks across subjects (columns).
     * Breaks ties by selecting the student with the smallest row index.
     *
     * Time Complexity: O(m * n) where m is number of students and n is number of subjects.
     * Space Complexity: O(1) auxiliary space beyond the input grid.
     *
     * @param marks 2D array of marks
     * @return TopperResult tuple (rowIndex, total)
     */
    public static TopperResult findTopper(int[][] marks) {
        if (marks == null || marks.length == 0 || marks[0].length == 0) {
            return new TopperResult(-1, 0);
        }

        int bestRowIndex = 0;
        int maxTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            // Strictly greater ensures tie-breaking picks the smallest row index
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestRowIndex = i;
            }
        }

        return new TopperResult(bestRowIndex, maxTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        TopperResult result = findTopper(marks);
        System.out.println("Expected: (1, 259) | Actual: " + result);
    }
}
