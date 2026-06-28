package com.dsaguides.slidingwindow.fixsize;

import java.util.Arrays;

/*3208. Alternating Groups II*/

public class Problem13 {

    /**
     * Finds the number of alternating groups of size k in a circular colors array.
     * @param colors The circular array of colors (0 for red, 1 for blue).
     * @param k      The required size of the alternating groups.
     * @return The total number of alternating groups of size k.
     */
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int alternatingGroupsCount = 0;

        // Tracks the length of the current alternating sequence
        int alternatingLength = 1;

        // Since the array is circular, we need to check up to n + k - 2 elements
        // to cover all possible groups of size k that wrap around the boundary.
        int limit = n + k - 2;
        for (int i = 1; i <= limit; i++) {
            // Check if the current tile alternates with the previous one
            if (colors[i % n] != colors[(i - 1) % n]) {
                alternatingLength++;
            } else {
                // Alternating pattern is broken, reset the sequence length
                alternatingLength = 1;
            }

            // If we have an alternating sequence of at least size k,
            // we have found a valid alternating group of size k.
            if (alternatingLength >= k) {
                alternatingGroupsCount++;
            }
        }

        return alternatingGroupsCount;
    }

    // Main method to test the algorithm with different scenarios locally
    public static void main(String[] args) {
        Problem13 solver = new Problem13();

        // Test Case 1
        int[] colors1 = {0, 1, 0, 1, 0};
        int k1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input:  colors = " + Arrays.toString(colors1) + ", k = " + k1);
        System.out.println("Output: " + solver.numberOfAlternatingGroups(colors1, k1)); // Expected: 3
        System.out.println();

        // Test Case 2
        int[] colors2 = {0, 1, 0, 0, 1, 0, 1};
        int k2 = 6;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input:  colors = " + Arrays.toString(colors2) + ", k = " + k2);
        System.out.println("Output: " + solver.numberOfAlternatingGroups(colors2, k2)); // Expected: 2
        System.out.println();

        // Test Case 3
        int[] colors3 = {1, 1, 0, 1};
        int k3 = 4;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input:  colors = " + Arrays.toString(colors3) + ", k = " + k3);
        System.out.println("Output: " + solver.numberOfAlternatingGroups(colors3, k3)); // Expected: 0
        System.out.println();
    }
}
