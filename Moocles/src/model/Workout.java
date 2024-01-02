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
public abstract class Workout {
    private String nameCardio;
    private String nameMuscle;
    private double durationCardio;
    private double repetitionMuscle;
    private double caloriesBurned;
    private double caloriesBurnedRepetisi;
    private double caloriesBurnedCardio;

    public double getCaloriesBurnedRepetisi() {
        return caloriesBurnedRepetisi;
    }

    public void setCaloriesBurnedRepetisi(double caloriesBurnedRepetisi) {
        this.caloriesBurnedRepetisi = caloriesBurnedRepetisi;
    }

    public double getCaloriesBurnedCardio() {
        return caloriesBurnedCardio;
    }

    public void setCaloriesBurnedCardio(double caloriesBurnedCardio) {
        this.caloriesBurnedCardio = caloriesBurnedCardio;
    }

    public String getNameCardio() {
        return nameCardio;
    }

    public void setNameCardio(String nameCardio) {
        this.nameCardio = nameCardio;
    }

    public String getNameMuscle() {
        return nameMuscle;
    }

    public void setNameMuscle(String nameMuscle) {
        this.nameMuscle = nameMuscle;
    }

    public double getDurationCardio() {
        return durationCardio;
    }

    public void setDurationCardio(double durationCardio) {
        this.durationCardio = durationCardio;
    }

    public double getRepetitionMuscle() {
        return repetitionMuscle;
    }

    public void setRepetitionMuscle(double repetitionMuscle) {
        this.repetitionMuscle = repetitionMuscle;
    }

    

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }
    
    // Menjadikan metode ini abstrak
    public abstract void calculateCaloriesBurned();
}


