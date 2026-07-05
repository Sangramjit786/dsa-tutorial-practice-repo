package com.dsaguides.slidingwindow.variablesize;


import java.util.HashSet;
import java.util.Set;

/*find distinct palindromic substrings*/
public class Problem34 {

    public static int countDistinctPalindromes(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<String> distinctPalindromes = new HashSet<>();
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // 1. Odd length palindromes (e.g., centered at 'b' in "aba")
            expandAroundCenter(s, i, i, distinctPalindromes);

            // 2. Even length palindromes (e.g., centered between 'a' and 'a' in "aa")
            expandAroundCenter(s, i, i + 1, distinctPalindromes);
        }

        // Return the number of unique elements collected
        return distinctPalindromes.size();
    }

    private static void expandAroundCenter(String s, int left, int right, Set<String> set) {
        // Move outwards as long as characters match and indices are valid
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            set.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: "abaaa"
        String test1 = "abaaa";
        System.out.println("Input: " + test1 + " -> Distinct Count: " + countDistinctPalindromes(test1));
        // Expected Output: 5 ("a", "b", "aa", "aba", "aaa")

        // Test Case 2: "gpek"
        String test2 = "gpek";
        System.out.println("Input: " + test2 + " -> Distinct Count: " + countDistinctPalindromes(test2));
        // Expected Output: 4 ("g", "p", "e", "k")
    }

}
