package com.dsaguides.slidingwindow.fixsize;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*995. Minimum Number of K Consecutive Bit Flips*/

public class Problem4 {
    // Your Queue-Based Sliding Window Algorithm
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;

        // The queue represents our sliding window of active flips.
        // It stores the starting indices of the flips.
        Queue<Integer> flipWindow = new LinkedList<>();
        int totalFlips = 0;

        for (int i = 0; i < n; i++) {
            // 1. Slide the window:
            // Remove flips that have expired (i.e., they are 'k' steps behind us)
            if (!flipWindow.isEmpty() && i - flipWindow.peek() >= k) {
                flipWindow.poll();
            }

            // 2. Check the current bit's actual value:
            if ((nums[i] + flipWindow.size()) % 2 == 0) {

                // If we need to flip, but there isn't enough room left for a 'k' sized window
                if (i + k > n) {
                    return -1;
                }

                // 3. Perform the flip:
                flipWindow.add(i);
                totalFlips++;
            }
        }

        return totalFlips;
    }

    // Main method to run and test the algorithm locally
    public static void main(String[] args) {
        Problem4 solver = new Problem4();

        // Test Case 1
        int[] nums1 = {0, 1, 0};
        int k1 = 1;
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input:  nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Output: " + solver.minKBitFlips(nums1, k1)); // Expected: 2
        System.out.println();

        // Test Case 2
        int[] nums2 = {1, 1, 0};
        int k2 = 2;
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input:  nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Output: " + solver.minKBitFlips(nums2, k2)); // Expected: -1
        System.out.println();

        // Test Case 3
        int[] nums3 = {0, 0, 0, 1, 0, 1, 1, 0};
        int k3 = 3;
        System.out.println("--- Test Case 3 ---");
        System.out.println("Input:  nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Output: " + solver.minKBitFlips(nums3, k3)); // Expected: 3
    }
}
