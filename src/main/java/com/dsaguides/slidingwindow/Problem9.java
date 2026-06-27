package com.dsaguides.slidingwindow;

import java.util.Arrays;

/*1652. Defuse the Bomb*/

public class Problem9 {

    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];

        // Base case: If k is 0, replace every number with 0
        if (k == 0) {
            return result; // Java initializes int arrays with 0s by default
        }

        // Define the initial sliding window bounds for i = 0
        int start = 1;
        int end = k;

        // If k is negative, the window shifts to the previous |k| elements
        if (k < 0) {
            start = n - Math.abs(k);
            end = n - 1;
        }

        int currentSum = 0;

        // 1. Calculate the sum of the very first window
        for (int i = start; i <= end; i++) {
            currentSum += code[i % n]; // % n handles circular wrapping
        }

        // 2. Slide the window for each element in the array
        for (int i = 0; i < n; i++) {
            // Assign the current window's sum to the result array
            result[i] = currentSum;

            // Remove the element that is leaving the window
            currentSum -= code[start % n];

            // Slide both pointers forward by 1 step
            start++;
            end++;

            // Add the new element that is entering the window
            currentSum += code[end % n];
        }

        return result;

    }

    public static void main(String[] args) {
        Problem9 solver = new Problem9();

        // Test Case 1: k > 0
        int[] code1 = {5, 7, 1, 4};
        int k1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: code = " + Arrays.toString(code1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(solver.decrypt(code1, k1)));
        // Expected: [12, 10, 16, 13]
        System.out.println();

        // Test Case 2: k == 0
        int[] code2 = {1, 2, 3, 4};
        int k2 = 0;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: code = " + Arrays.toString(code2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(solver.decrypt(code2, k2)));
        // Expected: [0, 0, 0, 0]
        System.out.println();

        // Test Case 3: k < 0
        int[] code3 = {2, 4, 9, 3};
        int k3 = -2;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: code = " + Arrays.toString(code3) + ", k = " + k3);
        System.out.println("Output: " + Arrays.toString(solver.decrypt(code3, k3)));
        // Expected: [12, 5, 6, 13]
        System.out.println();
    }
}
