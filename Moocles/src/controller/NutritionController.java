package controller;

/**
 *
 * @author raihansyahrin
 */

import Database.ConnectDBM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NutritionController {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    
    public void getConsumed(JLabel label){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT * FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }
            
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT consumedCalorie FROM nutrition WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
               
                label.setText(resultSet.getString("ConsumedCalorie"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public void showMessage(JTextField getLabel, JLabel setLabel) {
        try {
            // Getting the value from JTextField as a double
            double conditionValue = Double.parseDouble(getLabel.getText());

            // Checking the conditions
            if (conditionValue >= -100 && conditionValue <= 100) {
                setLabel.setText("Congratulations! You've successfully conquered the day!");
            } else if (conditionValue > 100) {
                setLabel.setText("Great job! Time to get moving, let's exercise!");
            } else if (conditionValue < -100) {
                setLabel.setText("Hmm, looks like you need to grab a bite. Don't let hunger strike!");
            }
        } catch (NumberFormatException e) {
            // Handling errors if the value cannot be converted to a double
            setLabel.setText("Oops, please enter a valid number!");
        }
    }
    
    public void showRequiredConsumed(JTextField labelRequired, JTextField labelConsumed){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT * FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }
            
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT RequeredCalorie, ConsumedCalorie FROM nutrition WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
                labelRequired.setText(resultSet.getString("RequeredCalorie"));
                labelConsumed.setText(resultSet.getString("ConsumedCalorie"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public double calculateBMI(double weight, double height) {
        double heightInMeters = height / 100.0;
        return weight / (heightInMeters * heightInMeters);

    }

    public double getRcalorie(String gender, int age, double bmiValue){
        
        
        if (gender.equals("Man")) {
            if (age <= 12) {
                if (bmiValue <= 18.5) { // Adjust for boys (age <= 12)
//            bmi = "Underweight"; 
                    return 2700;
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
//            bmi = "Normal Weight";  
                    return 2400;
                } else {
//            bmi = "Obese";
                    return 2000;
                } 
            } else if (age <= 18) { // Adjust for teenagers (age > 12 and age <= 18)
                if (bmiValue <= 18.5) { // Adjust for boys (age <= 12)
//            bmi = "Underweight"; 
                    return 2800;
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
//            bmi = "Normal Weight";  
                    return 2500;
                } else {
//            bmi = "Obese";
                    return 2100;
                } 
            } else if (age <= 40) {
                if (bmiValue <= 18.5) { // Adjust for boys (age <= 12)
//            bmi = "Underweight"; 
                    return 2600;
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
//            bmi = "Normal Weight";  
                    return 2300;
                } else {
//            bmi = "Obese";
                    return 1900;
                } 
            }
        } else if (gender.equals("Woman")) {
            if (age <= 12) { // Adjust for girls (age <= 12)
                if (bmiValue <= 18.5) { // Adjust for boys (age <= 12)
//            bmi = "Underweight"; 
                    return 2400;
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
//            bmi = "Normal Weight";  
                    return 2100;
                } else {
//            bmi = "Obese";
                    return 1700;
                }
            } else if (age <= 18) { // Adjust for teenagers (age > 12 and age <= 18)
                if (bmiValue <= 18.5) { // Adjust for boys (age <= 12)
//            bmi = "Underweight"; 
                    return 2500;
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
//            bmi = "Normal Weight";  
                    return 2200;
                } else {
//            bmi = "Obese";
                    return 1800;
                } 
            } else if (age <= 40) {
                if (bmiValue <= 18.5) { // Adjust for boys (age <= 12)
//            bmi = "Underweight"; 
                    return 2300;
                } else if (bmiValue > 18.5 && bmiValue <= 24.9) {
//            bmi = "Normal Weight";  
                    return 2000;
                } else {
//            bmi = "Obese";
                    return 1600;
                } 
            }
        }
        return 0;
    }
    
    public void saveRCCalories() {
        // Database connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null; // Fix variable name

        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery(); // Fix variable name
            String user = null;
            if (rs.next()) {
                user = rs.getString("username");
            }

            // Fetch height and weight from person table based on by_who
            String personQuery = "SELECT gender, age, height, weight FROM person WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(personQuery);
            preparedStatement.setString(1, user);
            ResultSet rsPerson = preparedStatement.executeQuery(); // Fix variable name

            String gender = "";
            int age = 0;
            double height = 0;
            double weight = 0;

            if (rsPerson.next()) {
                gender = rsPerson.getString("gender");
                age = rsPerson.getInt("age");
                height = rsPerson.getDouble("height");
                weight = rsPerson.getDouble("weight");
            }

            // Calculate BMI using fetched height and weight
            double bmiValue = calculateBMI(weight, height);

            // Get required calories based on BMI
            double requiredCalories = getRcalorie(gender, age, bmiValue);

            // SQL query to insert required calories into the database
            String insertQuery = "INSERT INTO nutrition (by_who, RequeredCalorie, ConsumedCalorie) VALUES (?, ?, '0')";

            // Create a PreparedStatement
            preparedStatement = connection.prepareStatement(insertQuery);

            // Set values for the parameters
            preparedStatement.setString(1, user);
            preparedStatement.setDouble(2, requiredCalories);

            // Execute the insert query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving required calories to the database: " + e.getMessage());
            // Log the stack trace or handle the exception appropriately
        } finally {
            // Close the ResultSets, PreparedStatement, and Connection in the finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                // Log the stack trace or handle the exception appropriately
            }
        }
    }
    
    public void deleteRCCalories() {
        // Database connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String user = null;
            if (rs.next()) {
                user = rs.getString("username");
            }

            // SQL query to delete a row based on by_who
            String deleteQuery = "DELETE FROM nutrition WHERE by_who = ?";

            // Create a PreparedStatement
            preparedStatement = connection.prepareStatement(deleteQuery);

            // Set values for the parameters
            preparedStatement.setString(1, user);

            // Execute the delete query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting row from the database: " + e.getMessage());
            // Log the stack trace or handle the exception appropriately
        } finally {
            // Close the PreparedStatement and Connection in the finally block
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                // Log the stack trace or handle the exception appropriately
            }
        }
    }
    
    public void updateConsumedCalorie() {
        // Database connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rsAddMeal = null;
        ResultSet rsNutrition = null;

        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String user = null;
            if (rs.next()) {
                user = rs.getString("username");
            }

            // Fetch the latest consumed calories from addmeal table based on by_who and timestamp
            String addMealQuery = "SELECT foodCalorie FROM addmeal WHERE by_who = ? ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(addMealQuery);
            preparedStatement.setString(1, user);
            rsAddMeal = preparedStatement.executeQuery();

            double latestFoodCalories = 0;

            if (rsAddMeal.next()) {
                latestFoodCalories = rsAddMeal.getDouble("foodCalorie");
            }

            // Fetch the required calories from nutrition table based on by_who
            String nutritionQuery = "SELECT RequeredCalorie, ConsumedCalorie FROM nutrition WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(nutritionQuery);
            preparedStatement.setString(1, user);
            rsNutrition = preparedStatement.executeQuery();

            double requiredCalories = 0;
            double consumedCalories = 0;

            if (rsNutrition.next()) {
                requiredCalories = rsNutrition.getDouble("RequeredCalorie");
                consumedCalories = rsNutrition.getDouble("ConsumedCalorie");
            }

            // Update ConsumedCalorie and subtract latest foodCalories from RequiredCalorie
            String updateQuery = "UPDATE nutrition SET ConsumedCalorie = ConsumedCalorie + ?, RequeredCalorie = RequeredCalorie - ? WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, latestFoodCalories);
            preparedStatement.setDouble(2, latestFoodCalories);
            preparedStatement.setString(3, user);
            

            // Execute the update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating consumed calorie: " + e.getMessage());
            // Log the stack trace or handle the exception appropriately
        } finally {
            // Close the ResultSets, PreparedStatement, and Connection in the finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rsAddMeal != null) {
                    rsAddMeal.close();
                }
                if (rsNutrition != null) {
                    rsNutrition.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                // Log the stack trace or handle the exception appropriately
            }
        }
    }
    
    
    public void showConsumedCal(JTextField label) {
        // Database connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rsAddMeal = null;
        ResultSet rsNutrition = null;

        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String user = null;
            if (rs.next()) {
                user = rs.getString("username");
            }

            // Fetch the latest consumed calories from addmeal table based on by_who and timestamp
            String addMealQuery = "SELECT foodCalorie FROM addmeal WHERE by_who = ? ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(addMealQuery);
            preparedStatement.setString(1, user);
            rsAddMeal = preparedStatement.executeQuery();

            double latestFoodCalories = 0;

            if (rsAddMeal.next()) {
                latestFoodCalories = rsAddMeal.getDouble("foodCalorie");
            }

            // Fetch the required calories from nutrition table based on by_who
            String nutritionQuery = "SELECT RequeredCalorie, ConsumedCalorie FROM nutrition WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(nutritionQuery);
            preparedStatement.setString(1, user);
            rsNutrition = preparedStatement.executeQuery();

            double requiredCalories = 0;
            double consumedCalories = 0;

            if (rsNutrition.next()) {
                requiredCalories = rsNutrition.getDouble("RequeredCalorie");
                consumedCalories = rsNutrition.getDouble("ConsumedCalorie");
            }

            // Update ConsumedCalorie and subtract latest foodCalories from RequiredCalorie
            String updateQuery = "UPDATE nutrition SET ConsumedCalorie = ConsumedCalorie + ?, RequeredCalorie = RequeredCalorie - ? WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, latestFoodCalories);
            preparedStatement.setDouble(2, latestFoodCalories);
            preparedStatement.setString(3, user);
            

            

            // Execute the update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating consumed calorie: " + e.getMessage());
            // Log the stack trace or handle the exception appropriately
        } finally {
            // Close the ResultSets, PreparedStatement, and Connection in the finally block
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rsAddMeal != null) {
                    rsAddMeal.close();
                }
                if (rsNutrition != null) {
                    rsNutrition.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
                // Log the stack trace or handle the exception appropriately
            }
        }
    }
}
