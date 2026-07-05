package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;

/*2062. Count Vowel Substrings of a String*/

public class Problem23 {

    public int countVowelSubstrings(String word) {
        // Substrings with exactly 5 unique vowels
        // = (vowel substrings with <= 5 unique vowels) - (vowel substrings with <= 4 unique vowels)
        return countAtMostKVowels(word, 5) - countAtMostKVowels(word, 4);
    }

    // Helper method to count vowel-only substrings with at most K unique vowels
    private int countAtMostKVowels(String word, int k) {
        int count = 0;
        int left = 0;
        Map<Character, Integer> vowelFreq = new HashMap<>();

        for (int right = 0; right < word.length(); right++) {
            char c = word.charAt(right);

            // If it's not a vowel, reset the window entirely
            if (!isVowel(c)) {
                vowelFreq.clear();
                left = right + 1;
                continue;
            }

            // Record the vowel frequency
            vowelFreq.put(c, vowelFreq.getOrDefault(c, 0) + 1);

            // If unique vowels exceed K, shrink the window from the left
            while (vowelFreq.size() > k) {
                char leftChar = word.charAt(left);
                vowelFreq.put(leftChar, vowelFreq.get(leftChar) - 1);
                if (vowelFreq.get(leftChar) == 0) {
                    vowelFreq.remove(leftChar);
                }
                left++;
            }

            // The number of valid substrings ending at 'right' is equal to the window size
            count += (right - left + 1);
        }

        return count;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem23 solver = new Problem23();

        // Test Case 1
        String word1 = "aeiouu";
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: word = \"" + word1 + "\"");
        System.out.println("Output: " + solver.countVowelSubstrings(word1)); // Expected: 2
        System.out.println();

        // Test Case 2
        String word2 = "unicornarihan";
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: word = \"" + word2 + "\"");
        System.out.println("Output: " + solver.countVowelSubstrings(word2)); // Expected: 0
        System.out.println();

        // Test Case 3
        String word3 = "cuaieuouac";
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: word = \"" + word3 + "\"");
        System.out.println("Output: " + solver.countVowelSubstrings(word3)); // Expected: 7
        System.out.println();
    }
}
