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
import models.VatPham;
import models.orderItem;

/**
 *
 * @author ACER
 */
public class QuanLyVP {

    ArrayList<VatPham> vatPhamArr;

    public QuanLyVP() {
    }

    public ArrayList<VatPham> GetMenu() {
        vatPhamArr = new ArrayList<VatPham>();
        String query = "select * FROM vatpham WHERE available = 1;";
        ResultSet rs = Database.queryHandle(query, "get");

        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("tenVatPham");
                int donGia = rs.getInt("donGia");

                VatPham vp = new VatPham(id, name, donGia);
                vatPhamArr.add(vp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyVP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vatPhamArr;
    }

    public void Insert(String name, int price) {
        String query = "INSERT INTO vatpham (tenVatPham, donGia, available) VALUES('" + name + "', " + price  +", 1);";
        Database.queryHandle(query, "insert");
    }

    public VatPham GetVatPhamByName(String name) {
        String query = "SELECT * FROM vatpham WHERE tenVatPham = \"" + name + "\" AND available = '1';";
        ResultSet rs = Database.queryHandle(query, "get");
        VatPham vp = new VatPham();
        vp.setID(-1);
        try {
            if (rs.next()) {
                vp.setID(rs.getInt("ID"));
                vp.setTenVatPham(rs.getString("tenVatPham"));
                vp.setGiaThanh(rs.getInt("donGia"));
                return vp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyVP.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vp;
    }

    public void UpdateVatPham(int id, String name, int price) {
        String query = "UPDATE vatpham SET tenVatPham = \"" + name + "\", donGia = " + price + " WHERE id = " + id + ";";
        Database.queryHandle(query, "update");
    }

    public void Delete(int id) {
        String query = "UPDATE vatpham SET available = '0' WHERE ID = " + id;
        Database.queryHandle(query, "update");
    }
}
