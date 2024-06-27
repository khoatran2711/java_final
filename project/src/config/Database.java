/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Database {

    public Database() {
//        enviroment ev = new enviroment();
//        con = getConnection(ev.dbUrl, ev.userName, ev.password);
    }

    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
//            ex.printStackTrace();
        }
        return conn;
    }

    public static ResultSet queryHandle(String queryCode, String method) {
        ResultSet rs = null;
        Connection con;
        if (method == "get") {
            try {
                enviroment ev = new enviroment();
                con = getConnection(ev.dbUrl, ev.userName, ev.password);
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(queryCode);
            } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }

            return rs;
        } else if (method == "insert" || method == "delete") {
            try {
                enviroment ev = new enviroment();
                con = getConnection(ev.dbUrl, ev.userName, ev.password);
                PreparedStatement stmt = con.prepareStatement(queryCode, Statement.RETURN_GENERATED_KEYS);
                stmt.executeUpdate();
                System.out.println("Insert data success");
                if (method == "insert") {
                    rs = stmt.getGeneratedKeys();
                }
                return rs;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (method == "update") {
            try {
                enviroment ev = new enviroment();
                con = getConnection(ev.dbUrl, ev.userName, ev.password);
                PreparedStatement preparedStatement = con.prepareStatement(queryCode);
                int rowsUpdated = preparedStatement.executeUpdate();
                return rs;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return rs;
    }

    public static Statement createStmt() throws SQLException {
        Connection con;
        try {
            enviroment ev = new enviroment();
            con = getConnection(ev.dbUrl, ev.userName, ev.password);
            Statement stmt = con.createStatement();
            return stmt;
        } catch (SQLException ex) {
            return null;
        }

    }
}
