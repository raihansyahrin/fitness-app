/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author LUGI FEBRIANSYAH
 */
public class ConnectDBM {
    private static Connection mysqlconfig;
    public static Connection  MoosclesDB() throws SQLException{
        try{
            String url ="jdbc:mysql://localhost:3306/mooscles?serverTimezone=UTC";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url,user,pass);
        }
        catch(SQLException e){
            System.err.println("Koneksi gagal \n"+e.getMessage());
        }
        return mysqlconfig;
    }
}

