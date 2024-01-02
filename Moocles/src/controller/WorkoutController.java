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
import javax.swing.JTextField;
import model.CardioWorkout;
import model.MuscleWorkout;


/**
 *
 * @author raihansyahrin
 */
public class WorkoutController {
    
    public void showBurned(JLabel label) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet, rs = null;

        try {
            connection = ConnectDBM.MoosclesDB();

            String queryByWho = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(queryByWho);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }

            String queryCount = "SELECT SUM(caloriesBurnedCardio) AS totalCardio, SUM(caloriesBurnedMuscle) AS totalMuscle FROM workout WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(queryCount);
            preparedStatement.setString(1, by_who);
            resultSet = preparedStatement.executeQuery();

            int totalCardio = 0;
            int totalMuscle = 0;
            
            if (resultSet.next()) {
                totalCardio += resultSet.getInt("totalCardio");
                totalMuscle += resultSet.getInt("totalMuscle");
            }
            label.setText(String.valueOf(totalCardio + totalMuscle));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void saveCardio(JTextField name, JTextField duration){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();
            
            // buat objek 
            CardioWorkout wo = new CardioWorkout(name.getText(), Double.parseDouble(duration.getText()));

            // set properti dari inputan objek person
          
            wo.calculateCaloriesBurned();
            
            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }
            
            // masukkan data cardio ke table workout 
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            statement.executeUpdate("INSERT INTO workout (by_who, nameCardio, durationCardio, caloriesBurnedCardio) VALUES ('" + by_who + "', '" + wo.getNameCardio() + "', '" + wo.getDurationCardio() + "','"+wo.getCaloriesBurned()+"')");
            
            statement.close();
            name.setText("");
            duration.setText("");
        } catch (SQLException e) {
            System.err.println("Cardio Tidak dapat DiSimpan \n" + e.getMessage());
        }
    }
    
    public void saveMuscle(JTextField name, JTextField repetition){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();
            
            // buat objek 
            MuscleWorkout wo = new MuscleWorkout(name.getText(), Double.parseDouble(repetition.getText()));

            // set properti dari inputan objek person
          
            wo.calculateCaloriesBurned();
            
            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }
            
            // masukkan data cardio ke table workout 
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            statement.executeUpdate("INSERT INTO workout (by_who, nameMuscle, repetitionMuscle, caloriesBurnedMuscle) VALUES ('" + by_who + "', '" + wo.getNameMuscle() + "', '" + wo.getRepetitionMuscle() + "','"+wo.getCaloriesBurned()+"')");
            
            statement.close();
            name.setText("");
            repetition.setText("");
        } catch (SQLException e) {
            System.err.println("Muslce Tidak dapat DiSimpan \n" + e.getMessage());
        }
    }
    
    public void saveTotal(JTextField label) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet, rs = null;

        try {
            connection = ConnectDBM.MoosclesDB();

            String queryByWho = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(queryByWho);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }

            String queryCount = "SELECT SUM(caloriesBurnedCardio) AS totalCardio, SUM(caloriesBurnedMuscle) AS totalMuscle FROM workout WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(queryCount);
            preparedStatement.setString(1, by_who);
            resultSet = preparedStatement.executeQuery();

            int totalCardio = 0;
            int totalMuscle = 0;
            
            if (resultSet.next()) {
                totalCardio += resultSet.getInt("totalCardio");
                totalMuscle += resultSet.getInt("totalMuscle");
            }
            
            label.setText(String.valueOf(totalCardio + totalMuscle));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
    
    public void countCardio(JTextField label) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet, rs = null;

        try {
            connection = ConnectDBM.MoosclesDB();

            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }

            String queryCount = "SELECT SUM(caloriesBurnedCardio) AS totalCardio FROM workout WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(queryCount);
            preparedStatement.setString(1, by_who);
            resultSet = preparedStatement.executeQuery();

            int total = 0;

            if (resultSet.next()) {
                total = resultSet.getInt("totalCardio");
            }

            label.setText(Integer.toString(total));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void countMuscle(JTextField label) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet, rs = null;

        try {
            connection = ConnectDBM.MoosclesDB();

            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }

            String queryCount = "SELECT SUM(caloriesBurnedMuscle) AS totalMuscle FROM workout WHERE by_who = ?";
            preparedStatement = connection.prepareStatement(queryCount);
            preparedStatement.setString(1, by_who);
            resultSet = preparedStatement.executeQuery();

            int total = 0;

            if (resultSet.next()) {
                total = resultSet.getInt("totalMuscle");
            }

            label.setText(Integer.toString(total));

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void countCal(JTextField label){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet, rs = null;
        // Query untuk mengambil semua nilai dari satu kolom
        try{
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
            
            String query1 = "SELECT caloriesBurned FROM workout WHERE by_who = '" + by_who + "'";
            connection = ConnectDBM.MoosclesDB();

//            Statement preparedStatement = ConnectDBM.MoosclesDB().createStatement();
            preparedStatement = connection.prepareStatement(query1);
            resultSet = preparedStatement.executeQuery();

            int total = 0;

            // Menjumlahkan nilai-nilai dari kolom yang diambil dari hasil query
            while (resultSet.next()) {
                int nilaiKolom = resultSet.getInt("caloriesBurned");
                total += nilaiKolom;
            }
            

            label.setText(Integer.toString(total));

        }catch(SQLException e){
            e.getMessage();
        }
    }

    public void refreshCount(JTextField calorie, JTextField muscle){
        countCardio(calorie);
        countMuscle(muscle);
    }
}
