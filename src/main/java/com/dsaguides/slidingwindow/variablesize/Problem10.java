package com.dsaguides.slidingwindow.variablesize;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Problem10 {

    /**
     * Finds the length of the shortest non-empty subarray of nums with a sum of at least k.
     * @param nums The input array of integers (can contain negative values).
     * @param k    The target sum threshold.
     * @return The length of the shortest valid subarray, or -1 if none exists.
     */
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        // Use 'long' for prefix sums to prevent integer overflow.
        // prefixSums[i] represents the sum of the first 'i' elements.
        long[] prefixSums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + nums[i];
        }

        int minLength = n + 1;

        // Deque stores the indices of prefixSums.
        // It acts as a sliding window, keeping indices of prefix sums in strictly increasing order.
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            // 1. Contract the window from the left:
            // If the current prefix sum minus the prefix sum at the start of our deque is >= k,
            // we have found a valid subarray of length (i - deque.peekFirst()).
            while (!deque.isEmpty() && prefixSums[i] - prefixSums[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }

            // 2. Maintain monotonicity (strictly increasing prefix sums in the deque):
            // If the current prefix sum is smaller than or equal to the prefix sum at the end of the deque,
            // we pop from the back.
            // Why? A smaller prefix sum 'prefixSums[i]' starting at a later index 'i' is ALWAYS a better
            // starting candidate than a larger prefix sum starting earlier.
            while (!deque.isEmpty() && prefixSums[i] <= prefixSums[deque.peekLast()]) {
                deque.pollLast();
            }

            // Add the current index to the sliding window
            deque.addLast(i);
        }

        // If minLength was never updated, no valid subarray exists
        return minLength == n + 1 ? -1 : minLength;
    }

    // Main method to run and test the algorithm locally
    public static void main(String[] args) {
        Problem10 solver = new Problem10();

        // Test Case 1
        int[] nums1 = {1};
        int k1 = 1;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solver.shortestSubarray(nums1, k1)); // Expected: 1
        System.out.println();

        // Test Case 2
        int[] nums2 = {1, 2};
        int k2 = 4;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solver.shortestSubarray(nums2, k2)); // Expected: -1
        System.out.println();

        // Test Case 3
        int[] nums3 = {2, -1, 2};
        int k3 = 3;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solver.shortestSubarray(nums3, k3)); // Expected: 3 (Subarray is [2, -1, 2])
        System.out.println();
    }
}
