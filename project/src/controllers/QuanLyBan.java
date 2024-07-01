    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import config.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Ban;

/**
 *
 * @author Admin
 */
public class QuanLyBan {

    Database db = new Database();
    ArrayList<Ban> DSBan;

    public QuanLyBan() {
        DSBan = new ArrayList<Ban>();
    }

    public ArrayList<Ban> getDSBan(){
        DSBan = new ArrayList<Ban>();
        ResultSet rs = db.queryHandle("SELECT * FROM ban ", "get");
        System.out.println(rs);
        try {
            while (rs.next()) {
                int soBan = rs.getInt("soBan");
                String trangThai = rs.getString("trangThai");           
                Ban b = new Ban(soBan, trangThai);
                DSBan.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return DSBan;
    }

    public void updateBan(int soBan) {
        ResultSet rs = db.queryHandle("UPDATE ban SET trangThai = 0 WHERE ban.soBan = " + soBan, "update");
    }
}   
