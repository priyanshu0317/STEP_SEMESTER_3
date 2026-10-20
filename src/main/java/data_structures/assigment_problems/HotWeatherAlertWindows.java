package data_structures.assigment_problems;

import java.util.Arrays;
import java.util.List;

public class HotWeatherAlertWindows {

    /**
     * Counts the number of contiguous k-hour windows with average temperature >= threshold
     * using the sliding window technique.
     *
     * Time Complexity: O(n) where n is the number of temperature readings.
     * Space Complexity: O(1) auxiliary space beyond the input list.
     *
     * @param readings  list of hourly integer temperature readings
     * @param k         window/block size in hours
     * @param threshold minimum required average temperature
     * @return number of qualifying windows
     */
    public static int countAlerts(List<Integer> readings, int k, int threshold) {
        if (readings == null || readings.size() < k || k <= 0) {
            return 0;
        }

        long requiredSum = (long) k * threshold;
        long currentWindowSum = 0;

        // Compute sum of the first window of size k
        for (int i = 0; i < k; i++) {
            currentWindowSum += readings.get(i);
        }

        int qualifyingCount = 0;
        if (currentWindowSum >= requiredSum) {
            qualifyingCount++;
        }

        // Slide the window across the remaining readings
        for (int i = k; i < readings.size(); i++) {
            currentWindowSum += readings.get(i) - readings.get(i - k);
            if (currentWindowSum >= requiredSum) {
                qualifyingCount++;
            }
        }

        return qualifyingCount;
    }

    /**
     * Overloaded method accepting primitive int array.
     */
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        long requiredSum = (long) k * threshold;
        long currentWindowSum = 0;

        for (int i = 0; i < k; i++) {
            currentWindowSum += readings[i];
        }

        int qualifyingCount = 0;
        if (currentWindowSum >= requiredSum) {
            qualifyingCount++;
        }

        for (int i = k; i < readings.length; i++) {
            currentWindowSum += readings[i] - readings[i - k];
            if (currentWindowSum >= requiredSum) {
                qualifyingCount++;
            }
        }

        return qualifyingCount;
    }

    public static void main(String[] args) {
        List<Integer> readings = Arrays.asList(2, 2, 2, 2, 5, 5, 5, 8);
        int k = 3;
        int threshold = 4;

        int alerts = countAlerts(readings, k, threshold);
        System.out.println("Expected Output: 3 | Actual Output: " + alerts);
    }
}
