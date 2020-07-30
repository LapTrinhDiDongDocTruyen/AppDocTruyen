package com.example.doanappdoctruyen;

import java.io.Serializable;

public class LoaiResponse implements Serializable {
    public int MaLoai;
    public String TenLoai;

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    @Override
    public String toString() {
        return "Loai{" +
                "MaLoai=" + MaLoai +
                ", TenLoai='" + TenLoai + '\'' +
                '}';
    }
}
