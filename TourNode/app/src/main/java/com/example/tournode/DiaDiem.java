package com.example.tournode;

import java.io.Serializable;

public class DiaDiem implements Serializable {
    private String chuDe;
    private int ID;
    private String tenDiaDiem;
    private String moTa;
    private String diaChi;
    private byte [] hinhAnh;


    public DiaDiem() {
    }
    public DiaDiem(int id, String tenDiaDiem,String chuDe,String moTa ,String diaChi , byte[] hinhAnh){
        this.ID = id;
        this.chuDe = chuDe;
        this.tenDiaDiem = tenDiaDiem;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }


    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String ghiChu) {
        this.diaChi = ghiChu;
    }

    public String getChuDe() {
        return chuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setChuDe(String chuDe) {
        this.chuDe = chuDe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }
}
