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
public class CardioWorkout extends Workout {
    public CardioWorkout(String name, double duration) {
        setNameCardio(name);
        setDurationCardio(duration);
    }

    @Override
    public void calculateCaloriesBurned() {
        double caloriesPerMinute = 8.0;
        setCaloriesBurned(caloriesPerMinute * getDurationCardio());
    }
}
