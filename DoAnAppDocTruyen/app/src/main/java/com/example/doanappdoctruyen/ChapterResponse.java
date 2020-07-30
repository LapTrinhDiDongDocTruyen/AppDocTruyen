package com.example.doanappdoctruyen;

import java.io.Serializable;

public class ChapterResponse implements Serializable {
    private  int MaChap;
    private String TenChap;
    private String NoiDung;
    private int MaTruyen;

    public int getMaChap() {
        return MaChap;
    }

    public void setMaChap(int maChap) {
        MaChap = maChap;
    }

    public String getTenChap() {
        return TenChap;
    }

    public void setTenChap(String tenChap) {
        TenChap = tenChap;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public int getMaTruyen() {
        return MaTruyen;
    }

    public void setMaTruyen(int maTruyen) {
        MaTruyen = maTruyen;
    }

    @Override
    public String toString() {
        return "ChapterResponse{" +
                "MaChap=" + MaChap +
                ", TenChap='" + TenChap + '\'' +
                ", NoiDung='" + NoiDung + '\'' +
                ", MaTruyen=" + MaTruyen +
                '}';
    }
}
