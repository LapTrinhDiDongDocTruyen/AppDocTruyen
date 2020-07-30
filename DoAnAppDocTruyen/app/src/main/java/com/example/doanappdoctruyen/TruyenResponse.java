package com.example.doanappdoctruyen;

import java.io.Serializable;

public class TruyenResponse implements Serializable {
    private int MaTruyen;
    private String TenTruyen;
    private String TenTG;
    private String AnhBia;
    private  String NoiDungTruyen;
    private int MaLoai;

    public String getSoChuong() {
        return SoChuong;
    }

    public void setSoChuong(String soChuong) {
        SoChuong = soChuong;
    }

    public String SoChuong;

    public int getMaTruyen() {
        return MaTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        MaTruyen = maTruyen;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getTenTG() {
        return TenTG;
    }

    public void setTenTG(String tenTG) {
        TenTG = tenTG;
    }

    public String getAnhBia() {
        return AnhBia;
    }

    public void setAnhBia(String anhBia) {
        AnhBia = anhBia;
    }

    public String getNoiDungTruyen() {
        return NoiDungTruyen;
    }

    public void setNoiDungTruyen(String noiDungTruyen) {
        NoiDungTruyen = noiDungTruyen;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    @Override
    public String toString() {
        return "TruyenResponse{" +
                "MaTruyen=" + MaTruyen +
                ", TenTruyen='" + TenTruyen + '\'' +
                ", TenTG='" + TenTG + '\'' +
                ", AnhBia='" + AnhBia + '\'' +
                ", NoiDungTruyen='" + NoiDungTruyen + '\'' +
                ", MaLoai=" + MaLoai +'\'' +
                ", SoChuong=" + SoChuong +
                '}';
    }
}
