package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;


/*795. Number of Subarrays with Bounded Maximum*/
public class Problem9 {

    /**
     * Finds the number of contiguous subarrays such that the maximum element
     * in the subarray is in the range [left, right].
     * @param nums  The input array of integers.
     * @param left  The lower bound of the range.
     * @param right The upper bound of the range.
     * @return The total count of valid subarrays.
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length;
        int totalSubarrays = 0;

        // 'start' marks the left boundary of our active window of elements <= 'right'
        int start = 0;

        // 'validCount' tracks the number of valid subarrays ending at the current index
        int validCount = 0;

        for (int i = 0; i < n; i++) {
            // Case 1: The element is too big. It breaks our window.
            if (nums[i] > right) {
                start = i + 1;
                validCount = 0;
            }
            // Case 2: The element is within the valid bounded range [left, right].
            // It can form a valid subarray starting anywhere from 'start' up to 'i'.
            else if (nums[i] >= left) {
                validCount = i - start + 1;
                totalSubarrays += validCount;
            }
            // Case 3: The element is smaller than 'left'.
            // It cannot form a valid subarray on its own, but it can extend all
            // valid subarrays that ended at the previous index.
            else {
                totalSubarrays += validCount;
            }
        }

        return totalSubarrays;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem9 solver = new Problem9();

        // Test Case 1
        int[] nums1 = {2, 1, 4, 3};
        int left1 = 2, right1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", left = " + left1 + ", right = " + right1);
        System.out.println("Output: " + solver.numSubarrayBoundedMax(nums1, left1, right1)); // Expected: 3 ([2], [2, 1], [3])
        System.out.println();

        // Test Case 2
        int[] nums2 = {2, 9, 2, 5, 6};
        int left2 = 2, right2 = 8;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", left = " + left2 + ", right = " + right2);
        System.out.println("Output: " + solver.numSubarrayBoundedMax(nums2, left2, right2)); // Expected: 7
        System.out.println();
    }
}
