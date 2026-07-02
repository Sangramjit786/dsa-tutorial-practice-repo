package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;

/*992. Subarrays with K Different Integers*/

public class Problem11 {

    /**
     * Finds the number of good subarrays where the number of different integers is exactly k.
     * Uses the principle: Exactly(K) = AtMost(K) - AtMost(K - 1)
     * * @param nums The input array of integers.
     * @param k    The exact number of distinct integers required.
     * @return The number of valid subarrays.
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        // Find subarrays with at most 'k' distinct minus those with at most 'k - 1' distinct
        return subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1);
    }

    /**
     * Helper method to find the number of subarrays with AT MOST 'k' distinct integers.
     * Uses the sliding window algorithm.
     */
    private int subarraysWithAtMostKDistinct(int[] nums, int k) {
        int n = nums.length;

        // Since the problem constraints state nums[i] <= nums.length,
        // we can use a frequency array instead of a HashMap for O(1) lookups.
        int[] freq = new int[n + 1];

        int left = 0;
        int distinctCount = 0;
        int totalSubarrays = 0;

        for (int right = 0; right < n; right++) {
            int rightNum = nums[right];

            // If we encounter a new distinct integer, increment our count
            if (freq[rightNum] == 0) {
                distinctCount++;
            }
            freq[rightNum]++;

            // If the window has more than 'k' distinct integers, shrink it from the left
            while (distinctCount > k) {
                int leftNum = nums[left];
                freq[leftNum]--;

                // If the frequency drops to 0, it's no longer in our current window
                if (freq[leftNum] == 0) {
                    distinctCount--;
                }
                left++;
            }

            // If the window [left, right] is valid, then all subarrays ending at 'right'
            // and starting anywhere from 'left' to 'right' are also valid.
            // The number of such subarrays is exactly (right - left + 1).
            totalSubarrays += (right - left + 1);
        }

        return totalSubarrays;
    }

    // Main method to run and test the algorithm locally
    public static void main(String[] args) {
        Problem11 solver = new Problem11();

        // Test Case 1
        int[] nums1 = {1, 2, 1, 2, 3};
        int k1 = 2;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solver.subarraysWithKDistinct(nums1, k1)); // Expected: 7
        System.out.println();

        // Test Case 2
        int[] nums2 = {1, 2, 1, 3, 4};
        int k2 = 3;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solver.subarraysWithKDistinct(nums2, k2)); // Expected: 3
        System.out.println();
    }
}
