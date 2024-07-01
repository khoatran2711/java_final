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
import java.text.SimpleDateFormat;
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

    public void addVoucher(String maVoucer, int giaTri, int soLuong, Date ngayBatDau, Date ngayKetThuc) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        
        String queryString = "INSERT INTO voucher(maVoucer,giaTri,soLuong,ngayBatDau,ngayKetThuc,available) VALUES ('" + maVoucer + "','" + giaTri + "','" + soLuong + "',' " + dateFormat.format(ngayBatDau) + "',' " + dateFormat.format(ngayKetThuc) + "', " + "'1'" + ")";
        System.out.println(queryString);
        Database.queryHandle(queryString, "insert");
    }

    public voucher getVoucherByMaVoucher(String maVoucer) {
        voucher vc = new voucher();
        String queryString = "SELECT * FROM voucher WHERE maVoucer = \"" + maVoucer + "\" and available = '1'";
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

    public void updateVoucher(int ID, String maVoucer, int giaTri, int soLuong, Date ngayKetThuc) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String query = "UPDATE voucher SET maVoucer = \"" + maVoucer + "\", giaTri = " + giaTri + ", soLuong = " + soLuong + ", ngayKetThuc = \"" + dateFormat.format(ngayKetThuc) + "\" WHERE ID = " + ID + ";";
        System.out.println(query);
        Database.queryHandle(query, "update");
    }
    
    public void DeleteVoucher(int id) {
        String query = "UPDATE voucher SET available = '0' WHERE ID = " + id;
        Database.queryHandle(query, "update");
    }
    public void usedVoucher(String maVoucher){
     String query = "UPDATE voucher SET  soLuong = soLuong - 1 "  + " WHERE maVoucer = \"" + maVoucher + "\";";
     System.out.println(query);
     Database.queryHandle(query, "update");

    }
    public boolean checkAccessVoucher(String maVoucer) {
//        saleHeCucChay
        Date date = new Date();

        Timestamp dateNow = new Timestamp(date.getTime());
        System.err.println(dateNow);

        String queryString = "SELECT * FROM voucher WHERE maVoucer =\"" + maVoucer + "\" and available = \"1\" and ngayBatDau < \"" + dateNow + "\" and ngayKetThuc > \"" + dateNow + "\";";
        System.out.println(queryString);
        ResultSet rs = Database.queryHandle(queryString, "get");
        System.err.println(rs);
        try {
            if (rs.next()) {
                usedVoucher(maVoucer);
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
