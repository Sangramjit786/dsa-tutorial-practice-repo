package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*2799. Count Complete Subarrays in an Array*/

public class Problem28 {

    public static int countCompleteSubarrays(int[] nums) {
        // Step 1: Find the total number of distinct elements in the entire array
        Set<Integer> distinctSet = new HashSet<>();
        for (int num : nums) {
            distinctSet.add(num);
        }
        int totalDistinct = distinctSet.size();

        int totalCompleteSubarrays = 0;
        int left = 0;

        // Map to keep track of element frequencies inside the current window
        Map<Integer, Integer> windowCounts = new HashMap<>();

        // Step 2: Slide across the array with the 'right' pointer
        for (int right = 0; right < nums.length; right++) {
            int currentNum = nums[right];
            windowCounts.put(currentNum, windowCounts.getOrDefault(currentNum, 0) + 1);

            // As long as the window has all the distinct elements of the array, it's valid
            while (windowCounts.size() == totalDistinct) {
                // If window [left...right] contains all distinct elements,
                // then any subarray starting at 'left' and ending anywhere from
                // 'right' to the very end of the array is ALSO complete.
                totalCompleteSubarrays += (nums.length - right);

                // Try to shrink the window from the left to find more valid subarrays
                int leftNum = nums[left];
                int leftCount = windowCounts.get(leftNum);

                if (leftCount - 1 == 0) {
                    windowCounts.remove(leftNum); // Element completely leaves the window
                } else {
                    windowCounts.put(leftNum, leftCount - 1);
                }

                left++;
            }
        }

        return totalCompleteSubarrays;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 3, 1, 2, 2};
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: nums = [1, 3, 1, 2, 2]");
        System.out.println("Output: " + countCompleteSubarrays(nums1)); // Expected: 4
        System.out.println();

        // Test Case 2
        int[] nums2 = {5, 5, 5, 5};
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input: nums = [5, 5, 5, 5]");
        System.out.println("Output: " + countCompleteSubarrays(nums2)); // Expected: 10
        System.out.println();
    }
}
