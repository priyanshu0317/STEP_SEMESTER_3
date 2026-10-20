package data_structures.class_problems;

public class MaximizeAreaBetweenBoundaries {

    /**
     * Optimal Solution: Two-Pointer Technique
     * Time Complexity: O(n) where n is the number of elements in heights.
     * Space Complexity: O(1) auxiliary space beyond input array.
     *
     * @param heights array of non-negative integers representing vertical wall heights
     * @return maximum container water area
     */
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int hLeft = heights[left];
            int hRight = heights[right];
            int width = right - left;
            int currentArea = Math.min(hLeft, hRight) * width;

            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            // Move pointer corresponding to the shorter wall inward
            if (hLeft < hRight) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * Brute-Force Solution for comparison
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int maxContainerAreaBruteForce(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int area = Math.min(heights[i], heights[j]) * (j - i);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxContainerArea(heights);
        System.out.println("Maximum Water Container Area: " + result);
    }
}
