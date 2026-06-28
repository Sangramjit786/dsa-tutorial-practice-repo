package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;


/*209. Minimum Size Subarray Sum*/
public class Problem4 {

    /**
     * Finds the minimal length of a contiguous subarray of which the sum is >= target.
     * @param target The target sum we want to reach or exceed.
     * @param nums   The input array of positive integers.
     * @return The minimal length of a valid subarray, or 0 if none exists.
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;

        // Sliding window left boundary pointer
        int left = 0;

        // Slide the 'right' boundary of our window from left to right
        for (int right = 0; right < n; right++) {
            currentSum += nums[right];

            // Contract the window from the left as long as the current window sum
            // is greater than or equal to the target.
            while (currentSum >= target) {
                // Update the minimum length found so far
                minLength = Math.min(minLength, right - left + 1);

                // Subtract the element leaving the window and shift the left pointer
                currentSum -= nums[left];
                left++;
            }
        }

        // If minLength was never updated, it means no valid subarray was found
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    // Main method to run and test the algorithm locally
    public static void main(String[] args) {
        Problem4 solver = new Problem4();

        // Test Case 1
        int target1 = 7;
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: target = " + target1 + ", nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + solver.minSubArrayLen(target1, nums1)); // Expected: 2 (Subarray [4, 3])
        System.out.println();

        // Test Case 2
        int target2 = 4;
        int[] nums2 = {1, 4, 4};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: target = " + target2 + ", nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + solver.minSubArrayLen(target2, nums2)); // Expected: 1 (Subarray [4])
        System.out.println();

        // Test Case 3
        int target3 = 11;
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: target = " + target3 + ", nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + solver.minSubArrayLen(target3, nums3)); // Expected: 0
        System.out.println();
    }
}
