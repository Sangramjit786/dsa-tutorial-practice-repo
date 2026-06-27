package com.dsaguides.slidingwindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 438. Find All Anagrams in a String*/


public class Problem3 {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resultList = new ArrayList<>();

        if(s.length() < p.length()){
            return resultList;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for(int i = 0; i < p.length(); i++){
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        if(Arrays.equals(pCount, sCount)){
            resultList.add(0);
        }

        int left = 0;
        for(int right = p.length(); right < s.length(); right++){
            sCount[s.charAt(right) - 'a']++;
            sCount[s.charAt(left) - 'a']--;

            left++;

            if(Arrays.equals(pCount, sCount)){
                resultList.add(left);
            }
        }
        return resultList;
    }

    // Main method to run and test the code locally
    public static void main(String[] args) {
        // Create an instance of the Solution class
        Problem3 solution = new Problem3();

        // Test Case 1: Standard example
        String s1 = "cbaebabacd";
        String p1 = "abc";
        System.out.println("Test Case 1:");
        System.out.println("Input: s = \"" + s1 + "\", p = \"" + p1 + "\"");
        System.out.println("Output: " + solution.findAnagrams(s1, p1)); // Expected: [0, 6]
        System.out.println();

        // Test Case 2: Overlapping anagrams
        String s2 = "abab";
        String p2 = "ab";
        System.out.println("Test Case 2:");
        System.out.println("Input: s = \"" + s2 + "\", p = \"" + p2 + "\"");
        System.out.println("Output: " + solution.findAnagrams(s2, p2)); // Expected: [0, 1, 2]
        System.out.println();

        // Test Case 3: Your previous TLE test case
        String s3 = "aaabb";
        String p3 = "bb";
        System.out.println("Test Case 3 (Previous TLE Case):");
        System.out.println("Input: s = \"" + s3 + "\", p = \"" + p3 + "\"");
        System.out.println("Output: " + solution.findAnagrams(s3, p3)); // Expected: [3]
    }
}
