/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class voucher {
    private int ID;
    private String maVoucer;
    private int giaTri;
    private int soLuong;
    private Timestamp ngayBatDau;
    private Timestamp ngayKetThuc;
    private boolean available;

    public voucher() {
    }
    
    public voucher(int ID, String maVoucer, int giaTri, int soLuong,Timestamp ngayBatDau,Timestamp ngayKetThuc,boolean available) {
        this.ID = ID;
        this.maVoucer = maVoucer;
        this.giaTri = giaTri;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.available = available;
    }

    public int getID() {
        return ID;
    }

    public int getGiaTri() {
        return giaTri;
    }

    public String getMaVoucer() {
        return maVoucer;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public Timestamp getNgayBatDau() {
        return ngayBatDau;
    }

    public Timestamp getNgayKetThuc() {
        return ngayKetThuc;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMaVoucer(String maVoucer) {
        this.maVoucer = maVoucer;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public void setGiaTri(int giaTri) {
        this.giaTri = giaTri;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNgayBatDau(Timestamp ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(Timestamp ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
    
    
    
}
