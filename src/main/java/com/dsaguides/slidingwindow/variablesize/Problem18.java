package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;

/*1493. Longest Subarray of 1's After Deleting One Element*/

public class Problem18 {

    public int longestSubarray(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // Count the number of zeros in the current window
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If we have more than one zero, shrink the window from the left
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // The window size is (right - left + 1).
            // Because we must delete one element, the actual length of 1's is (right - left).
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem18 solver = new Problem18();

        // Test Case 1
        int[] nums1 = {1, 1, 0, 1};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + solver.longestSubarray(nums1)); // Expected: 3
        System.out.println();

        // Test Case 2
        int[] nums2 = {0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + solver.longestSubarray(nums2)); // Expected: 5
        System.out.println();

        // Test Case 3
        int[] nums3 = {1, 1, 1};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + solver.longestSubarray(nums3)); // Expected: 2
        System.out.println();
    }
}
