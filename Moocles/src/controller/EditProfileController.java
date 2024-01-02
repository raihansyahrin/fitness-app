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
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Person;

/**
 *
 * @author raihansyahrin
 */
public class EditProfileController {
    public void SaveInputEdit(JTextField nameInput, ButtonGroup genderButtonGroup, JTextField ageInput,
            JTextField weightInput, JTextField heightInput, JLabel showName, JLabel showGender, JLabel showAge,
            JLabel showWeight, JLabel showHeight) {
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
            
            // Create an instance of Person
            Person person = new Person();

            // Set properties from input fields to the Person object
            person.setName(nameInput.getText());
            person.setAge(Integer.parseInt(ageInput.getText()));
            person.setWeight(Integer.parseInt(weightInput.getText()));
            person.setHeight(Integer.parseInt(heightInput.getText()));

            // Get the selected radio button
            for (Enumeration<AbstractButton> buttons = genderButtonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    person.setGender(button.getText());
                    break;
                }
            }

            // Update person data in the database
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            statement.executeUpdate("UPDATE person SET name = '" + person.getName() + "', gender = '"
                    + person.getGender() + "', age = '" + person.getAge() + "', weight = '" + person.getWeight()
                    + "', height = '" + person.getHeight() + "' WHERE by_who = '" + by_who + "'");

            // Refresh the Profile view after confirming edits
            ProfileController pc = new ProfileController();
            pc.refreshProfile(showName, showGender, showAge, showWeight, showHeight);

            statement.close();

            // Show success message
            JOptionPane.showMessageDialog(null, "Person Berhasil Di Update");

            // Clear input fields and radio buttons
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
