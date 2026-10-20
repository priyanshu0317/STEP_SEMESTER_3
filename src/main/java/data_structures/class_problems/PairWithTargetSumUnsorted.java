package data_structures.class_problems;

import java.util.HashSet;
import java.util.Set;

public class PairWithTargetSumUnsorted {

    /**
     * Determines if any two distinct elements in nums sum up to target using a Hash Set.
     * Time Complexity: O(n) average time where n is the length of nums.
     * Space Complexity: O(n) auxiliary space to store seen elements.
     *
     * @param nums   array of integers (may contain negatives and duplicates, unsorted)
     * @param target integer target sum
     * @return true if two distinct elements sum to target, false otherwise
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    /**
     * Brute-force approach for complexity comparison.
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("nums = [2, 7, 11, 15], target = 9 -> " + hasPairWithSum(nums1, target1));

        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("nums = [3, 4, 6], target = 20 -> " + hasPairWithSum(nums2, target2));
    }
}
