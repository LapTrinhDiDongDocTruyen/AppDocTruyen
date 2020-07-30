package com.example.doanappdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTheLoai extends Fragment implements LoaiAdapter.ClickedItem{
    RecyclerView recyclerView;
    LoaiAdapter loaisAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai, container, false);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_loai);
        int spanCount = 2;//Số cột nếu thiết lập lưới đứng, số dòng nếu lưới ngang
        int orientation = GridLayoutManager.VERTICAL;//Lưới ngang
//int orientation = GridLayoutManager.HORIZONTAL;//Lưới đứng

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        loaisAdapter = new LoaiAdapter(this::ClickedLoai);
        getAllLoai();
    }
    public void getAllLoai(){

        retrofit2.Call<List<LoaiResponse>> loailist = ApiClient.getUserService().getAllLoai();
        loailist.enqueue(new Callback<List<LoaiResponse>>() {
            @Override
            public void onResponse(Call<List<LoaiResponse>> call, Response<List<LoaiResponse>> response) {
                if(response.isSuccessful()){
                    List<LoaiResponse> loaiResponses = response.body();
                    loaisAdapter.setData(loaiResponses);
                    recyclerView.setAdapter(loaisAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<LoaiResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });

    }
    @Override
    public void ClickedLoai(LoaiResponse loaiResponse) {
        startActivity(new Intent(getContext(),TruyenActivity.class).putExtra("loai",loaiResponse));

    }
}
