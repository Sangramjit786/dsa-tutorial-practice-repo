package com.dsaguides.slidingwindow;

import java.util.Arrays;


/*3254. Find the Power of K-Size Subarrays I*/
public class Problem14 {

    /**
     * Finds the power of all subarrays of size k.
     * @param nums The input array of integers.
     * @param k    The size of the subarray window.
     * @return An array of size n - k + 1 containing the power of each subarray.
     */
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] results = new int[n - k + 1];

        // Track the current length of the consecutive ascending sequence.
        // A single element is always consecutive with itself, so we start at 1.
        int consecutiveCount = 1;

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                // If the current element is exactly 1 greater than the previous element,
                // it extends our consecutive ascending sequence.
                if (nums[i] == nums[i - 1] + 1) {
                    consecutiveCount++;
                } else {
                    // Otherwise, the consecutive pattern is broken, reset the count to 1.
                    consecutiveCount = 1;
                }
            }

            // Once our window reaches size k (starting from index k - 1),
            // we can start evaluating and filling our results array.
            if (i >= k - 1) {
                // If the consecutive ascending sequence ending at 'i' is at least
                // 'k' elements long, the entire current window is valid.
                // The maximum element of a sorted ascending array is always its last element.
                if (consecutiveCount >= k) {
                    results[i - k + 1] = nums[i];
                } else {
                    results[i - k + 1] = -1;
                }
            }
        }

        return results;
    }

    // Main method to test the algorithm with the given examples
    public static void main(String[] args) {
        Problem14 solver = new Problem14();

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4, 3, 2, 5};
        int k1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(solver.resultsArray(nums1, k1)));
        // Expected: [3, 4, -1, -1, -1]
        System.out.println();

        // Test Case 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int k2 = 4;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(solver.resultsArray(nums2, k2)));
        // Expected: [-1, -1]
        System.out.println();

        // Test Case 3
        int[] nums3 = {3, 2, 3, 2, 3, 2};
        int k3 = 2;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + Arrays.toString(solver.resultsArray(nums3, k3)));
        // Expected: [-1, 3, -1, 3, -1]
        System.out.println();
    }
}
