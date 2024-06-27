/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Statement;
import config.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Nguoi;
import models.NhanVien;
import views.LoginFrame;

/**
 *
 * @author Admin
 */
public class QuanLyNhanVien {

    Database db = new Database();
    private ArrayList<NhanVien> dSNhanVien;

    public QuanLyNhanVien() {
        dSNhanVien = new ArrayList<NhanVien>();
    }

    public void themDsNhanVien(NhanVien nv) {
        dSNhanVien.add(nv);
    }

    public ArrayList<NhanVien> GetAllData() {
        return dSNhanVien;
    }

    public void getNhanVien() {
        dSNhanVien = new ArrayList<NhanVien>();
//        Database db = new Database();
        String query = "SELECT * FROM thongtinnguoidung "
                + "INNER JOIN quyen ON quyen.ID = thongtinnguoidung.IDQuyen " + "WHERE available = '1'" 
                + "ORDER BY thongtinnguoidung.id DESC;";
        ResultSet rs = db.queryHandle(query, "get");
        if (rs != null) {
            try {
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String hoVaTen = rs.getNString("hoVaTen");
                    Date ngaySinh = rs.getDate("ngaySinh");
                    String diaChi = rs.getString("diaChi");
                    String quyen = rs.getString("tenQuyen");
                    String taiKhoan = rs.getString("taiKhoan");
                    String matKhau = rs.getString("matKhau");
                    boolean available = rs.getBoolean("available");
                    int idRole = rs.getInt("idQuyen");
                    NhanVien nv = new NhanVien(000, "A", id, hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, idRole,available);
                    themDsNhanVien(nv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean checkData() {
        if (dSNhanVien == null) {
            return false;
        }
        return true;
    }

    public void themThongTinNhanVien(int id, NhanVien nv) {
        ResultSet rs = db.queryHandle("INSERT INTO nhanvien VALUES ( " + id + ',' + nv.getLuong() + ',' + "\'" + nv.getCaLam() + "\'" + ")", "insert");
    }

    public int themThongTinNguoiDung(Nguoi nguoi) {
        String query = "INSERT INTO thongtinnguoidung( hoVaTen,ngaySinh, diaChi, taiKhoan, matKhau,IDquyen) VALUES ( " 
                + "\'" + nguoi.getHoVaTen() + "\'" 
                + ',' + "\'" + "2003-12-12" + "\'" //nguoi.getNgaySinh()
                + ',' + "\'" + nguoi.getDiaChi() + "\'" 
                + ',' + "\'" + nguoi.getTaiKhoan() + "\'" 
                + ',' + "\'" + nguoi.getMatKhau() + "\'" 
                + ',' + nguoi.getIdQuyen() + " )";
        
        ResultSet rs = db.queryHandle(query, "insert");
        int id = 0;
        try {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void themNhanVien(NhanVien nv) throws SQLException {
        getNhanVien();
        int id = themThongTinNguoiDung(nv);
        themThongTinNhanVien(id, nv);

    }

    public void xoaNhanVien(int id) {
        ResultSet rs = db.queryHandle("UPDATE nhanvien SET available = '0'  IDNguoiDung=" + id, "update");
        rs = db.queryHandle("UPDATE nhanvien SET available = '0'  IDNguoiDung=" + id, "update");
    }

    public void searchByColumn(String col, String value) throws SQLException {
        ResultSet rs = db.queryHandle("select * from ThongTinNguoiDung inner join quyen on quyen.ID = thongtinnguoidung.IDQuyen inner join nhanVien on thongtinnguoidung.ID = nhanvien.ID Where " + col + "=" + "\'" + value + "\'", "get");
        if (rs.next()) {
            int id = rs.getInt("ID");
            String hoVaTen = rs.getNString("hoVaTen");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String quyen = rs.getString("tenQuyen");
            String caLam = rs.getString("caLam");
            int luong = rs.getInt("luong");
            String taiKhoan = rs.getString("taiKhoan");
            String matKhau = rs.getString("matKhau");
            int idRole = rs.getInt("id");
            boolean available = rs.getBoolean("available");

            NhanVien nv = new NhanVien(luong, caLam, id, hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, idRole, available);
            themDsNhanVien(nv);
        }
    }

    public int GetRoleWithUserNameAndPass(String userName, String passWord) {
        String query = "SELECT * FROM thongtinnguoidung WHERE taiKhoan = \'" + userName + "\'" + "and matkhau = \'" + passWord + "\';";
        ResultSet r = Database.queryHandle(query, "get");
        try {
            if (r.next()) {
                QuanLyTaiKhoan qlTaiKhoan = new QuanLyTaiKhoan(r.getInt("ID"));
                return r.getInt("IDQuyen");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        return -1;
    }

    public NhanVien getaNhanVien(int idNguoiDung) throws SQLException {
        String query = "select * from ThongTinNguoiDung inner join quyen on quyen.ID = thongtinnguoidung.IDQuyen inner join nhanVien on thongtinnguoidung.ID = nhanvien.ID Where nhanVien.ID = " + idNguoiDung + " ";
        ResultSet rs = db.queryHandle(query, "get");
        if (rs.next()) {
            int id = rs.getInt("ID");
            String hoVaTen = rs.getNString("hoVaTen");
            Date ngaySinh = rs.getDate("ngaySinh");
            String diaChi = rs.getString("diaChi");
            String quyen = rs.getString("tenQuyen");
            int idRole = rs.getInt("idRole");
            String caLam = rs.getString("caLam");
            int luong = rs.getInt("luong");
            String taiKhoan = rs.getString("taiKhoan");
            String matKhau = rs.getString("matKhau");
            boolean available = rs.getBoolean("available");



            NhanVien nv = new NhanVien(luong, caLam, id, hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, idRole, available);
            return nv;
        }
        return null;
    }

    public void suaNhanVien(NhanVien nv) {
        String queryThongTinNguoiDung = "UPDATE thongTinNGuoiDung SET hoVaTen = \'" + nv.getHoVaTen() + "\', ngaySinh = \'" + nv.getNgaySinh() + "\', diaChi = \'" + nv.getDiaChi() + "\' , taiKhoan = \'" + nv.getTaiKhoan() + "\', matKhau = \'" + nv.getDiaChi() + "\' WHERE id = " + nv.getID() + ";";
        ResultSet rs = db.queryHandle(queryThongTinNguoiDung, "insert");
        String queryNhanVien = "UPDATE thongTinNGuoiDung SET  caLam = \'" + nv.getCaLam() + "\', luong = \'" + nv.getLuong() + "\' WHERE IDNguoiDung = " + nv.getID() + ";";
        rs = db.queryHandle(queryNhanVien, "insert");
    }
}
