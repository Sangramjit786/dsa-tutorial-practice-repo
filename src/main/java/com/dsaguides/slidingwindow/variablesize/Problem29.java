package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;

/*2962. Count Subarrays Where Max Element Appears at Least K Times*/

public class Problem29 {

    public long countSubarrays(int[] nums, int k) {
        // 1. Find the maximum element of the entire array using Java 8 Streams
        int maxElement = Arrays.stream(nums)
                .max()
                .orElse(Integer.MIN_VALUE);

        int left = 0;
        int maxElementCount = 0;
        long totalSubArrays = 0;

        // 2. Sliding window loop
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == maxElement) {
                maxElementCount++;
            }

            while (maxElementCount >= k) {
                // If a window from 'left' to 'right' has k max elements,
                // any window extending to the end of the array is also valid.
                totalSubArrays += (nums.length - right);

                if (nums[left] == maxElement) {
                    maxElementCount--;
                }
                left++;
            }
        }
        return totalSubArrays;
    }

    public static void main(String[] args) {
        Problem29 solution = new Problem29();

        // Your specific test case input
        int[] nums = {28, 5, 58, 91, 24, 91, 53, 9, 48, 85, 16, 70, 91, 91, 47, 91, 61, 4, 54, 61, 49};
        int k = 1;

        long result = solution.countSubarrays(nums, k);

        // Print outputs
        System.out.println("Input Array: " + Arrays.toString(nums));
        System.out.println("k value: " + k);
        System.out.println("Total Subarrays (Expected: 187): " + result);
    }
}
