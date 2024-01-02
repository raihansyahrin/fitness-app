/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author raihansyahrin
 */

import Database.ConnectDBM;
import java.sql.SQLException;
import model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Account;

public class PersonController {
    public void showUsername(JTextField showUsername){
        try {
            // Ambil data akun dari proses login atau sesuai kebutuhan aplikasi
            Account account = new Account(); // sesuaikan dengan implementasi login Anda

            // Buat kueri SQL untuk mendapatkan username dari tabel account
            String query = "SELECT username FROM account WHERE username = ?";

            try (PreparedStatement preparedStatement = ConnectDBM.MoosclesDB().prepareStatement(query)) {
                preparedStatement.setString(1, account.getUsername());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Ambil dan tampilkan username
                        String username = resultSet.getString("username");
                        showUsername.setText(username);
                    } else {
                        // Tidak ada data ditemukan
                        showUsername.setText("No data found");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tampilkan kesalahan pada konsol
        }

    }
    
    public void SaveInput(JTextField nameInput, ButtonGroup genderButtonGroup,  JTextField ageInput,  JTextField weightInput,  JTextField heightInput) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        
        try {
            // Get the database connection
            connection = ConnectDBM.MoosclesDB();
            
            // buat objek 
            Person person = new Person();

            // set properti dari inputan objek person
            person.setName(nameInput.getText());
            person.setAge(Integer.parseInt(ageInput.getText()));
            person.setWeight(Integer.parseInt(weightInput.getText()));
            person.setHeight(Integer.parseInt(heightInput.getText()));
            


            // ambil radio button yang di select
            for (Enumeration<AbstractButton> buttons = genderButtonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    person.setGender(button.getText());
                    break;
                }
            }
            
            // by_who
            String query = "SELECT username FROM logs ORDER BY timestamp DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            String by_who = null;
            if (rs.next()) {
                by_who = rs.getString("username");
            }

            // masukkan data person ke database
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            statement.executeUpdate("INSERT INTO person (by_who, name, gender, age, weight, height) VALUES ('"  + by_who + "', '" + person.getName() + "', '" + person.getGender() + "', '" + person.getAge() + "', '" + person.getWeight() + "', '" + person.getHeight() +"')");
            
            statement.close();

            // pesan berhasil
            JOptionPane.showMessageDialog(null, "Person Berhasil DiSimpan");

            //clear inputan
            nameInput.setText("");
            ageInput.setText("");
            weightInput.setText("");
            heightInput.setText("");
            genderButtonGroup.clearSelection();
        } catch (SQLException e) {
            System.err.println("person Tidak dapat DiSimpan \n" + e.getMessage());
        }
    }
}
