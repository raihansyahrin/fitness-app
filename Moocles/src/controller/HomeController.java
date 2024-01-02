/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author LUGI FEBRIANSYAH
 */
public class HomeController {
    
    public void ShowBMI(JLabel labelBMI, JLabel labelSuggest) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();
            
            // Membuat objek StringBuilder untuk menampung data
            StringBuilder builder = new StringBuilder();
            
            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }

            // Mengambil data berat dan tinggi dari tabel person
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT weight, height FROM person WHERE by_who = " + by_who);
            String query1 = "SELECT weight, height FROM person WHERE by_who = '" + by_who + "'";
            preparedStatement = connection.prepareStatement(query1);
//            preparedStatement.setString(1, by_who);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Melakukan perulangan untuk setiap baris data
            while (resultSet.next()) {
                // Mengambil nilai berat dan tinggi dari setiap baris
                int weight = resultSet.getInt("weight");
                int height = resultSet.getInt("height");

                // Menghitung BMI
                double bmi = calculateBMI(weight, height);
                
                 String bmiCategory = determineBMICategory(bmi);

                // Menambahkan nilai BMI ke dalam StringBuilder
                builder.append(String.format("%.1f\n", bmi));
//                labelBMI.setText(String.format("%.2f\n", bmi));
                
                labelSuggest.setText(bmiCategory);
        
            }

            // Menampilkan nilai BMI di label
            labelBMI.setText(builder.toString());
            

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Failed memperbarui bmi \n" + e.getMessage());
        }
    }

    // Fungsi untuk menghitung BMI
    private double calculateBMI(double weight, double height) {
        // Menghitung BMI menggunakan rumus BMI = weight / (height * height)
        // Nilai height dalam satuan centi meter
        double heightInCentiMeter = height / 100.0;
        return weight / (heightInCentiMeter * heightInCentiMeter);
    }
    
    private String determineBMICategory(double bmi) {
        if (bmi <= 18.5) {
            return "You're Underweight, let's increase your muscle mass with muscle training!";
        } else if (bmi <= 24.9) {
            return "You're Normal Weight, Maintain your current muscle mass and stay healthy by exercising";
        } else {
            return "You're Obese, Let's decrease your body fat mass with cardio training";
        }
    }
}
