/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import config.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class QuanLyTaiKhoan {
    public static QuanLyTaiKhoan Ins;
    
    public QuanLyTaiKhoan(int id){
        Ins = this;
        this.id = id;
    }
    
    private int id;

    public int getId() {
        return id;
    }
    
    public String GetNameRole(int id){
        String query = "select * FROM quyen WHERE id = " + id;
        ResultSet rs = Database.queryHandle(query, "get");
        try {
            if(rs.next()){
                return rs.getString("tenQuyen");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public void DeleteTaiKhoan(int id ){
        String query = "DELETE FROM thongtinnguoidung WHERE ID = " + id;
        Database.queryHandle(query, "delete");
    }
}
