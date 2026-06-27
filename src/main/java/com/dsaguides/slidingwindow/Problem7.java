package com.dsaguides.slidingwindow;

import java.util.Arrays;

public class Problem7 {

    /*1176. Diet Plan Performance */

    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        if(calories.length < k){
            return 0;
        }

        int totalCalories = 0;
        int totalPoints = 0;
        for(int i = 0; i < k; i++){
            totalCalories += calories[i];
        }

        totalPoints = getTotalPoints(lower, upper, totalCalories, totalPoints);

        int left = 0;
        for(int right = k; right < calories.length; right++){
            totalCalories += calories[right] - calories[left];

            left++;

            totalPoints = getTotalPoints(lower, upper, totalCalories, totalPoints);
        }
        return totalPoints;
    }

    private static int getTotalPoints(int lower, int upper, int totalCalories, int totalPoints) {
        if(totalCalories < lower){
            totalPoints--;
        } else if(totalCalories > upper){
            totalPoints++;
        }
        return totalPoints;
    }

    public static void main(String[] args) {
        Problem7 solver = new Problem7();

        int[] calories1 = {1, 2, 3, 4, 5};
        int k1 = 1, lower1 = 3, upper1 = 3;
        System.out.println("=== Test Case 1 ===");
        System.out.println("Input: calories = " + Arrays.toString(calories1) + ", k = " + k1 + ", lower = " + lower1 + ", upper = " + upper1);
        System.out.println("Output: " + solver.dietPlanPerformance(calories1, k1, lower1, upper1)); // Expected: 0
        System.out.println();

        // Test Case 2
        int[] calories2 = {3, 2};
        int k2 = 2, lower2 = 0, upper2 = 1;
        System.out.println("=== Test Case 2 ===");
        System.out.println("Input: calories = " + Arrays.toString(calories2) + ", k = " + k2 + ", lower = " + lower2 + ", upper = " + upper2);
        System.out.println("Output: " + solver.dietPlanPerformance(calories2, k2, lower2, upper2)); // Expected: 1
        System.out.println();

        // Test Case 3
        int[] calories3 = {6, 5, 0, 0};
        int k3 = 2, lower3 = 1, upper3 = 5;
        System.out.println("=== Test Case 3 ===");
        System.out.println("Input: calories = " + Arrays.toString(calories3) + ", k = " + k3 + ", lower = " + lower3 + ", upper = " + upper3);
        System.out.println("Output: " + solver.dietPlanPerformance(calories3, k3, lower3, upper3)); // Expected: 0
        System.out.println();
    }
}
