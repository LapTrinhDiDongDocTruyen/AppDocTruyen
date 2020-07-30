package com.example.doanappdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TruyenActivity extends AppCompatActivity implements TruyenAdapter.ClickedItem{
    RecyclerView recyclerView;
    TruyenAdapter truyensAdapter;
    String maloai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truyen_activity);
        Intent intent = getIntent();
        if(intent.getExtras() !=null) {
            LoaiResponse loaiResponse = (LoaiResponse) intent.getSerializableExtra("loai");
            maloai = String.valueOf(loaiResponse.getMaLoai());
            String tenloai = loaiResponse.getTenLoai();
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(tenloai) ;
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        recyclerView = findViewById(R.id.rv_truyens);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        truyensAdapter = new TruyenAdapter(this::ClickedTruyen);
        getAllTruyenTheoLoai(maloai);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void getAllTruyenTheoLoai(String ma){

        retrofit2.Call<List<TruyenResponse>> userlist = ApiClient.getUserService().getTruyenTheoMaLoai(ma);

        userlist.enqueue(new Callback<List<TruyenResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<TruyenResponse>> call, Response<List<TruyenResponse>> response) {

                if(response.isSuccessful()){
                    List<TruyenResponse> truyenResponseList = response.body();
                    truyensAdapter.setData(truyenResponseList);
                    recyclerView.setAdapter(truyensAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<TruyenResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedTruyen(TruyenResponse truyenResponse) {
        startActivity(new Intent(this,TruyenDetailActivity.class).putExtra("data",truyenResponse));
    }
}
