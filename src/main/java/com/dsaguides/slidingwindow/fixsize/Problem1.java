package com.dsaguides.slidingwindow.fixsize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*30. Substring with Concatenation of All Words*/
public class Problem1 {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if(s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        Map<String, Integer> wordsCount = new HashMap<>();

        for(String word : words){
            wordsCount.put(word, wordsCount.getOrDefault(word, 0) + 1);
        }

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;
        int sLen = s.length();

        for(int i = 0; i < wordLen; i++){
            int left = i;
            int right = i;
            Map<String, Integer> currentCounts = new HashMap<>();
            int wordsUsed = 0;

            while(right + wordLen <= sLen){
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if(wordsCount.containsKey(word)){

                    currentCounts.put(word, currentCounts.getOrDefault(word, 0) + 1);
                    wordsUsed++;

                    while(currentCounts.get(word) > wordsCount.get(word)){
                        String leftWord = s.substring(left, left + wordLen);
                        currentCounts.put(leftWord, currentCounts.getOrDefault(leftWord, 0) - 1);
                        wordsUsed--;
                        left += wordLen;
                    }

                    if(wordsUsed == numWords){
                        result.add(left);
                    }

                } else {
                    currentCounts.clear();
                    wordsUsed = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Problem1 problem = new Problem1();

        // Test case 1
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        System.out.println("Test 1 - s: \"" + s1 + "\", words: " + Arrays.toString(words1));
        System.out.println("Result: " + problem.findSubstring(s1, words1));
        System.out.println("Expected: [0, 9]");
        System.out.println();

        // Test case 2
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        System.out.println("Test 2 - s: \"" + s2 + "\", words: " + Arrays.toString(words2));
        System.out.println("Result: " + problem.findSubstring(s2, words2));
        System.out.println("Expected: []");
        System.out.println();

        // Test case 3
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        System.out.println("Test 3 - s: \"" + s3 + "\", words: " + Arrays.toString(words3));
        System.out.println("Result: " + problem.findSubstring(s3, words3));
        System.out.println("Expected: [6, 9, 12]");
    }
}
