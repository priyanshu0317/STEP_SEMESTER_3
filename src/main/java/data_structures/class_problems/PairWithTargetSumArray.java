package data_structures.class_problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSumArray {

    /**
     * Approach 1: Hash Set Lookup (Optimal Time)
     * Time Complexity: O(n) where n is the number of elements in nums.
     * Space Complexity: O(n) auxiliary space for the hash set.
     */
    public static boolean hasPairWithSumHashSet(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> complements = new HashSet<>();
        for (int x : nums) {
            if (complements.contains(target - x)) {
                return true;
            }
            complements.add(x);
        }
        return false;
    }

    /**
     * Approach 2: Sorting + Two Pointers
     * Time Complexity: O(n log n) due to sorting.
     * Space Complexity: O(n) to clone array without mutating original input (or O(1) in-place).
     */
    public static boolean hasPairWithSumTwoPointers(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int[] copy = nums.clone();
        Arrays.sort(copy);

        int left = 0;
        int right = copy.length - 1;

        while (left < right) {
            int currentSum = copy[left] + copy[right];
            if (currentSum == target) {
                return true;
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Sample 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Sample 1 (HashSet): " + hasPairWithSumHashSet(nums1, target1));
        System.out.println("Sample 1 (TwoPointers): " + hasPairWithSumTwoPointers(nums1, target1));

        // Sample 2
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("Sample 2 (HashSet): " + hasPairWithSumHashSet(nums2, target2));
        System.out.println("Sample 2 (TwoPointers): " + hasPairWithSumTwoPointers(nums2, target2));
    }
}
