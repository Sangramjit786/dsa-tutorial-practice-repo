package com.dsaguides.slidingwindow;

import java.util.Arrays;

/*1423. Maximum Points You Can Obtain from Cards*/

public class Problem8 {

    /**
     * Calculates the maximum points you can obtain by taking exactly k cards
     * from either the beginning or the end of the card row.
     * Uses the classic sliding window to minimize the sum of the remaining (n - k) cards.
     * * @param cardPoints The array representing points of each card.
     * @param k          The number of cards to take.
     * @return The maximum score obtainable.
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;

        // Calculate the total sum of all cards
        for (int points : cardPoints) {
            totalSum += points;
        }

        // If we need to take all cards, return the total sum immediately
        if (k == n) {
            return totalSum;
        }

        // The size of the window we want to minimize (the cards we leave behind)
        int windowSize = n - k;
        int currentWindowSum = 0;

        // 1. Calculate the sum of the first window of size (n - k)
        for (int i = 0; i < windowSize; i++) {
            currentWindowSum += cardPoints[i];
        }

        int minWindowSum = currentWindowSum;

        // 2. Slide the window across the array
        for (int i = windowSize; i < n; i++) {
            // Add the element entering on the right, remove the element leaving on the left
            currentWindowSum += cardPoints[i] - cardPoints[i - windowSize];
            minWindowSum = Math.min(minWindowSum, currentWindowSum);
        }

        // The maximum score is the total sum minus the minimum subarray sum left behind
        return totalSum - minWindowSum;
    }

    // Main method to test the algorithm with the given examples
    public static void main(String[] args) {
        Problem8 solver = new Problem8();

        // Test Case 1
        int[] cardPoints1 = {1, 2, 3, 4, 5, 6, 1};
        int k1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: cardPoints = " + Arrays.toString(cardPoints1) + ", k = " + k1);
        System.out.println("Output: " + solver.maxScore(cardPoints1, k1)); // Expected: 12
        System.out.println();

        // Test Case 2
        int[] cardPoints2 = {2, 2, 2};
        int k2 = 2;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: cardPoints = " + Arrays.toString(cardPoints2) + ", k = " + k2);
        System.out.println("Output: " + solver.maxScore(cardPoints2, k2)); // Expected: 4
        System.out.println();

        // Test Case 3
        int[] cardPoints3 = {9, 7, 7, 9, 7, 7, 9};
        int k3 = 7;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: cardPoints = " + Arrays.toString(cardPoints3) + ", k = " + k3);
        System.out.println("Output: " + solver.maxScore(cardPoints3, k3)); // Expected: 55
        System.out.println();
    }
}
