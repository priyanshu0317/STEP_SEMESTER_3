package sorting_algorithms.assigment_problems;

public class PlacementCutoff {

    /**
     * Finds the k-th highest mark using Quickselect with Lomuto partition (last element as pivot).
     * The k-th highest element corresponds to index (n - k) in ascending sorted order.
     *
     * Time Complexity:
     *   - Average Case: O(n)
     *   - Worst Case: O(n^2)
     * Extra Space: O(1) auxiliary space (iterative in-place partitioning).
     * Stability: Not stable (swaps non-adjacent elements across pivot).
     *
     * @param marks array of applicant marks
     * @param k     1-based rank from highest (1 <= k <= marks.length)
     * @return the k-th highest mark
     */
    public static int kthHighest(int[] marks, int k) {
        if (marks == null || k < 1 || k > marks.length) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        int[] copy = marks.clone(); // preserve original marks array
        int targetIndex = copy.length - k;
        int left = 0;
        int right = copy.length - 1;

        while (left <= right) {
            int pivotIndex = partition(copy, left, right);
            if (pivotIndex == targetIndex) {
                return copy[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        return copy[targetIndex];
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // Sample 1: marks = [72, 95, 60, 88, 79], k = 2
        int[] marks1 = {72, 95, 60, 88, 79};
        System.out.println("Expected: 88 | Actual: " + kthHighest(marks1, 2));

        // Sample 2: marks = [50, 90, 90, 70], k = 2
        int[] marks2 = {50, 90, 90, 70};
        System.out.println("Expected: 90 | Actual: " + kthHighest(marks2, 2));
    }
}
