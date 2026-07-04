package com.dsaguides.slidingwindow.variablesize;


/*2516. Take K of Each Character From Left and Right*/

public class Problem26 {

    public static int takeCharacters(String s, int k) {
        // Step 1: Count total occurrences of 'a', 'b', and 'c' in the entire string
        int[] totalCounts = new int[3];
        for (int i = 0; i < s.length(); i++) {
            totalCounts[s.charAt(i) - 'a']++;
        }

        // Step 2: If the total string doesn't even have k of each character, it's impossible
        if (totalCounts[0] < k || totalCounts[1] < k || totalCounts[2] < k) {
            return -1;
        }

        // Step 3: Sliding Window to find the maximum middle part we can leave behind
        int maxWindowLength = 0;
        int left = 0;
        int[] windowCounts = new int[3]; // Tracks characters inside our "leave behind" window

        for (int right = 0; right < s.length(); right++) {
            // Add the current character to our window
            windowCounts[s.charAt(right) - 'a']++;

            // If taking this character means the outside characters drop below k,
            // shrink the window from the left until the outside pool is valid again.
            // (Total in string - Current inside window) = Amount remaining outside
            while (totalCounts[0] - windowCounts[0] < k ||
                    totalCounts[1] - windowCounts[1] < k ||
                    totalCounts[2] - windowCounts[2] < k) {

                windowCounts[s.charAt(left) - 'a']--;
                left++;
            }

            // Update the maximum middle window size we have successfully kept
            maxWindowLength = Math.max(maxWindowLength, right - left + 1);
        }

        // The minimum minutes/characters taken is the total length minus the maximum middle chunk left behind
        return s.length() - maxWindowLength;
    }

    public static void main(String[] args) {
        // Test Case 1
        String s1 = "aabaaaacaabc";
        int k1 = 2;
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input: s = \"" + s1 + "\", k = " + k1);
        System.out.println("Output: " + takeCharacters(s1, k1)); // Expected: 8
        System.out.println();

        // Test Case 2
        String s2 = "a";
        int k2 = 1;
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input: s = \"" + s2 + "\", k = " + k2);
        System.out.println("Output: " + takeCharacters(s2, k2)); // Expected: -1
        System.out.println();
    }
}
