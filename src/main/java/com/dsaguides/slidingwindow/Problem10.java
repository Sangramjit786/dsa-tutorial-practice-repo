package com.dsaguides.slidingwindow;

import java.util.Arrays;

/*2090. K Radius Subarray Averages*/
public class Problem10 {

    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Pre-fill the array with -1
        // This automatically handles indices at the edges (0 to k-1, and n-k to n-1)
        // and the edge case where the array is too small.
        Arrays.fill(result, -1);

        // The size of a full window is (k elements left) + 1 (center) + (k elements right)
        // We use 'long' here to prevent overflow in edge cases where k is huge.
        long windowSize = 2L * k + 1;

        // If the window size is larger than the array, we can't form any valid windows.
        if (windowSize > n) {
            return result;
        }

        // Use a 'long' for the sum to prevent Integer Overflow
        // (10^5 elements * 10^5 max value = 10^10, which exceeds max int)
        long windowSum = 0;

        // Step 2: Calculate the sum of the very first window
        for (int i = 0; i < windowSize; i++) {
            windowSum += nums[i];
        }

        // The center of the very first window is exactly at index 'k'
        result[k] = (int) (windowSum / windowSize);

        // Step 3: Slide the window across the rest of the array
        for (int i = (int) windowSize; i < n; i++) {
            // Add the new element entering on the right, remove the old one leaving on the left
            windowSum += nums[i];
            windowSum -= nums[i - (int) windowSize];

            // If the right edge of the window is 'i', the center is 'i - k'
            result[i - k] = (int) (windowSum / windowSize);
        }

        return result;
    }

    // Main method to test the algorithm with the given examples
    public static void main(String[] args) {
        Problem10 solver = new Problem10();

        // Test Case 1
        int[] nums1 = {7, 4, 3, 9, 1, 8, 5, 2, 6};
        int k1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(solver.getAverages(nums1, k1)));
        // Expected: [-1, -1, -1, 5, 4, 4, -1, -1, -1]
        System.out.println();

        // Test Case 2
        int[] nums2 = {100000};
        int k2 = 0;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(solver.getAverages(nums2, k2)));
        // Expected: [100000]
        System.out.println();

        // Test Case 3
        int[] nums3 = {8};
        int k3 = 100000;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + Arrays.toString(solver.getAverages(nums3, k3)));
        // Expected: [-1]
        System.out.println();
    }
}
