package com.dsaguides.slidingwindow.variablesize;


/*3095. Shortest Subarray With OR at Least K I*/
public class Problem30 {

    public int minimumSubarrayLength(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int currentOr = 0;
        int[] bitCounts = new int[32]; // Tracks the frequency of '1' bits at each position

        for (int right = 0; right < nums.length; right++) {
            // 1. Add the incoming right element to our window bit tracker
            currentOr = updateBitCounts(bitCounts, nums[right], 1);

            // 2. Shrink the window from the left as long as the OR value satisfies the condition
            while (currentOr >= k && left <= right) {
                minLength = Math.min(minLength, right - left + 1);

                // Remove the outgoing left element from our window bit tracker
                currentOr = updateBitCounts(bitCounts, nums[left], -1);
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * Helper method to add or remove a number's bits from our tracker
     * and calculate the current window's reconstructed bitwise OR value.
     */
    private int updateBitCounts(int[] bitCounts, int num, int delta) {
        int currentOrValue = 0;
        for (int i = 0; i < 32; i++) {
            // Check if the i-th bit is set in the number
            if (((num >> i) & 1) == 1) {
                bitCounts[i] += delta;
            }
            // If at least one number in the window has this bit set, set it in the result
            if (bitCounts[i] > 0) {
                currentOrValue |= (1 << i);
            }
        }
        return currentOrValue;
    }

    public static void main(String[] args) {
        Problem30 solution = new Problem30();

        // Test Case 1
        int[] nums1 = {1, 2, 3};
        int k1 = 2;
        System.out.println("Example 1 Output (Expected: 1): " + solution.minimumSubarrayLength(nums1, k1));

        // Test Case 2
        int[] nums2 = {2, 1, 8};
        int k2 = 10;
        System.out.println("Example 2 Output (Expected: 3): " + solution.minimumSubarrayLength(nums2, k2));

        // Test Case 3
        int[] nums3 = {1, 2};
        int k3 = 0;
        System.out.println("Example 3 Output (Expected: 1): " + solution.minimumSubarrayLength(nums3, k3));
    }
}
