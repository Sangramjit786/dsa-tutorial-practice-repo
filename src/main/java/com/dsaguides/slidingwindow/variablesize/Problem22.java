package com.dsaguides.slidingwindow.variablesize;


/*2024.Maximize the Confusion of an Exam*/
public class Problem22 {

    public int maxConsecutiveAnswers(String answerKey, int k) {
        // Return the maximum window possible for making either all 'T's or all 'F's
        return Math.max(getMaxConsecutive(answerKey, k, 'T'), getMaxConsecutive(answerKey, k, 'F'));
    }

    private int getMaxConsecutive(String answerKey, int k, char target) {
        int maxLen = 0;
        int left = 0;
        int countOtherChar = 0;

        for (int right = 0; right < answerKey.length(); right++) {
            // If the character doesn't match our target, it requires a flip
            if (answerKey.charAt(right) != target) {
                countOtherChar++;
            }

            // If flips exceed k, shrink the window from the left
            while (countOtherChar > k) {
                if (answerKey.charAt(left) != target) {
                    countOtherChar--;
                }
                left++;
            }

            // Update the maximum length of the valid window found
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem22 solver = new Problem22();

        // Test Case 1
        String answerKey1 = "TTFF";
        int k1 = 2;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: answerKey = \"" + answerKey1 + "\", k = " + k1);
        System.out.println("Output: " + solver.maxConsecutiveAnswers(answerKey1, k1)); // Expected: 4
        System.out.println();

        // Test Case 2
        String answerKey2 = "TFFT";
        int k2 = 1;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: answerKey = \"" + answerKey2 + "\", k = " + k2);
        System.out.println("Output: " + solver.maxConsecutiveAnswers(answerKey2, k2)); // Expected: 3
        System.out.println();

        // Test Case 3
        String answerKey3 = "TTFTTFTT";
        int k3 = 1;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: answerKey = \"" + answerKey3 + "\", k = " + k3);
        System.out.println("Output: " + solver.maxConsecutiveAnswers(answerKey3, k3)); // Expected: 5
        System.out.println();
    }
}
