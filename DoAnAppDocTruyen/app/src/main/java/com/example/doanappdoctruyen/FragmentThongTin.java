package com.example.doanappdoctruyen;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentThongTin extends Fragment implements TruyenAdapter.ClickedItem{
    TruyenAdapter truyenAdapter;
    RecyclerView recyclerView;
    TextView tentruyen,tentg,noidungtruyen,sochuong;
    ImageView anhbia;
    String maloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongtin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tentruyen  = view.findViewById(R.id.tentruyen);
        tentg  = view.findViewById(R.id.tentg);
        noidungtruyen = view.findViewById(R.id.noidungtruyen);
        sochuong = view.findViewById(R.id.sochuong);
        anhbia = view.findViewById(R.id.anhtruyen);

        Intent intent = getActivity().getIntent();
        if(intent.getExtras() !=null) {
            TruyenResponse userResponse = (TruyenResponse) intent.getSerializableExtra("data");
            String tenTruyen = userResponse.getTenTruyen();
            String tenTG = userResponse.getTenTG();
            String noiDung = userResponse.getNoiDungTruyen();
            String anh = userResponse.getAnhBia();
            String SoChuong = userResponse.getSoChuong() + " Chương";
            maloai = String.valueOf(userResponse.getMaLoai());
            tentruyen.setText(tenTruyen);
            tentg.setText(tenTG);
            noidungtruyen.setText(noiDung);
            sochuong.setText(SoChuong);
            Picasso.with(getContext()).load(anh).into(anhbia);

        }
        recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        truyenAdapter = new TruyenAdapter(this::ClickedTruyen);
        int orientation = LinearLayoutManager.HORIZONTAL; //Cuộn ngang
// int orientation = LinearLayoutManager.VERTICAL; //cuốn đứng
        boolean reverse = false; //true thì bắt đầu từ phần tử cuối
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.scrollToPosition(0);//Thiết lập phần tử mặc định nếu muốn
// Gắn vào RecylerView
        recyclerView.setLayoutManager(layoutManager);
        getAllChap(maloai);
    }

    public void getAllChap(String ma){

        Call<List<TruyenResponse>> userlist = ApiClient.getUserService().getTruyenTheoMaLoai(ma);
        userlist.enqueue(new Callback<List<TruyenResponse>>() {
            @Override
            public void onResponse(Call<List<TruyenResponse>> call, Response<List<TruyenResponse>> response) {
                if(response.isSuccessful()){
                    List<TruyenResponse> truyenResponses = response.body();
                    truyenAdapter.setData(truyenResponses);
                    recyclerView.setAdapter(truyenAdapter);

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
        startActivity(new Intent(getContext(),TruyenDetailActivity.class).putExtra("data",truyenResponse));
    }
}
