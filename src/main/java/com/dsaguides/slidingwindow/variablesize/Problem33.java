package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;


/*3306. Count of Substrings Containing Every Vowel and K Consonants II*/
public class Problem33 {

    public static void main(String[] args) {
        // Scenario 1
        System.out.println("Scenario 1 Output: " + countOfSubstrings("aeiou", 0)); // Expected: 1

        // Scenario 2
        System.out.println("Scenario 2 Output: " + countOfSubstrings("ieaouqqieaouqq", 1)); // Expected: 3
    }

    public static long countOfSubstrings(String word, int k) {
        long totalSubstrings = 0;
        int left = 0;
        int consonants = 0;
        int extraVowels = 0;
        Map<Character, Integer> vowelCounts = new HashMap<>();

        for (int right = 0; right < word.length(); right++) {
            char rChar = word.charAt(right);

            if (isVowel(rChar)) {
                vowelCounts.put(rChar, vowelCounts.getOrDefault(rChar, 0) + 1);
            } else {
                consonants++;
            }

            // 1. If we have too many consonants, slide the left pointer up
            while (consonants > k) {
                char lChar = word.charAt(left);
                if (isVowel(lChar)) {
                    vowelCounts.put(lChar, vowelCounts.get(lChar) - 1);
                    if (vowelCounts.get(lChar) == 0) {
                        vowelCounts.remove(lChar);
                    }
                } else {
                    consonants--;
                }
                left++;
                extraVowels = 0; // Reset extra vowels because the window boundary shifted
            }

            // 2. Shrink extra duplicate vowels from the left to keep the window minimal
            while (vowelCounts.size() == 5 && consonants == k && isVowel(word.charAt(left)) && vowelCounts.get(word.charAt(left)) > 1) {
                char lChar = word.charAt(left);
                vowelCounts.put(lChar, vowelCounts.get(lChar) - 1);
                extraVowels++;
                left++;
            }

            // 3. If the window matches all criteria, add the current minimal window
            // plus all valid sub-windows formed by the extra vowels we skipped
            if (vowelCounts.size() == 5 && consonants == k) {
                totalSubstrings += (1 + extraVowels);
            }
        }

        return totalSubstrings;
    }

    private static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
