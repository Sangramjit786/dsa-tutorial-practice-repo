package com.dsaguides.slidingwindow.fixsize;

import java.util.Arrays;

/*3439. Reschedule Meetings for Maximum Free Time I*/

public class Problem15 {

    /**
     * Finds the maximum amount of free time possible after rescheduling at most k meetings.
     * @param eventTime The total duration of the event (from 0 to eventTime).
     * @param k         The maximum number of meetings we can reschedule.
     * @param startTime The start times of the non-overlapping meetings.
     * @param endTime   The end times of the non-overlapping meetings.
     * @return The maximum continuous free time possible.
     */
    public int maxConsecutiveAnswering(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;

        // There are always exactly n + 1 gaps of free time in a schedule with n meetings.
        int[] gaps = new int[n + 1];

        // Gap 0: Time from 0 to the start of the first meeting
        gaps[0] = startTime[0];

        // Gaps 1 to n-1: Time between consecutive meetings
        for (int i = 1; i < n; i++) {
            gaps[i] = startTime[i] - endTime[i - 1];
        }

        // Gap n: Time from the end of the last meeting to the eventTime
        gaps[n] = eventTime - endTime[n - 1];

        // Rescheduling at most k meetings allows us to merge up to k + 1 consecutive gaps.
        int windowSize = k + 1;
        int currentGapSum = 0;

        // 1. Calculate the sum of the first window of size (k + 1)
        for (int i = 0; i < windowSize; i++) {
            currentGapSum += gaps[i];
        }

        int maxFreeTime = currentGapSum;

        // 2. Slide the window across the gaps array
        for (int i = windowSize; i < gaps.length; i++) {
            // Add the new gap entering on the right, remove the old gap leaving on the left
            currentGapSum += gaps[i] - gaps[i - windowSize];
            maxFreeTime = Math.max(maxFreeTime, currentGapSum);
        }

        return maxFreeTime;
    }

    // Main method to test the sliding window algorithm with the given examples
    public static void main(String[] args) {
        Problem15 solver = new Problem15();

        // Test Case 1
        int eventTime1 = 5;
        int k1 = 1;
        int[] startTime1 = {1, 3};
        int[] endTime1 = {2, 5};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: eventTime = " + eventTime1 + ", k = " + k1);
        System.out.println("startTime = " + Arrays.toString(startTime1) + ", endTime = " + Arrays.toString(endTime1));
        System.out.println("Output: " + solver.maxConsecutiveAnswering(eventTime1, k1, startTime1, endTime1)); // Expected: 2
        System.out.println();

        // Test Case 2
        int eventTime2 = 10;
        int k2 = 1;
        int[] startTime2 = {0, 2, 9};
        int[] endTime2 = {1, 4, 10};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: eventTime = " + eventTime2 + ", k = " + k2);
        System.out.println("startTime = " + Arrays.toString(startTime2) + ", endTime = " + Arrays.toString(endTime2));
        System.out.println("Output: " + solver.maxConsecutiveAnswering(eventTime2, k2, startTime2, endTime2)); // Expected: 6
        System.out.println();

        // Test Case 3
        int eventTime3 = 5;
        int k3 = 2;
        int[] startTime3 = {0, 1, 2, 3, 4};
        int[] endTime3 = {1, 2, 3, 4, 5};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: eventTime = " + eventTime3 + ", k = " + k3);
        System.out.println("startTime = " + Arrays.toString(startTime3) + ", endTime = " + Arrays.toString(endTime3));
        System.out.println("Output: " + solver.maxConsecutiveAnswering(eventTime3, k3, startTime3, endTime3)); // Expected: 0
        System.out.println();
    }
}
