package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;


/*3305. Count of Substrings Containing Every Vowel and K Consonants I*/
public class Problem32 {
    public int countOfSubstrings(String word, int k) {
        // Exactly k consonants = (At least k consonants) - (At least k + 1 consonants)
        return countAtLeast(word, k) - countAtLeast(word, k + 1);
    }

    private int countAtLeast(String word, int k) {
        int n = word.length();
        int left = 0;
        int consonantCount = 0;
        int validSubstringsCount = 0;

        // Track the frequency of vowels in the current window
        Map<Character, Integer> vowelFreq = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char rightChar = word.charAt(right);

            if (isVowel(rightChar)) {
                vowelFreq.put(rightChar, vowelFreq.getOrDefault(rightChar, 0) + 1);
            } else {
                consonantCount++;
            }

            // Shrink the window as long as it contains all 5 vowels and AT LEAST k consonants
            while (vowelFreq.size() == 5 && consonantCount >= k) {
                // If the window from 'left' to 'right' is valid, then all substrings
                // starting at 'left' and ending anywhere from 'right' to the end of the string are also valid.
                validSubstringsCount += (n - right);

                char leftChar = word.charAt(left);
                if (isVowel(leftChar)) {
                    vowelFreq.put(leftChar, vowelFreq.get(leftChar) - 1);
                    if (vowelFreq.get(leftChar) == 0) {
                        vowelFreq.remove(leftChar);
                    }
                } else {
                    consonantCount--;
                }
                left++;
            }
        }

        return validSubstringsCount;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void main(String[] args) {
        Problem32 solution = new Problem32();

        // Example 1
        String word1 = "aeioqq";
        int k1 = 1;
        System.out.println("Example 1 Output (Expected: 0): " + solution.countOfSubstrings(word1, k1));

        // Example 2
        String word2 = "aeiou";
        int k2 = 0;
        System.out.println("Example 2 Output (Expected: 1): " + solution.countOfSubstrings(word2, k2));

        // Example 3
        String word3 = "ieaouqqieaouqq";
        int k3 = 1;
        System.out.println("Example 3 Output (Expected: 3): " + solution.countOfSubstrings(word3, k3));
    }
}
