package com.dsaguides.slidingwindow.variablesize;


/*1358. Number of Substrings Containing All Three Characters*/
public class Problem16 {

    public int numberOfSubstrings(String s) {
        int[] counts = new int[3]; // counts[0]->'a', counts[1]->'b', counts[2]->'c'
        int left = 0;
        int totalSubstrings = 0;
        int n = s.length();

        for (int right = 0; right < n; right++) {
            // Include the current character in the window
            counts[s.charAt(right) - 'a']++;

            // While the window contains at least one 'a', 'b', and 'c'
            while (counts[0] > 0 && counts[1] > 0 && counts[2] > 0) {
                // All substrings starting from 'left' and ending at 'right' through 'n-1' are valid
                totalSubstrings += n - right;

                // Shrink the window from the left
                counts[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return totalSubstrings;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem16 solver = new Problem16();

        // Test Case 1
        String s1 = "abcabc";
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + solver.numberOfSubstrings(s1)); // Expected: 10
        System.out.println();

        // Test Case 2
        String s2 = "aaacb";
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: " + solver.numberOfSubstrings(s2)); // Expected: 3
        System.out.println();

        // Test Case 3
        String s3 = "abc";
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: s = \"" + s3 + "\"");
        System.out.println("Output: " + solver.numberOfSubstrings(s3)); // Expected: 1
        System.out.println();
    }
}
