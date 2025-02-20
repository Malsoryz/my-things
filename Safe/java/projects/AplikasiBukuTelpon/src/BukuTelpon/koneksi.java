/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BukuTelpon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author malsoryz
 */
public class koneksi {
    private static Connection koneksiMysql;
    public static Connection koneksiDB() throws SQLException {
        if (koneksiMysql==null) {
            try {
                String url = "jdbc:mysql://localhost:3306/bukutelpon";
                String user = "eternity";
                String pass = "eternity351";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksiMysql = (Connection) DriverManager.getConnection(url,user,pass);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Koneksi!");
            }
        }
        return koneksiMysql;
    }
}
