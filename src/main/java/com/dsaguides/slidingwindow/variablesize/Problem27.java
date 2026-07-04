package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;

/*2537. Count the Number of Good Subarrays*/

public class Problem27 {

    public static long countGoodSubarrays(int[] nums, int k) {
        long totalGoodSubarrays = 0;
        int left = 0;
        long currentPairsCount = 0;

        // Map to keep track of the frequencies of elements inside the current window
        Map<Integer, Integer> frequencies = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            int currentNum = nums[right];

            // Get the existing count of this element before adding the new one
            int existingCount = frequencies.getOrDefault(currentNum, 0);

            // If it already appeared 'existingCount' times, adding it now
            // creates 'existingCount' new matching pairs.
            currentPairsCount += existingCount;

            // Update the map with the new count
            frequencies.put(currentNum, existingCount + 1);

            // Shrink the window from the left as long as it remains valid (pairs >= k)
            while (currentPairsCount >= k) {
                // If a window from 'left' to 'right' is valid, then any subarray starting
                // from 'left' up to 'right' and extending ALL the way to the end of
                // the array is ALSO valid.
                // There are exactly (nums.length - right) such valid subarrays.
                totalGoodSubarrays += (nums.length - right);

                // Now slide the left pointer up to try and minimize the window
                int leftNum = nums[left];
                int leftCount = frequencies.get(leftNum);

                // Removing this element destroys (leftCount - 1) pairs it was forming
                currentPairsCount -= (leftCount - 1);

                // Update the frequency map
                if (leftCount - 1 == 0) {
                    frequencies.remove(leftNum);
                } else {
                    frequencies.put(leftNum, leftCount - 1);
                }

                left++;
            }
        }

        return totalGoodSubarrays;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 1, 1, 1, 1};
        int k1 = 10;
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: nums = [1, 1, 1, 1, 1], k = " + k1);
        System.out.println("Output: " + countGoodSubarrays(nums1, k1)); // Expected: 1
        System.out.println();

        // Test Case 2
        int[] nums2 = {3, 1, 4, 3, 2, 2, 4};
        int k2 = 2;
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input: nums = [3, 1, 4, 3, 2, 2, 4], k = " + k2);
        System.out.println("Output: " + countGoodSubarrays(nums2, k2)); // Expected: 4
        System.out.println();
    }
}
