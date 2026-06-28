package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;

/*487. Max Consecutive Ones II*/

public class Problem8 {

    public int findMaxConsecutiveOnes(int[] nums) {
        if(nums.length == 0){
            return 0;
        }

        int n = nums.length;
        int[] binaryCounts = new int[2];

        int left = 0;
        int maxFreqWindow = 0;
        int maxLength = 0;

        for(int right = 0; right < n; right++){
            binaryCounts[nums[right]]++;

            maxFreqWindow = Math.max(maxFreqWindow, binaryCounts[nums[right]]);

            while ((right - left + 1) - maxFreqWindow > 1){
                binaryCounts[nums[left]]--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return  maxLength;
    }


    public static void main(String[] args) {
        Problem8 solver = new Problem8();

        // Test Case 1
        int[] nums1 = {1, 0, 1, 1, 0};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input:  nums = " + Arrays.toString(nums1));
        System.out.println("Output: " + solver.findMaxConsecutiveOnes(nums1)); // Expected: 4 (Flip first 0 to get [1,1,1,1,0])
        System.out.println();

        // Test Case 2
        int[] nums2 = {1, 0, 1, 1, 0, 1};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input:  nums = " + Arrays.toString(nums2));
        System.out.println("Output: " + solver.findMaxConsecutiveOnes(nums2)); // Expected: 4
        System.out.println();

        // Test Case 3: Array of all zeros
        int[] nums3 = {0, 0, 0};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input:  nums = " + Arrays.toString(nums3));
        System.out.println("Output: " + solver.findMaxConsecutiveOnes(nums3)); // Expected: 1 (Flip any single zero)
        System.out.println();
    }
}
