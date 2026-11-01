package sorting_algorithms.assigment_problems;

public class AttendanceDisorderScore {

    /**
     * Counts the total number of disorder pairs (inversions) using Merge Sort in O(n log n) time.
     *
     * Time Complexity: O(n log n) in all cases (best, average, worst).
     * Extra Space: O(n) auxiliary space for temporary merging buffer.
     * Stability: The underlying merge sort is stable.
     *
     * @param roll array of roll numbers
     * @return total count of disorder pairs
     */
    public static long countInversions(int[] roll) {
        if (roll == null || roll.length <= 1) {
            return 0;
        }

        int[] temp = new int[roll.length];
        int[] copy = roll.clone(); // preserve original array
        return mergeSortAndCount(copy, temp, 0, copy.length - 1);
    }

    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSortAndCount(arr, temp, left, mid);
            count += mergeSortAndCount(arr, temp, mid + 1, right);
            count += mergeAndCount(arr, temp, left, mid, right);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;     // pointer for left half
        int j = mid + 1;  // pointer for right half
        int k = left;     // pointer for temp array
        long inversions = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                // arr[i] > arr[j]: since left subarray is sorted, arr[j] is smaller
                // than all remaining elements arr[i...mid]
                temp[k++] = arr[j++];
                inversions += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        return inversions;
    }

    public static void main(String[] args) {
        // Sample 1: roll = [2, 4, 1, 3, 5]
        int[] roll1 = {2, 4, 1, 3, 5};
        System.out.println("Expected: 3 | Actual: " + countInversions(roll1));

        // Sample 2: roll = [5, 4, 3, 2, 1]
        int[] roll2 = {5, 4, 3, 2, 1};
        System.out.println("Expected: 10 | Actual: " + countInversions(roll2));
    }
}
