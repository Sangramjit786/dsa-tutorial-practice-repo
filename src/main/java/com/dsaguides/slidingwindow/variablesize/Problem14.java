package com.dsaguides.slidingwindow.variablesize;

import java.util.HashMap;
import java.util.Map;

/*1297. Maximum Number of Occurrences of a Substring*/

public class Problem14 {

    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        if(s == null || s.length() == 0 || s.length() < minSize){
            return 0;
        }

        int maxCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        Map<String, Integer> substringCounts = new HashMap<>();
        int left = 0;
        int currentWindowFreq = 0;
        for(int right = 0; right < s.length(); right++){
            char rChar = s.charAt(right);
            map.put(rChar, map.getOrDefault(rChar, 0) + 1);
            currentWindowFreq++;

            while(map.size() > maxLetters || (right - left + 1) > minSize){
                char lChar = s.charAt(left);
                if(map.get(lChar) > 1){
                    map.put(lChar, map.get(lChar) - 1);
                } else if(map.get(lChar) == 1){
                    map.remove(lChar);
                }
                currentWindowFreq--;
                left++;
            }

            if(map.size() <= maxLetters && minSize == (right - left + 1)){
                String validString = s.substring(left, right + 1);
                int count = substringCounts.getOrDefault(validString, 0) + 1;
                substringCounts.put(validString, count);
                maxCount = Math.max(maxCount,count);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "aababcaab";
        int maxLetters1 = 2, minSize1 = 3, maxSize1 = 4;
        System.out.println("Example 1:");
        System.out.println("Input: s = \"" + s1 + "\", maxLetters = " + maxLetters1 +
                ", minSize = " + minSize1 + ", maxSize = " + maxSize1);
        System.out.println("Output: " + maxFreq(s1, maxLetters1, minSize1, maxSize1)); // Expected: 2
        System.out.println("---");

        // Example 2
        String s2 = "aaaa";
        int maxLetters2 = 1, minSize2 = 3, maxSize2 = 3;
        System.out.println("Example 2:");
        System.out.println("Input: s = \"" + s2 + "\", maxLetters = " + maxLetters2 +
                ", minSize = " + minSize2 + ", maxSize = " + maxSize2);
        System.out.println("Output: " + maxFreq(s2, maxLetters2, minSize2, maxSize2)); // Expected: 2
    }
}
