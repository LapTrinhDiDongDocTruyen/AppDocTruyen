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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryFragment extends Fragment implements TruyenAdapter.ClickedItem{
    RecyclerView recyclerView;
    private  View mRootView;
    TruyenAdapter truyenAdapter;
    Utils utils;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_truyens1);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        truyenAdapter = new TruyenAdapter(this::ClickedTruyen);
        getAllUsers();
    }
    public void getAllUsers(){
    }

    @Override
    public void ClickedTruyen(TruyenResponse truyenResponse) {

        startActivity(new Intent(getContext(),TruyenDetailActivity.class).putExtra("data",truyenResponse));
    }

}

