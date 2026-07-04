package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;

/*1838. Frequency of the Most Frequent Element*/

public class Problem20 {

    public int maxFrequency(int[] nums, int k) {
        // Step 1: Sort the array to group closer numbers together
        Arrays.sort(nums);

        int maxFreq = 0;
        int left = 0;
        long currentWindowSum = 0;

        // Step 2: Use sliding window
        for (int right = 0; right < nums.length; right++) {
            currentWindowSum += nums[right];

            // Calculate the operations needed to make all elements in the window equal to nums[right]
            // If it exceeds k, shrink the window from the left
            while ((long) nums[right] * (right - left + 1) - currentWindowSum > k) {
                currentWindowSum -= nums[left];
                left++;
            }

            // Update the maximum frequency found (size of the window)
            maxFreq = Math.max(maxFreq, right - left + 1);
        }

        return maxFreq;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem20 solver = new Problem20();

        // Test Case 1
        int[] arr1 = {1, 2, 4};
        int k1 = 5;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr1) + ", k = " + k1);
        System.out.println("Output: " + solver.maxFrequency(arr1, k1)); // Expected: 3
        System.out.println();

        // Test Case 2
        int[] arr2 = {1, 4, 8, 13};
        int k2 = 5;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr2) + ", k = " + k2);
        System.out.println("Output: " + solver.maxFrequency(arr2, k2)); // Expected: 2
        System.out.println();

        // Test Case 3
        int[] arr3 = {3, 9, 6};
        int k3 = 2;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr3) + ", k = " + k3);
        System.out.println("Output: " + solver.maxFrequency(arr3, k3)); // Expected: 1
        System.out.println();
    }
}
