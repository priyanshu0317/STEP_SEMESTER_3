package sorting_algorithms.assigment_problems;

import java.util.Arrays;

public class NoticeBoardShuffle {

    /**
     * Sorts the scores array using optimized Bubble Sort with early termination.
     *
     * Time Complexity:
     *   - Best Case (already sorted): O(n)
     *   - Average / Worst Case: O(n^2)
     * Extra Space: O(1) auxiliary space (in-place sort).
     * Stability: Stable (equal elements never swap).
     *
     * @param scores integer array of test scores
     * @return int[] array of size 2: {passes, swaps}
     */
    public static int[] bubbleStats(int[] scores) {
        if (scores == null || scores.length <= 1) {
            return new int[]{0, 0};
        }

        int passes = 0;
        int swaps = 0;
        int n = scores.length;

        for (int i = 0; i < n - 1; i++) {
            passes++;
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {
        // Sample 1: scores = [5, 1, 4, 2, 8]
        int[] scores1 = {5, 1, 4, 2, 8};
        int[] stats1 = bubbleStats(scores1);
        System.out.println(Arrays.toString(scores1) + " passes = " + stats1[0] + ", swaps = " + stats1[1]);

        // Sample 2: scores = [1, 2, 3, 4]
        int[] scores2 = {1, 2, 3, 4};
        int[] stats2 = bubbleStats(scores2);
        System.out.println(Arrays.toString(scores2) + " passes = " + stats2[0] + ", swaps = " + stats2[1]);
    }
}
