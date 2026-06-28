package com.dsaguides.slidingwindow.fixsize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* 187. Repeated DNA Sequences */
public class Problem2 {

    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        // Loop through the string, stopping where a 10-letter window can no longer fit
        for (int i = 0; i <= s.length() - 10; i++) {
            String substring = s.substring(i, i + 10);

            // If the substring is already in 'seen', add it to 'repeated'
            if (!seen.add(substring)) {
                repeated.add(substring);
            }
        }

        // Convert the set of repeated sequences back into a list
        return new ArrayList<>(repeated);
    }

    // Main method to run and test the program
    public static void main(String[] args) {
        // Test Case 1
        String dna1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> result1 = findRepeatedDnaSequences(dna1);
        System.out.println("Test Case 1 Input:  " + dna1);
        System.out.println("Test Case 1 Output: " + result1);
        System.out.println();

        // Test Case 2
        String dna2 = "AAAAAAAAAAAAA";
        List<String> result2 = findRepeatedDnaSequences(dna2);
        System.out.println("Test Case 2 Input:  " + dna2);
        System.out.println("Test Case 2 Output: " + result2);
    }
}
