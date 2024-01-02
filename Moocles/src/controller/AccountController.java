package controller;

import Database.ConnectDBM;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Account;



public class AccountController {
    public void SaveInput(JTextField username, JTextField email, JTextField password) {
        try {
            // buat objek 
            Account account = new Account();

            // set properti dari inputan objek person
            account.setUsername(username.getText());
            account.setEmail(email.getText());
            account.setPassword(password.getText());


            // masukkan data account ke database
            Statement statement = ConnectDBM.MoosclesDB().createStatement();
            statement.executeUpdate("INSERT INTO account (username, email, password) VALUES ('" + account.getUsername() + "', '" + account.getEmail() + "', '" + account.getPassword() + "')");
            Statement statement1 = ConnectDBM.MoosclesDB().createStatement();
            statement1.executeUpdate("INSERT INTO logs (username) VALUES ('" + account.getUsername() + "')");
           
            statement.close();

            // pesan berhasil
            JOptionPane.showMessageDialog(null, "Account Berhasil DiSimpan");

            //CLEAR SELECTION       
            username.setText("");
            email.setText("");
            password.setText("");
        } catch (SQLException e) {
            System.err.println("account Tidak dapat DiSimpan \n" + e.getMessage());
        }
    }   
}