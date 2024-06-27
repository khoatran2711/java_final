/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class NhanVien extends Nguoi{
    private int luong;
    private String caLam;

    public NhanVien(int luong, String caLam, int ID, String hoVaTen, Date ngaySinh, String diaChi, String taiKhoan, String matKhau, int idQuyen,boolean available) {
        super(ID, hoVaTen, ngaySinh, diaChi, taiKhoan, matKhau, idQuyen);
        this.luong = luong;
        this.caLam = caLam;
    }

    public NhanVien() {
    }

    public String getCaLam() {
        return caLam;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }

  
    
}
