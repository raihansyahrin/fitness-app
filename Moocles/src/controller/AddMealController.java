package controller;

import Database.ConnectDBM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMealController {

    public static boolean addMeal(String meal, double calorie) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();

            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }
            
            // SQL query to insert meal information into the database
            String insertQuery = "INSERT INTO addmeal (by_who, foodname, foodcalorie) VALUES ('" + by_who + "', '" + meal + "', '" + calorie + "')";

            // Create a PreparedStatement
            preparedStatement = connection.prepareStatement(insertQuery);

            // Execute the insert query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding meal to database: " + e.getMessage());
            // Log the stack trace or handle the exception appropriately
            return false;
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
            
            }
        }
    }
}