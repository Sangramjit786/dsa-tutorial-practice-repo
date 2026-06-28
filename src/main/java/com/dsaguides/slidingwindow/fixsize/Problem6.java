package com.dsaguides.slidingwindow.fixsize;

import java.util.Arrays;

/*1151. Minimum Swaps to Group All 1's Together */

public class Problem6 {

    public int minSwaps(int[] data) {
        int k = 0;
        for(int num : data){
            if(num == 1){
                k++;
            }
        }

        int t = 0;

        for(int i = 0; i < k; i++){
            t += data[i];
        }

        int max = t;

        for(int i = k; i < data.length; i++){
            t += data[i];
            t -= data[i - k];
            max = Math.max(max, t);
        }

        return  k - max;

    }



    public static void main(String[] args) {
        Problem6 solver = new Problem6();

        // Test Case 1
        int[] data1 = {1, 0, 1, 0, 1};
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input:  " + Arrays.toString(data1));
        System.out.println("Output: " + solver.minSwaps(data1)); // Expected: 1
        System.out.println();

        // Test Case 2
        int[] data2 = {0, 0, 0, 1, 0};
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input:  " + Arrays.toString(data2));
        System.out.println("Output: " + solver.minSwaps(data2)); // Expected: 0
        System.out.println();

        // Test Case 3
        int[] data3 = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1};
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input:  " + Arrays.toString(data3));
        System.out.println("Output: " + solver.minSwaps(data3)); // Expected: 3
    }
}
