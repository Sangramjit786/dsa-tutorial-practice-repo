package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;

/*1004. Max Consecutive Ones III*/

public class Problem12 {

    /**
     * Finds the maximum number of consecutive 1's if you can flip at most k 0's.
     * @param nums The input binary array.
     * @param k    The maximum number of 0's we can flip.
     * @return The maximum length of a valid subarray.
     */
    public int longestOnes(int[] nums, int k) {
        // No need for the edge case check at the top, the sliding window
        // naturally handles cases where k > nums.length!

        int left = 0;
        int zeroCount = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // If we encounter a 0, we increment our zero tracker
            if (nums[right] == 0) {
                zeroCount++;
            }

            // If we have flipped more than 'k' zeros, our window is invalid.
            // We must shrink the window from the left until we discard a zero.
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // Update the maximum length of a valid window containing at most k zeros.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem12 solver = new Problem12();

        // Test Case 1
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solver.longestOnes(nums1, k1)); // Expected: 6
        System.out.println();

        // Test Case 2
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solver.longestOnes(nums2, k2)); // Expected: 10
        System.out.println();

        // Your Failing Test Case
        int[] nums3 = {0, 0, 0, 0};
        int k3 = 0;
        System.out.println("=== Test Case 3 (Failing Case Fix) ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solver.longestOnes(nums3, k3)); // Expected: 0
        System.out.println();
    }
}
