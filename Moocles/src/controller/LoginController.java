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
import javax.swing.JOptionPane;

/**
 *
 * @author LUGI FEBRIANSYAH
 */

public class LoginController{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    
    public boolean login(String username, String password){
        try{
            connection = ConnectDBM.MoosclesDB();
            
            String query = "SELECT * FROM account WHERE username = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            rs = preparedStatement.executeQuery();

            if (rs.next()){
                if (password.equals(rs.getString("password"))){
                    JOptionPane.showMessageDialog(null, "Login berhasil");
                    
                    Statement statement1 = ConnectDBM.MoosclesDB().createStatement();
                    statement1.executeUpdate("INSERT INTO logs (username) VALUES ('" + username + "')");
                    return true; // Return true if login is successful
                } else {
                    JOptionPane.showMessageDialog(null, "Password yang dimasukkan salah");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username yang dimasukkan tidak terdaftar");
            }
        } catch (SQLException e) {
            System.err.println("Akun tidak bisa login \n" + e.getMessage());           
        }finally {
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}