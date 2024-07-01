/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import config.Database;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Bill;


import models.VatPham;
import models.orderItem;

/**
 *
 * @author Admin
 */
public class QuanLyHoaDon {

    Database db = new Database();
    private ArrayList<orderItem> orderArr;
    private int idhoaDon;
    private int indexTable;
    private int soBan;

    public Database getDb() {
        return db;
    }

    public ArrayList<orderItem> getOrderArr() {
        return orderArr;
    }

    public int getIdhoaDon() {
        return idhoaDon;
    }

    public int getSoBan() {
        return soBan;
    }

    public QuanLyHoaDon() {
        indexTable = -1;
    }

    private ArrayList<orderItem> ReadData(ResultSet rs) {
        orderArr = new ArrayList<orderItem>();
        idhoaDon = -1;
        try {
            while (rs.next()) {
                idhoaDon = rs.getInt("ID");
                soBan = rs.getInt("soBan");
                Timestamp checkIN = rs.getTimestamp("checkIn");
                Timestamp checkOut = rs.getTimestamp("checkOut");
                String tenVatPham = rs.getString("tenVatPham");
                int soLuong = rs.getInt("soLuong");
                int donGia = rs.getInt("donGia");
                Time thoiGianGoiMon = rs.getTime("thoiGianGoiMon");

                VatPham vp = new VatPham(0, tenVatPham, donGia);
                orderArr.add(new orderItem(vp, soLuong, thoiGianGoiMon));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return orderArr;
    }

    public ArrayList<orderItem> GetHoaDonWithIndex(int indexTable) {
        this.indexTable = indexTable;
        String query = "SELECT hoadon.ID, hoadon.soBan, hoadon.checkIn, hoadon.checkOut, vatpham.tenVatPham, orderitem.soLuong, vatpham.donGia, orderitem.thoiGianGoiMon"
                + " FROM hoadon\n"
                + "INNER JOIN orderitem ON orderitem.IDhoaDon = hoadon.ID\n"
                + "INNER JOIN vatpham ON vatpham.ID = orderitem.IDVatPham\n"
                + "WHERE soBan = " + indexTable
                + " AND hoadon.isPayed = 0\n"
                + "ORDER BY orderitem.thoiGianGoiMon ASC";

        ResultSet rs = db.queryHandle(query, "get");
        ReadData(rs);
        return orderArr;
    }

    public void OrderCurrentHoaDon(int idVatPham, int sl, Time time) {
        String query = "INSERT INTO orderitem(IDhoaDon, idvatPham, soluong, thoiGianGoiMon) "
                + "VALUES( " + idhoaDon + ", " + idVatPham + " , " + sl + " , " + "\"" + time + "\");";
        db.queryHandle(query, "insert");

    }

    public void PayTable(int tongTien) {
        if (indexTable < 0) {
            return;
        }

        Timestamp datetimeValue = new Timestamp(System.currentTimeMillis());
        String query
                = "UPDATE hoadon SET checkOut = \"" + datetimeValue
                + "\", idNhanVien = " + QuanLyTaiKhoan.Ins.getId()
                +", tongTien = "+ tongTien
                + ", isPayed = 1 WHERE hoadon.ID = " + idhoaDon;
        Database.queryHandle(query, "update");

        query = "UPDATE ban SET trangThai = 1 WHERE ban.soBan = " + indexTable;
        Database.queryHandle(query, "update");

        indexTable = -1;
    }

    public void CreateIDHoaDon() {
//        Calendar calendar = Calendar.getInstance();
//        java.util.Date currentTime = calendar.getTime();
//        java.sql.Time sqlTime = new java.sql.Time(currentTime.getTime());
        Timestamp datetimeValue = new Timestamp(System.currentTimeMillis());
        String query = "INSERT INTO hoadon (soBan, checkIn, checkOut, idNhanVien, isPayed)"
                + "VALUES (" + indexTable
                + ", \"" + datetimeValue
                + "\", NULL , NULL, FALSE)";
        db.queryHandle(query, "insert");

        query = "select id FROM hoadon WHERE soBan = " + indexTable + " AND isPayed = false";
        ResultSet rs = db.queryHandle(query, "get");
        try {
            if (rs.next()) {
                idhoaDon = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Bill> GetAllHoaDon() {
        ArrayList<Bill> list = new ArrayList<Bill>();
        String query = "SELECT hoadon.ID, hoadon.soBan, hoadon.checkIn, hoadon.checkOut, tongTien, thongtinnguoidung.hoVaTen FROM orderitem \n"
                + "INNER JOIN hoadon ON orderitem.IDHoaDon = hoadon.ID\n"
                + "INNER JOIN vatpham ON orderitem.IDVatPham = vatpham.ID\n"
                + "INNER JOIN thongtinnguoidung ON thongtinnguoidung.ID = hoadon.idNhanVien\n"
                + "GROUP BY orderitem.IDHoaDon ";

        ResultSet rs = Database.queryHandle(query, "get");
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int soBan = rs.getInt("soBan");
                Timestamp ci = rs.getTimestamp("checkIn");
                Timestamp co = rs.getTimestamp("checkOut");
                int tongTien = rs.getInt("tongTien");
                String tenNV = rs.getString("hoVaTen");

                Bill bill = new Bill(id, soBan, ci, co, tongTien, tenNV);
                list.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    
    public long GetDoanhThu(Date start, Date end){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String query = String.format("SELECT SUM(orderitem.soLuong * vatpham.donGia) AS tong FROM hoadon inner JOIN orderitem on orderitem.IDHoaDon = hoadon.ID inner JOIN vatpham on vatpham.ID = orderitem.IDVatPham WHERE checkIn >= '%s' AND checkOut <= '%s'", 
            ""+dateFormat.format(start), 
            ""+dateFormat.format(end));
        
        System.out.println(query);
        ResultSet rs = Database.queryHandle(query, "get");
        try {
            while (rs.next()) {
                return rs.getLong("tong");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    
    
    public int GetCountHoaDon(Date start, Date end){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = String.format("SELECT count(hoadon.soBan) AS count FROM hoadon WHERE checkIn >= '%s' AND checkOut <= '%s'", 
            ""+dateFormat.format(start), 
            ""+dateFormat.format(end));
        
        ResultSet rs = Database.queryHandle(query, "get");
        try {
            while (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
    public ArrayList<Bill> GetHoaDon(Date start, Date end){
        ArrayList<Bill> list = new ArrayList<Bill>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String query = String.format("SELECT hoadon.ID, hoadon.soBan, hoadon.checkIn, hoadon.checkOut, sum(orderitem.soLuong * vatpham.donGia) AS TongTien, thongtinnguoidung.hoVaTen FROM orderitem " +
"INNER JOIN hoadon ON orderitem.IDHoaDon = hoadon.ID " +
"INNER JOIN vatpham ON orderitem.IDVatPham = vatpham.ID " +
"INNER JOIN thongtinnguoidung ON thongtinnguoidung.ID = hoadon.idNhanVien " +
"WHERE checkIn >= '%s' AND checkOut <= '%s' GROUP BY orderitem.IDHoaDon desc", 
            ""+dateFormat.format(start), 
            ""+dateFormat.format(end));
        
        ResultSet rs = Database.queryHandle(query, "get");
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                int soBan = rs.getInt("soBan");
                Timestamp ci = rs.getTimestamp("checkIn");
                Timestamp co = rs.getTimestamp("checkOut");
                int tongTien = rs.getInt("tongTien");
                String tenNV = rs.getString("hoVaTen");

                Bill bill = new Bill(id, soBan, ci, co, tongTien, tenNV);
                list.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}
