package com.dsaguides.slidingwindow.variablesize;


/*2444. Count Subarrays With Fixed Bounds*/
public class Problem24 {

    public static long countSubarrays(int[] nums, long k) {
        long countSubArrays = 0;
        int left = 0;
        long currentSum = 0;

        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Maintain the window condition: score must be strictly less than k
            // Casting the length calculation to (long) prevents 32-bit integer overflow
            while (currentSum * (long) (right - left + 1) >= k) {
                currentSum -= nums[left];
                left++;
            }

            // If the window [left...right] is valid, then all valid subarrays
            // ending at 'right' equal the current window length.
            countSubArrays += (right - left + 1);
        }

        return countSubArrays;
    }

    public static void main(String[] args) {
        // Test Case 1 from the problem statement
        int[] nums1 = {2, 1, 4, 3, 5};
        long k1 = 10;
        System.out.println("--- Test Case 1 ---");
        System.out.println("Input Array: [2, 1, 4, 3, 5], k = " + k1);
        System.out.println("Output: " + countSubarrays(nums1, k1)); // Expected: 6
        System.out.println();

        // Test Case 2 from the problem statement
        int[] nums2 = {1, 1, 1};
        long k2 = 5;
        System.out.println("--- Test Case 2 ---");
        System.out.println("Input Array: [1, 1, 1], k = " + k2);
        System.out.println("Output: " + countSubarrays(nums2, k2)); // Expected: 5
        System.out.println();

    }
}
