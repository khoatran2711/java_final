/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Admin
 */
public class Ban {

    private int soBan;
    private String trangThai;
    private boolean available;

    public Ban() {
    }

    public Ban(int soBan, String trangThai) {
        this.soBan = soBan;
        this.trangThai = trangThai;
    }

    public int getSoBan() {
        return soBan;
    }

    public String getTrangThai() {
        if(trangThai.equals("0")){
            return "Đang sử dụng";
        }
        return "Trống";
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public void setAvailabel(boolean  available) {
        this.available = available;
    }

}
