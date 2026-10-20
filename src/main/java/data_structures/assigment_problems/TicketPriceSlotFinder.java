package data_structures.assigment_problems;

import java.util.Arrays;
import java.util.List;

public class TicketPriceSlotFinder {

    /**
     * Finds the index of newPrice if present, or the insertion index where it belongs
     * to maintain sorted order, using Binary Search.
     *
     * Time Complexity: O(log n) where n is the number of prices.
     * Space Complexity: O(1) auxiliary space beyond the input list.
     *
     * @param prices   sorted list of distinct integers
     * @param newPrice integer to locate or insert
     * @return index between 0 and prices.size() inclusive
     */
    public static int findSlot(List<Integer> prices, int newPrice) {
        if (prices == null || prices.isEmpty()) {
            return 0;
        }

        int low = 0;
        int high = prices.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = prices.get(mid);

            if (midVal == newPrice) {
                return mid;
            } else if (midVal < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // When not found, low pointer sits exactly at the correct insertion index
        return low;
    }

    /**
     * Overloaded method accepting primitive int array.
     */
    public static int findSlot(int[] prices, int newPrice) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = prices[mid];

            if (midVal == newPrice) {
                return mid;
            } else if (midVal < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        List<Integer> prices = Arrays.asList(120, 150, 200, 260);

        // Sample 1: already present
        int slot1 = findSlot(prices, 150);
        System.out.println("prices = " + prices + ", newPrice = 150 -> Slot: " + slot1);

        // Sample 2: insert in middle
        int slot2 = findSlot(prices, 210);
        System.out.println("prices = " + prices + ", newPrice = 210 -> Slot: " + slot2);

        // Sample 3: insert at end
        int slot3 = findSlot(prices, 300);
        System.out.println("prices = " + prices + ", newPrice = 300 -> Slot: " + slot3);
    }
}
