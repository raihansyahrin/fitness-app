/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author raihansyahrin
 */public class Dinner implements Meal {
    // Similar implementation as Breakfast, adapt as needed
    private String menuName;
    private double calories;

    public Dinner(String menuName, double calories) {
        this.menuName = menuName;
        this.calories = calories;
    }

    @Override
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String getMenuName() {
        return menuName;
    }

    @Override
    public double getCalories() {
        return calories;
    }

    @Override
    public void addToConsumedCalories(Nutrition nutrition) {
        double consumedCalories = nutrition.getConsumedCalories();
        nutrition.setConsumedCalories(consumedCalories + calories);
    }
}