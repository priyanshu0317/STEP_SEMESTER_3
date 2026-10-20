package data_structures.assigment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeTokenQueues {

    /**
     * Merges two pre-sorted lists of tokens using the two-pointer technique.
     * Time Complexity: O(m + n) where m and n are the lengths of counterA and counterB.
     * Space Complexity: O(m + n) to store the merged result list.
     *
     * @param counterA sorted list of integers
     * @param counterB sorted list of integers
     * @return merged list sorted in ascending order
     */
    public static List<Integer> mergeTokens(List<Integer> counterA, List<Integer> counterB) {
        if (counterA == null) counterA = new ArrayList<>();
        if (counterB == null) counterB = new ArrayList<>();

        List<Integer> merged = new ArrayList<>(counterA.size() + counterB.size());
        int i = 0;
        int j = 0;

        while (i < counterA.size() && j < counterB.size()) {
            if (counterA.get(i) <= counterB.get(j)) {
                merged.add(counterA.get(i));
                i++;
            } else {
                merged.add(counterB.get(j));
                j++;
            }
        }

        while (i < counterA.size()) {
            merged.add(counterA.get(i));
            i++;
        }

        while (j < counterB.size()) {
            merged.add(counterB.get(j));
            j++;
        }

        return merged;
    }

    public static void main(String[] args) {
        // Sample 1
        List<Integer> counterA1 = Arrays.asList(3, 8, 15, 20);
        List<Integer> counterB1 = Arrays.asList(5, 8, 12);
        List<Integer> result1 = mergeTokens(counterA1, counterB1);
        System.out.println("Sample 1 Output: " + result1);

        // Sample 2
        List<Integer> counterA2 = new ArrayList<>();
        List<Integer> counterB2 = Arrays.asList(4, 9);
        List<Integer> result2 = mergeTokens(counterA2, counterB2);
        System.out.println("Sample 2 Output: " + result2);
    }
}
