package com.dsaguides.slidingwindow.variablesize;


/*76. Minimum Window Substring*/
public class Problem2 {

    /**
     * Finds the minimum window substring of s such that every character in t
     * (including duplicates) is included in the window.
     * * @param s The source string.
     * @param t The target string containing characters to match.
     * @return The minimum window substring, or "" if none exists.
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Frequency map to store the count of characters needed from t.
        // Using an integer array of size 128 covers all ASCII letters and symbols.
        int[] targetMap = new int[128];
        for (char c : t.toCharArray()) {
            targetMap[c]++;
        }

        // 'count' tracks how many characters from t still need to be matched in our current window.
        int count = t.length();

        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = -1;

        // Slide the 'right' boundary of the window
        for (int right = 0; right < s.length(); right++) {
            char rChar = s.charAt(right);

            // If the character is part of our target string t, decrement the match count.
            // (Values > 0 in targetMap represent characters we still actively need).
            if (targetMap[rChar] > 0) {
                count--;
            }

            // Decrement the requirement of the current character.
            // Characters not in t will drop below 0.
            targetMap[rChar]--;

            // When our window contains all required characters of t (count == 0)
            while (count == 0) {
                // Update the minimum window coordinates if the current window is smaller
                int currentWindowLen = right - left + 1;
                if (currentWindowLen < minLen) {
                    minLen = currentWindowLen;
                    startIndex = left;
                }

                // Prepare to slide the 'left' boundary to contract the window
                char lChar = s.charAt(left);

                // Add the character back to the frequency requirements map
                targetMap[lChar]++;

                // If targetMap[lChar] goes above 0, it means we have discarded a character
                // that is vital to matching t, so we must increase 'count' to look for it again.
                if (targetMap[lChar] > 0) {
                    count++;
                }

                left++;
            }
        }

        // Return the smallest matched substring, or empty string if no valid window was found
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }

    // Main method to run and test the algorithm locally
    public static void main(String[] args) {
        Problem2 solver = new Problem2();

        // Test Case 1
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("Output: \"" + solver.minWindow(s1, t1) + "\""); // Expected: "BANC"
        System.out.println();

        // Test Case 2
        String s2 = "a";
        String t2 = "a";
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("Output: \"" + solver.minWindow(s2, t2) + "\""); // Expected: "a"
        System.out.println();

        // Test Case 3
        String s3 = "a";
        String t3 = "aa";
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("Output: \"" + solver.minWindow(s3, t3) + "\""); // Expected: ""
        System.out.println();
    }
}
