package data_structures.class_problems;

public class WarehouseGridSummary {

    public static class Coordinate {
        private int row;
        private int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    public static class SummaryResult {
        private long totalItems;
        private Coordinate maxCoordinate;

        public SummaryResult(long totalItems, Coordinate maxCoordinate) {
            this.totalItems = totalItems;
            this.maxCoordinate = maxCoordinate;
        }

        public long getTotalItems() {
            return totalItems;
        }

        public Coordinate getMaxCoordinate() {
            return maxCoordinate;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", " + maxCoordinate + ")";
        }
    }

    /**
     * Calculates total items in 2D warehouse grid and the (row, col) coordinates of max item bin.
     * Ties are broken by picking the first bin encountered in row-major scan order.
     *
     * Time Complexity: O(m * n) where m is rows and n is columns.
     * Space Complexity: O(1) auxiliary space beyond the input grid.
     *
     * @param grid 2D integer array
     * @return SummaryResult containing total items and max Coordinate
     */
    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, new Coordinate(0, 0));
        }

        long totalItems = 0;
        int maxVal = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int count = grid[r][c];
                totalItems += count;
                // Strict inequality ensures first encountered bin is kept on ties
                if (count > maxVal) {
                    maxVal = count;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(totalItems, new Coordinate(maxRow, maxCol));
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        SummaryResult result = warehouseSummary(grid);
        System.out.println("Warehouse Summary: " + result);
    }
}
