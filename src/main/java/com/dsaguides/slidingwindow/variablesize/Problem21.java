package com.dsaguides.slidingwindow.variablesize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem21 {

    public int[] distinctNumbers(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        if(nums.length < k){
            return new int[0];
        }
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < k; i++){
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        result[0] = freqMap.size();

        int left = 0;
        for(int right = k; right < nums.length; right++){
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            if(freqMap.get(nums[left]) > 1){
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
            } else if (freqMap.get(nums[left]) == 1) {
                freqMap.remove(nums[left]);
            }

            left++;
            result[left] = freqMap.size();
        }
        return result;
    }

    // Main method to test the algorithm with the given examples locally
    public static void main(String[] args) {
        Problem21 solver = new Problem21();

        // Test Case 1
        int[] arr1 = {1, 2, 3, 2, 2, 1, 3};
        int k1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr1) + ", k = " + k1);
        System.out.println("Output: " + Arrays.toString(solver.distinctNumbers(arr1, k1))); // Expected: [3, 2, 2, 2, 3]
        System.out.println();

        // Test Case 2
        int[] arr2 = {1, 1, 1, 1, 2, 3, 4};
        int k2 = 4;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: arr = " + Arrays.toString(arr2) + ", k = " + k2);
        System.out.println("Output: " + Arrays.toString(solver.distinctNumbers(arr2, k2))); // Expected: [1, 2, 3, 4]
        System.out.println();
    }
}
