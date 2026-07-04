package com.dsaguides.slidingwindow.variablesize;


/*2444. Count Subarrays With Fixed Bounds*/
public class Problem25 {

    public static long countSubarrays(int[] nums, int minK, int maxK) {
        long totalSubarrays = 0;

        // Track the most recent positions of critical boundaries
        int lastMinKPos = -1;
        int lastMaxKPos = -1;
        int lastBadPos = -1; // Position of the last element out of [minK, maxK] bounds

        for (int i = 0; i < nums.length; i++) {
            // 1. If the current element is out of bounds, it's a "wall". Update its position.
            if (nums[i] < minK || nums[i] > maxK) {
                lastBadPos = i;
            }

            // 2. Track the latest indices where minK and maxK appear
            if (nums[i] == minK) {
                lastMinKPos = i;
            }
            if (nums[i] == maxK) {
                lastMaxKPos = i;
            }

            // 3. Find the start of the valid window.
            // A valid subarray must contain both the latest minK and maxK,
            // meaning its start index must be less than or equal to the smaller of the two positions.
            int validWindowStart = Math.min(lastMinKPos, lastMaxKPos);

            // 4. If this start index sits to the right of our last bad position "wall",
            // the number of valid subarrays ending at index 'i' is exactly (validWindowStart - lastBadPos).
            if (validWindowStart > lastBadPos) {
                totalSubarrays += (validWindowStart - lastBadPos);
            }
        }

        return totalSubarrays;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 3, 5, 2, 7, 5};
        int minK1 = 1;
        int maxK1 = 5;
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input Array: [1, 3, 5, 2, 7, 5], minK = 1, maxK = 5");
        System.out.println("Output: " + countSubarrays(nums1, minK1, maxK1)); // Expected: 2 ([1,3,5] and [1,3,5,2])
        System.out.println();

        // Test Case 2
        int[] nums2 = {1, 1, 1, 1};
        int minK2 = 1;
        int maxK2 = 1;
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input Array: [1, 1, 1, 1], minK = 1, maxK = 1");
        System.out.println("Output: " + countSubarrays(nums2, minK2, maxK2)); // Expected: 10
        System.out.println();
    }
}
