/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package btl_java_coffee;

import config.Database;
import controllers.QuanLyBan;
import controllers.QuanLyNhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.NhanVien;

import models.VatPham;
import models.orderItem;
import views.LoginFrame;
import views.NhanVienTable;

/**
 *
 * @author Admin
 */
public class BTL_JAVA_COFFEE {

    /**
     * @param args the command line arguments
     */
    private static String DB_URL = "jdbc:mysql://localhost:3306/coffe_db";
    private static String USER_NAME = "root";
    //private static String PASSWORD = "Khoa@2711";
    private static String PASSWORD = "";

    /**
     * main
     *
     * @author viettuts.vn
     * @param args
     */
    public static void main(String args[]) throws SQLException {
        LoginFrame qlb = new LoginFrame();
        qlb.setVisible(true);

    }

    /**
     * create connection
     *
     * @author viettuts.vn
     * @param dbURL: database's url
     * @param userName: username is used to login
     * @param password: password is used to login
     * @return connection
     */
    public static Connection getConnection(String dbURL, String userName,
            String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
}
