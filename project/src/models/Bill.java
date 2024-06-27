/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author ACER
 */
public class Bill {

    private int id;
    private int soBan;
    private Timestamp checkIn;
    private Timestamp checkOut;
    private int tongTien;
    private String hotenNV;
    private boolean available;

    public Bill(int id, int soBan, Timestamp checkIn, Timestamp checkOut, int tongTien, String hotenNV) {
        setId(id);
        setSoBan(soBan);
        setCheckIn(checkIn);
        setCheckOut(checkOut);
        setTongTien(tongTien);
        setHotenNV(hotenNV);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public void setCheckIn(Timestamp checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Timestamp checkOut) {
        this.checkOut = checkOut;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public void setHotenNV(String hotenNV) {
        this.hotenNV = hotenNV;
    }

    public int getId() {
        return id;
    }

    public int getSoBan() {
        return soBan;
    }

    public Timestamp getCheckIn() {
        return checkIn;
    }

    public Timestamp getCheckOut() {
        return checkOut;
    }

    public int getTongTien() {
        return tongTien;
    }

    public String getHotenNV() {
        return hotenNV;
    }
    public void setAvailabel(boolean  available) {
        this.available = available;
    }
}
