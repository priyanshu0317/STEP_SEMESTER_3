package data_structures.assigment_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostPopularCanteenOrder {

    public static class PopularItemResult {
        private String item;
        private int count;

        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        public String getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    /**
     * Finds the most frequently ordered canteen item.
     * Ties are broken by choosing the item that appeared first in the original orders list.
     *
     * Time Complexity: O(n) where n is the number of orders.
     * Space Complexity: O(u) where u is the number of unique item names (u <= n).
     *
     * @param orders list of item name strings
     * @return PopularItemResult containing (item, count)
     */
    public static PopularItemResult mostPopular(List<String> orders) {
        if (orders == null || orders.isEmpty()) {
            return new PopularItemResult("", 0);
        }

        // Pass 1: Count frequency of each item using a Hash Map
        Map<String, Integer> frequencyMap = new HashMap<>();
        int maxCount = 0;

        for (String item : orders) {
            int newCount = frequencyMap.getOrDefault(item, 0) + 1;
            frequencyMap.put(item, newCount);
            if (newCount > maxCount) {
                maxCount = newCount;
            }
        }

        // Pass 2: Traverse orders in original order to find first item achieving maxCount
        for (String item : orders) {
            if (frequencyMap.get(item) == maxCount) {
                return new PopularItemResult(item, maxCount);
            }
        }

        return new PopularItemResult(orders.get(0), maxCount);
    }

    public static void main(String[] args) {
        // Sample 1
        List<String> orders1 = Arrays.asList("dosa", "idli", "vada", "dosa", "idli", "dosa", "tea");
        PopularItemResult result1 = mostPopular(orders1);
        System.out.println("Sample 1 Output: " + result1);

        // Sample 2
        List<String> orders2 = Arrays.asList("tea", "coffee", "coffee", "tea");
        PopularItemResult result2 = mostPopular(orders2);
        System.out.println("Sample 2 Output: " + result2);
    }
}
