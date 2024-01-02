/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Prawira
 */
public class Report {
    public static String generateReport(Nutrition nutrition, Workout exercise, Goal goal) {
        double consumedCalories = nutrition.getConsumedCalories();
        double burnedCalories = exercise.getCaloriesBurned();
        double requiredCalories = nutrition.getRequiredCalories();

        double calorieBalance = consumedCalories - burnedCalories;
        String comment;

        if (calorieBalance > requiredCalories) {
            comment = "You've exceeded your calorie goal. Consider adjusting your diet or exercise.";
        } else if (calorieBalance < requiredCalories) {
            comment = "You're below your calorie goal. Make sure to consume enough calories for your goal.";
        } else {
            comment = "You're right on track. Keep it up!";
        }

        return "Calorie Balance: " + calorieBalance + " calories\n" + comment;
    }
}
