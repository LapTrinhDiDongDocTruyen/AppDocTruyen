package com.example.doanappdoctruyen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("truyen/")
    Call<List<TruyenResponse>> getAllUsers();
    @GET("Chapter/{id}")
    Call<List<ChapterResponse>> getAllChapter(@Path("id") String ma);
    @GET("Loaitruyen/")
    Call<List<LoaiResponse>> getAllLoai();
    @GET("TruyenTheoMaLoai/{id}")
    Call<List<TruyenResponse>> getTruyenTheoMaLoai(@Path("id") String ma);
}
