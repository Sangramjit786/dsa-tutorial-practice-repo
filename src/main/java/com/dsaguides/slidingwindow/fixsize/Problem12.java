package com.dsaguides.slidingwindow.fixsize;

import java.util.Arrays;

/*3206. Alternating Groups I*/

public class Problem12 {

    /**
     * Finds the number of alternating groups of size 3 in a circular colors array.
     * @param colors The circular array of colors (0 for red, 1 for blue).
     * @return The total number of alternating groups.
     */
    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        int alternatingGroupsCount = 0;

        // A sliding window of size 3 starting at index i
        // The three elements are: i, (i + 1) % n, and (i + 2) % n
        for (int i = 0; i < n; i++) {
            int left = colors[i];
            int middle = colors[(i + 1) % n];
            int right = colors[(i + 2) % n];

            // In an alternating group of size 3:
            // 1. The middle color must be different from the left color.
            // 2. The middle color must be different from the right color.
            if (middle != left && middle != right) {
                alternatingGroupsCount++;
            }
        }

        return alternatingGroupsCount;
    }

    // Main method to test the algorithm with different scenarios locally
    public static void main(String[] args) {
        Problem12 solver = new Problem12();

        // Test Case 1
        int[] colors1 = {1, 1, 1};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input:  " + Arrays.toString(colors1));
        System.out.println("Output: " + solver.numberOfAlternatingGroups(colors1)); // Expected: 0
        System.out.println();

        // Test Case 2
        int[] colors2 = {0, 1, 0, 0, 1};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input:  " + Arrays.toString(colors2));
        System.out.println("Output: " + solver.numberOfAlternatingGroups(colors2)); // Expected: 3
        System.out.println();

        // Test Case 3: Perfectly alternating circle
        int[] colors3 = {0, 1, 0, 1, 0, 1};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input:  " + Arrays.toString(colors3));
        System.out.println("Output: " + solver.numberOfAlternatingGroups(colors3)); // Expected: 6
        System.out.println();
    }
}
