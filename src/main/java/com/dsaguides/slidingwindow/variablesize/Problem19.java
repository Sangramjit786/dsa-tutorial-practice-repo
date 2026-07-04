package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*1695. Maximum Erasure Value*/

public class Problem19 {

    public int maximumUniqueSubarray(int[] nums) {
        int maxScore = 0;
        int currentSum = 0;
        int left = 0;

        // HashSet to store unique elements in the current window
        Set<Integer> seen = new HashSet<>();

        for (int right = 0; right < nums.length; right++) {
            // If the element is already in the set, shrink the window from the left
            while (seen.contains(nums[right])) {
                seen.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }

            // Add the current element to the window
            seen.add(nums[right]);
            currentSum += nums[right];

            // Update the maximum score found so far
            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem19 solver = new Problem19();

        // Test Case 1
        int[] arr1 = {4, 2, 4, 5, 6};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr1));
        System.out.println("Output: " + solver.maximumUniqueSubarray(arr1)); // Expected: 17
        System.out.println();

        // Test Case 2
        int[] arr2 = {5, 2, 1, 2, 5, 2, 1, 2, 5};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr2));
        System.out.println("Output: " + solver.maximumUniqueSubarray(arr2)); // Expected: 8
        System.out.println();
    }
}
