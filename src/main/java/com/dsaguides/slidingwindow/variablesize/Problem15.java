package com.dsaguides.slidingwindow.variablesize;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


/*1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit*/
public class Problem15 {

    public static int longestSubarray(int[] nums, int limit) {
        // Deque to store indices of elements in decreasing order (max element at front)
        Deque<Integer> maxDeque = new ArrayDeque<>();
        // Deque to store indices of elements in increasing order (min element at front)
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain maxDeque: remove indices of smaller elements
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            // Maintain minDeque: remove indices of larger elements
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            // If the current window violates the condition, shrink it from the left
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                left++;
                // Remove elements that are no longer part of the sliding window
                if (maxDeque.peekFirst() < left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() < left) {
                    minDeque.pollFirst();
                }
            }

            // Calculate the maximum length of a valid subarray found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem15 solver = new Problem15();

        // Test Case 1
        int[] nums1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", limit = " + limit1);
        System.out.println("Output: " + solver.longestSubarray(nums1, limit1)); // Expected: 2
        System.out.println();

        // Test Case 2
        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", limit = " + limit2);
        System.out.println("Output: " + solver.longestSubarray(nums2, limit2)); // Expected: 4
        System.out.println();

        // Test Case 3
        int[] nums3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", limit = " + limit3);
        System.out.println("Output: " + solver.longestSubarray(nums3, limit3)); // Expected: 3
        System.out.println();
    }


}
