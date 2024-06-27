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
public class Nguoi {

    private int ID;
    private String hoVaTen;
    private Date ngaySinh;
    private String diaChi;
    private String taiKhoan;
    private String matKhau;
    private int idQuyen;
    private boolean available;

    public void setIdQuyen(int idQuyen) {
        this.idQuyen = idQuyen;
    }
    
    public int getIdQuyen() {
        return idQuyen;
    }

    public Nguoi() {

    }

    public Nguoi(int ID, String hoVaTen, Date ngaySinh, String diaChi, String taiKhoan, String matKhau, int idQuyen) {
        this.ID = ID;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.idQuyen = idQuyen;
    }
  

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public int getID() {
        return ID;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
       public void setAvailabel(boolean  available) {
        this.available = available;
    }
}
