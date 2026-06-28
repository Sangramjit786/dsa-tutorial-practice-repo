package com.dsaguides.slidingwindow.variablesize;

import java.util.HashSet;
import java.util.Set;

/*3. Longest Substring Without Repeating Characters*/

public class Problem1 {

    /**
     * Finds the length of the longest substring without repeating characters.
     * @param s The input string containing letters, digits, symbols, and spaces.
     * @return The length of the longest unique substring.
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int maxLength = 0;

        // This set acts as our sliding window to track the unique characters currently inside it.
        Set<Character> charSet = new HashSet<>();

        // 'left' and 'right' represent the boundaries of our sliding window
        int left = 0;
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);

            // If we encounter a duplicate character, we shrink the window from the left
            // until the duplicate character is removed from our set.
            while (charSet.contains(currentChar)) {
                charSet.remove(s.charAt(left));
                left++;
            }

            // Add the current character to the window and update our maximum length
            charSet.add(currentChar);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the algorithm with the given examples
    public static void main(String[] args) {
        Problem1 solver = new Problem1();

        // Test Case 1
        String s1 = "abcabcbb";
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input:  s = \"" + s1 + "\"");
        System.out.println("Output: " + solver.lengthOfLongestSubstring(s1)); // Expected: 3 ("abc")
        System.out.println();

        // Test Case 2
        String s2 = "bbbbb";
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input:  s = \"" + s2 + "\"");
        System.out.println("Output: " + solver.lengthOfLongestSubstring(s2)); // Expected: 1 ("b")
        System.out.println();

        // Test Case 3
        String s3 = "pwwkew";
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input:  s = \"" + s3 + "\"");
        System.out.println("Output: " + solver.lengthOfLongestSubstring(s3)); // Expected: 3 ("wke")
        System.out.println();
    }
}
