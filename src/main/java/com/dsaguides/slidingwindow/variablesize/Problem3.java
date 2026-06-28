package com.dsaguides.slidingwindow.variablesize;


/*159. Longest Substring with At Most Two Distinct Characters*/
public class Problem3 {

    /**
     * Finds the length of the longest substring that contains at most two distinct characters.
     * @param s The input string containing English letters.
     * @return The length of the longest valid substring.
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int maxLength = 0;

        // Sliding window pointers
        int left = 0;

        // Frequency array to keep track of character occurrences inside the window.
        // Using 128 covers all English uppercase and lowercase letters.
        int[] freq = new int[128];
        int distinctCount = 0;

        for (int right = 0; right < n; right++) {
            char rChar = s.charAt(right);

            // If this is a new character in our current window, increment the unique count
            if (freq[rChar] == 0) {
                distinctCount++;
            }
            freq[rChar]++;

            // If we have more than 2 distinct characters, shrink the window from the left
            while (distinctCount > 2) {
                char lChar = s.charAt(left);
                freq[lChar]--;

                // If the frequency of the left character drops to 0, it is no longer in our window
                if (freq[lChar] == 0) {
                    distinctCount--;
                }
                left++; // Slide the left boundary forward
            }

            // Update the maximum length of a valid window found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem3 solver = new Problem3();

        // Test Case 1
        String s1 = "eceba";
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input:  s = \"" + s1 + "\"");
        System.out.println("Output: " + solver.lengthOfLongestSubstringTwoDistinct(s1)); // Expected: 3 ("ece")
        System.out.println();

        // Test Case 2
        String s2 = "ccaabbb";
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input:  s = \"" + s2 + "\"");
        System.out.println("Output: " + solver.lengthOfLongestSubstringTwoDistinct(s2)); // Expected: 5 ("aabbb")
        System.out.println();

        // Test Case 3: Single character string
        String s3 = "a";
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input:  s = \"" + s3 + "\"");
        System.out.println("Output: " + solver.lengthOfLongestSubstringTwoDistinct(s3)); // Expected: 1 ("a")
        System.out.println();
    }
}
