package com.dsaguides.slidingwindow.variablesize;

/*424. Longest Repeating Character Replacement*/

public class Problem7 {

    /**
     * Finds the length of the longest substring containing the same letter
     * after performing at most k character replacements.
     * @param s The input string consisting of uppercase English letters.
     * @param k The maximum number of operations allowed.
     * @return The maximum length of a valid substring.
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] charCounts = new int[26]; // Frequency map for uppercase letters A-Z

        int left = 0;
        int maxFreqInWindow = 0; // Tracks the frequency of the most common character in the current window
        int maxLength = 0;

        for (int right = 0; right < n; right++) {
            char rChar = s.charAt(right);
            charCounts[rChar - 'A']++;

            // Update the maximum frequency of any single character in our current window
            maxFreqInWindow = Math.max(maxFreqInWindow, charCounts[rChar - 'A']);

            // Current window size is (right - left + 1).
            // The number of characters we would need to change is: (window size) - maxFreqInWindow.
            // If this number exceeds k, the window is invalid, so we shrink it from the left.
            while ((right - left + 1) - maxFreqInWindow > k) {
                char lChar = s.charAt(left);
                charCounts[lChar - 'A']--;
                left++;
            }

            // Update the maximum length of a valid window found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the sliding window algorithm locally
    public static void main(String[] args) {
        Problem7 solver = new Problem7();

        // Test Case 1
        String s1 = "ABAB";
        int k1 = 2;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + solver.characterReplacement(s1, k1)); // Expected: 4 (Replace A's to B's or vice-versa)
        System.out.println();

        // Test Case 2
        String s2 = "AABABBA";
        int k2 = 1;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + solver.characterReplacement(s2, k2)); // Expected: 4 (Replace middle 'A' to 'B' -> "AABBBBA" has "BBBB")
        System.out.println();

        // Test Case 3: Single character string
        String s3 = "A";
        int k3 = 0;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + solver.characterReplacement(s3, k3)); // Expected: 1
        System.out.println();
    }
}
