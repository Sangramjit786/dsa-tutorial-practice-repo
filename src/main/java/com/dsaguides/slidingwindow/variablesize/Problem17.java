package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;


/*1574. Shortest Subarray to be Removed to Make Array Sorted*/
public class Problem17 {

    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int left = 0;

        // 1. Find the longest non-decreasing prefix
        while (left < n - 1 && arr[left] <= arr[left + 1]) {
            left++;
        }

        // If the entire array is already sorted
        if (left == n - 1) {
            return 0;
        }

        // 2. Find the longest non-decreasing suffix
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }

        // Base case: remove either the entire suffix or the entire prefix
        int minToRemove = Math.min(n - left - 1, right);

        // 3. Use two pointers to merge a valid prefix and suffix
        int i = 0;
        int j = right;
        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                // If valid, the elements between index i and j must be removed
                minToRemove = Math.min(minToRemove, j - i - 1);
                i++; // Try to expand prefix
            } else {
                j++; // Shrink suffix to find a larger element
            }
        }

        return minToRemove;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem17 solver = new Problem17();

        // Test Case 1
        int[] arr1 = {1, 2, 3, 10, 4, 2, 3, 5};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr1));
        System.out.println("Output: " + solver.findLengthOfShortestSubarray(arr1)); // Expected: 3
        System.out.println();

        // Test Case 2
        int[] arr2 = {5, 4, 3, 2, 1};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr2));
        System.out.println("Output: " + solver.findLengthOfShortestSubarray(arr2)); // Expected: 4
        System.out.println();

        // Test Case 3
        int[] arr3 = {1, 2, 3};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr3));
        System.out.println("Output: " + solver.findLengthOfShortestSubarray(arr3)); // Expected: 0
        System.out.println();
    }
}
