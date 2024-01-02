/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Database.ConnectDBM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;

/**
 *
 * @author raihansyahrin
 */
public class ProfileController {
    
    public void showName(JLabel showName){
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
            ResultSet resultSet = statement.executeQuery("SELECT name FROM person WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
                showName.setText(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public void showGender(JLabel showGender){
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
            ResultSet resultSet = statement.executeQuery("SELECT gender FROM person WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
                showGender.setText(resultSet.getString("gender"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public void showAge(JLabel showAge){
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
            ResultSet resultSet = statement.executeQuery("SELECT age FROM person WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
                showAge.setText(resultSet.getString("age"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public void showWeight(JLabel showWeight){
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
            ResultSet resultSet = statement.executeQuery("SELECT weight FROM person WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
                showWeight.setText(resultSet.getString("weight"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public void showHeight(JLabel showHeight){
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
            ResultSet resultSet = statement.executeQuery("SELECT height FROM person WHERE by_who  = '" + by_who + "'");
            if (resultSet.next()) {
                showHeight.setText(resultSet.getString("height"));
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e) {
            System.err.println("Gagal Merekam Isi Details \n" + e.getMessage());
        }
    }
    
    public void refreshProfile(JLabel showName, JLabel showGender, JLabel showAge, JLabel showWeight, JLabel showHeight) {
        showName(showName);
        showGender(showGender);
        showAge(showAge);
        showWeight(showWeight);
        showHeight(showHeight);
    }  
}
