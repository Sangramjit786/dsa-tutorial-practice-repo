package com.dsaguides.slidingwindow.variablesize;


/*1208. Get Equal Substrings Within Budget*/
public class Problem13 {

    public static int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int maxLen = 0;
        int currentCost = 0;
        int left = 0;

        // The 'right' pointer expands the window
        for (int right = 0; right < n; right++) {
            // Add the transformation cost of the current character to our running total
            currentCost += Math.abs(s.charAt(right) - t.charAt(right));

            // If the total cost exceeds our budget, shrink the window from the 'left'
            while (currentCost > maxCost) {
                currentCost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }

            // Calculate the valid window size and update the maximum length
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "abcd";
        String t1 = "bcdf";
        int maxCost1 = 3;
        System.out.println("Example 1:");
        System.out.println("Input: s = \"" + s1 + "\", t = \"" + t1 + "\", maxCost = " + maxCost1);
        System.out.println("Output: " + equalSubstring(s1, t1, maxCost1)); // Expected: 3
        System.out.println("---");

        // Example 2
        String s2 = "abcd";
        String t2 = "cdef";
        int maxCost2 = 3;
        System.out.println("Example 2:");
        System.out.println("Input: s = \"" + s2 + "\", t = \"" + t2 + "\", maxCost = " + maxCost2);
        System.out.println("Output: " + equalSubstring(s2, t2, maxCost2)); // Expected: 1
        System.out.println("---");

        // Example 3
        String s3 = "abcd";
        String t3 = "acde";
        int maxCost3 = 0;
        System.out.println("Example 3:");
        System.out.println("Input: s = \"" + s3 + "\", t = \"" + t3 + "\", maxCost = " + maxCost3);
        System.out.println("Output: " + equalSubstring(s3, t3, maxCost3)); // Expected: 1
    }
}
