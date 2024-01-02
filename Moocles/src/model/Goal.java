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
public class Goal{
    private Person person; // Goal menerima referensi ke objek Person
    private Nutrition nutrition;
    
    public Goal(Person person) {
        this.person = person;
        setRequiredCalories();
    }
    

    public Nutrition getNutrition() {
        return nutrition;
    }

    public String determineGoal() {
        String bmi = null;//person.getBmi();
        if (bmi.equals("Underweight")) {
            return "Surplus Calorie";
        } else if (bmi.equals("Obese")) {
            return "Deficit Calorie";
        } else {
            return "Normal Calorie";
        }
    }
    
    private void setRequiredCalories() {
        nutrition = new Nutrition();

        if (determineGoal().equals("Surplus Calorie")) {
            nutrition.setRequiredCalories(3000);
        } else if (determineGoal().equals("Deficit Calorie")) {
            nutrition.setRequiredCalories(1000);
        } else {
            nutrition.setRequiredCalories(2000);
        }
    }
}
