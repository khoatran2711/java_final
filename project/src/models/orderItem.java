/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Time;

/**
 *
 * @author Admin
 */
public class orderItem {

    private VatPham vatPham;
    private int soLuong;
    private Time thoiGianGoiMon;

    public orderItem(VatPham vatPham, int soLuong, Time thoiGianGoiMon) {
        this.vatPham = vatPham;
        this.soLuong = soLuong;
        this.thoiGianGoiMon = thoiGianGoiMon;

    }

    public orderItem() {

    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setvatpham(VatPham vatPham) {
        this.vatPham = vatPham;
    }

    public VatPham getvatpham() {
        return vatPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public Time getThoiGianGoiMon() {
        return thoiGianGoiMon;
    }

    public void setThoiGianGoiMon(Time thoiGianGoiMon) {
        this.thoiGianGoiMon = thoiGianGoiMon;
    }
}
