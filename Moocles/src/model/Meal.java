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

public interface Meal {
    void setMenuName(String menuName);
    void setCalories(double calories);
    String getMenuName();
    double getCalories();
    void addToConsumedCalories(Nutrition nutrition);
}