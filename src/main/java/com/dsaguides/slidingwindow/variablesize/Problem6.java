package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;

/*340. Longest Substring with At Most K Distinct Characters */

public class Problem6 {

    /**
     * Finds the length of the longest substring of s that contains at most k distinct characters.
     * @param s The input string.
     * @param k The maximum allowed number of distinct characters.
     * @return The maximum length of a valid substring.
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() < k){
            return 0;
        }

        int maxLen = 0;

        Map<Character, Integer> charFreqMap = new HashMap<>();

        int left = 0;

        for(int right = 0; right < s.length(); right++){
            char rChar = s.charAt(right);
            charFreqMap.put(rChar, charFreqMap.getOrDefault(rChar, 0) + 1);

            while(charFreqMap.size() > k){
                char lChar = s.charAt(left);
                if(charFreqMap.get(lChar) > 1){
                    charFreqMap.put(lChar, charFreqMap.get(lChar) - 1);
                } else if(charFreqMap.get(lChar) == 1){
                    charFreqMap.remove(lChar);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Main method to test the sliding window algorithm locally
    public static void main(String[] args) {
        Problem6 solver = new Problem6();

        // Test Case 1
        String s1 = "eceba";
        int k1 = 2;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + solver.lengthOfLongestSubstringKDistinct(s1, k1)); // Expected: 3 ("ece")
        System.out.println();

        // Test Case 2
        String s2 = "aa";
        int k2 = 1;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + solver.lengthOfLongestSubstringKDistinct(s2, k2)); // Expected: 2 ("aa")
        System.out.println();

        // Test Case 3: Empty string or k = 0
        String s3 = "abcdef";
        int k3 = 0;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Output: " + solver.lengthOfLongestSubstringKDistinct(s3, k3)); // Expected: 0
        System.out.println();
    }
}
