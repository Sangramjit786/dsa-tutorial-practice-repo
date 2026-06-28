package com.dsaguides.slidingwindow.fixsize;

import java.util.HashMap;
import java.util.Map;

/*1100. Find K-Length Substrings With No Repeated Characters */

public class Problem5 {

    public int numKLenSubstrNoRepeats(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        if(s.length() < k){
            return 0;
        }

        for(int i = 0; i < k; i++){
            charCount.put(s.charAt(i), charCount.getOrDefault(s.charAt(i), 0) + 1);
        }
        int count = charCount.size() == k ? 1 : 0;

        for(int right = k ; right < s.length(); right++){
            charCount.put(s.charAt(right), charCount.getOrDefault(s.charAt(right), 0) + 1);
            charCount.put(s.charAt(right - k), charCount.getOrDefault(s.charAt(right - k), 0) - 1);
            if(charCount.get(s.charAt(right - k)) == 0){
                charCount.remove(s.charAt(right - k));
            }
            if(charCount.size() == k){
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Problem5 solver = new Problem5();

        // Test Case 1
        String s1 = "havefunonleetcode";
        int k1 = 5;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Expected Output: 6");
        System.out.println("Actual Output:   " + solver.numKLenSubstrNoRepeats(s1, k1));
        System.out.println();

        // Test Case 2: k is larger than the string length
        String s2 = "home";
        int k2 = 5;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Expected Output: 0");
        System.out.println("Actual Output:   " + solver.numKLenSubstrNoRepeats(s2, k2));
        System.out.println();

        // Test Case 3: Edge case where k is larger than the alphabet size
        String s3 = "abcdefghijklmnopqrstuvwxyzabc";
        int k3 = 27;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: s = \"" + s3 + "\", k = " + k3);
        System.out.println("Expected Output: 0");
        System.out.println("Actual Output:   " + solver.numKLenSubstrNoRepeats(s3, k3));
        System.out.println();
    }
}
