/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import config.Database;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import models.voucher;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QuanLyVoucher {

    Database db = new Database();
    ArrayList<voucher> DSVoucer;

    public QuanLyVoucher() {
        DSVoucer = new ArrayList<voucher>();

    }

    public ArrayList<voucher> getDSVoucher() {
        DSVoucer = new ArrayList<voucher>();
        ResultSet rs = db.queryHandle("SELECT * FROM voucher WHERE available = '1' ", "get");

        try {
            while (rs.next()) {
                int ID = rs.getInt("ID");
                System.out.println(ID);
                String maVoucer = rs.getNString("maVoucer");
                int giaTri = rs.getInt("giaTri");
                int soLuong = rs.getInt("soLuong");
                Timestamp ngayBatDau = rs.getTimestamp("ngayBatDau");
                Timestamp ngayKetThuc = rs.getTimestamp("ngayKetThuc");
                boolean available = rs.getBoolean("available");
                voucher vc = new voucher(ID, maVoucer, giaTri, soLuong, ngayBatDau, ngayKetThuc, available);
                DSVoucer.add(vc);

            }
        } catch (Exception e) {
            System.err.println("Getting data has error");
        }
        return DSVoucer;
    }

    public void addVoucher(String maVoucer, int giaTri, int soLuong, Timestamp ngayBatDau, Timestamp ngayKetThuc, boolean available) {
        String queryString = "INSERT INTO voucher(maVoucer,giaTri,soLuong,ngayBatDau,ngayKetThuc,available) VALUES ('" + maVoucer + "','" + giaTri + "','" + soLuong + "',' " + ngayBatDau + "',' " + ngayKetThuc + "', " + "'1'" + ")";
        Database.queryHandle(queryString, "insert");
    }

    public voucher getVoucherByMaVoucher(String maVoucer) {
        voucher vc = new voucher();
        String queryString = "SELECT * FROM voucher WHERE maVoucer =" + maVoucer + " and available = '1'";
        ResultSet rs = Database.queryHandle(queryString, "get");
        try {
            if (rs.next()) {
                vc.setID(rs.getInt("ID"));
                vc.setMaVoucer(rs.getNString("maVoucer"));
                vc.setGiaTri(rs.getInt("giaTri"));
                vc.setSoLuong(rs.getInt("soLuong"));
                vc.setNgayBatDau(rs.getTimestamp("ngayBatDau"));
                vc.setNgayKetThuc(rs.getTimestamp("ngayKetThuc"));
                return vc;

            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyVP.class.getName()).log(Level.SEVERE, null, ex);

        }
       
        return vc;

    }

    public voucher getVoucherByID(int ID) {
        voucher vc = new voucher();
        String queryString = "SELECT * FROM voucher WHERE ID =" + ID + " and available = '1'";
        ResultSet rs = Database.queryHandle(queryString, "get");
        try {
            if (rs.next()) {
                vc.setID(rs.getInt("ID"));
                vc.setMaVoucer(rs.getNString("maVoucer"));
                vc.setGiaTri(rs.getInt("giaTri"));
                vc.setSoLuong(rs.getInt("soLuong"));
                vc.setNgayBatDau(rs.getTimestamp("ngayBatDau"));
                vc.setNgayKetThuc(rs.getTimestamp("ngayKetThuc"));
                return vc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyVP.class.getName()).log(Level.SEVERE, null, ex);

        }
        return vc;

    }

    public void updateVoucher(int ID, String maVoucer, int giaTri, int soLuong, Timestamp ngayBatDau, Timestamp ngayKetThuc, boolean available) {
        String query = "UPDATE voucher SET maVoucer = \"" + maVoucer + "\", giaTri = " + giaTri + "\", soLuong = " + soLuong + "\", ngayBatDau = " + ngayBatDau + "\", ngayKetThuc = " + ngayKetThuc + " WHERE ID = " + ID + ";";
        Database.queryHandle(query, "update");
    }

    public void DeleteVoucher(int id) {
        String query = "UPDATE voucher SET available = '0' WHERE ID = " + id;
        Database.queryHandle(query, "update");
    }

    public boolean checkAccessVoucher(String maVoucer) {
        Date date = new Date();
        Timestamp dateNow = new Timestamp(date.getTime());
        voucher vc = new voucher();
        String queryString = "SELECT * FROM voucher WHERE maVoucer =" + maVoucer + " and available = '1' and ngayBatDau < " + dateNow + " and ngayKetThuc > " + dateNow + ";";
        ResultSet rs = Database.queryHandle(queryString, "get");
        try {
            if (rs.next()) {
                return true;

            }
        } catch (Exception e) {
        }
        return false;
    }
}
