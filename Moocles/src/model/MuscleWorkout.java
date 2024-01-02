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
public class MuscleWorkout extends Workout {
    public MuscleWorkout(String name, double repetitions) {
        setNameMuscle(name);
        
        setRepetitionMuscle(repetitions);
    }

    @Override
    public void calculateCaloriesBurned() {
        double caloriesPerRepetition = 5.0;
        setCaloriesBurned(caloriesPerRepetition * getRepetitionMuscle());
    }
}
