package com.dsaguides.slidingwindow.fixsize;

import java.util.Arrays;

/*2134. Minimum Swaps to Group All 1's Together II*/

public class Problem11 {

    /**
     * Finds the minimum number of swaps required to group all 1's together in a circular array.
     * * @param nums The input binary circular array.
     * @return The minimum number of swaps.
     */
    public int minSwaps(int[] nums) {
        int n = nums.length;

        // 1. Find the total number of 1's in the array.
        // This count becomes the fixed size of our sliding window.
        int totalOnes = 0;
        for (int num : nums) {
            if (num == 1) {
                totalOnes++;
            }
        }

        // Edge case: If there are no 1's or the array is entirely 1's, 0 swaps are needed.
        if (totalOnes == 0 || totalOnes == n) {
            return 0;
        }

        // 2. Count the number of 1's in the very first window of size 'totalOnes'
        int currentOnesInWindow = 0;
        for (int i = 0; i < totalOnes; i++) {
            if (nums[i] == 1) {
                currentOnesInWindow++;
            }
        }

        int maxOnesInWindow = currentOnesInWindow;

        // 3. Slide the window across the entire circular array.
        // We slide the starting index of the window from 1 up to n-1.
        for (int i = 1; i < n; i++) {
            // The element leaving the window is at index i - 1
            if (nums[i - 1] == 1) {
                currentOnesInWindow--;
            }

            // The new element entering the window is at index (i + totalOnes - 1) % n
            // The modulo operator '% n' handles the circular wrap-around seamlessly.
            int nextIndex = (i + totalOnes - 1) % n;
            if (nums[nextIndex] == 1) {
                currentOnesInWindow++;
            }

            // Update the maximum number of 1's found in any window
            maxOnesInWindow = Math.max(maxOnesInWindow, currentOnesInWindow);
        }

        // The minimum swaps required is the total number of 1's minus the max 1's already grouped.
        return totalOnes - maxOnesInWindow;
    }

    // Main method to test the algorithm with the provided examples
    public static void main(String[] args) {
        Problem11 solver = new Problem11();

        // Test Case 1
        int[] nums1 = {0, 1, 0, 1, 1, 0, 0};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + solver.minSwaps(nums1)); // Expected: 1
        System.out.println();

        // Test Case 2
        int[] nums2 = {0, 1, 1, 1, 0, 0, 1, 1, 0};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + solver.minSwaps(nums2)); // Expected: 2
        System.out.println();

        // Test Case 3
        int[] nums3 = {1, 1, 0, 0, 1};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + solver.minSwaps(nums3)); // Expected: 0
        System.out.println();
    }
}
