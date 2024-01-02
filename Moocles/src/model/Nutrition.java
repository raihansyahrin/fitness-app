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
public class Nutrition {
    private double requiredCalories;
    private double consumedCalories;

    public double getRequiredCalories() {
        return requiredCalories;
    }

    public void setRequiredCalories(double requiredCalories) {
        this.requiredCalories = requiredCalories;
    }

    public double getConsumedCalories() {
        return consumedCalories;
    }

    public void setConsumedCalories(double consumedCalories) {
        this.consumedCalories = consumedCalories;
    }


    private void calculateCalorieBalance(double requiredCalories, double consumedCalories) {
        Nutrition nutrition = new Nutrition();
        nutrition.setRequiredCalories(requiredCalories);
        nutrition.setConsumedCalories(consumedCalories);
        double calorieBalance = nutrition.calculateCalorieBalance();
        // Update tampilan atau lakukan apa pun yang Anda inginkan dengan nilai calorieBalance
    }
    public double calculateCalorieBalance() {
        return getRequiredCalories() - getConsumedCalories(); // Perubahan di sini
    }
}
