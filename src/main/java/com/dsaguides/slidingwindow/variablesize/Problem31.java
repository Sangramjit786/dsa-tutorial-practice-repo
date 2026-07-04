package com.dsaguides.slidingwindow.variablesize;


/*3097. Shortest Subarray With OR at Least K II*/
public class Problem31 {

    public int minimumSubarrayLength(int[] nums, int k) {
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int currentOr = 0;
        int[] bitCounts = new int[32]; // Keeps track of '1' bits across the 32-bit integers

        for (int right = 0; right < nums.length; right++) {
            // Add the right element's bits into our sliding window bit counter
            currentOr = updateBitCounts(bitCounts, nums[right], 1);

            // Shrink the window from the left as long as the OR total meets or exceeds k
            while (currentOr >= k && left <= right) {
                minLength = Math.min(minLength, right - left + 1);

                // Remove the left element's bits as it exits our window
                currentOr = updateBitCounts(bitCounts, nums[left], -1);
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    /**
     * Updates our bit frequency counter and dynamically recalculates the window's current OR value.
     */
    private int updateBitCounts(int[] bitCounts, int num, int delta) {
        int reconstructedOr = 0;
        for (int i = 0; i < 32; i++) {
            // If the i-th bit of the number is set, update its frequency in our window
            if (((num >> i) & 1) == 1) {
                bitCounts[i] += delta;
            }
            // If at least one active number in our window has this bit set, keep it hot
            if (bitCounts[i] > 0) {
                reconstructedOr |= (1 << i);
            }
        }
        return reconstructedOr;
    }

    public static void main(String[] args) {
        Problem31 solution = new Problem31();

        // Example 1
        int[] nums1 = {1, 2, 3};
        int k1 = 2;
        System.out.println("Example 1 Result (Expected: 1): " + solution.minimumSubarrayLength(nums1, k1));

        // Example 2
        int[] nums2 = {2, 1, 8};
        int k2 = 10;
        System.out.println("Example 2 Result (Expected: 3): " + solution.minimumSubarrayLength(nums2, k2));

        // Example 3
        int[] nums3 = {1, 2};
        int k3 = 0;
        System.out.println("Example 3 Result (Expected: 1): " + solution.minimumSubarrayLength(nums3, k3));
    }
}
