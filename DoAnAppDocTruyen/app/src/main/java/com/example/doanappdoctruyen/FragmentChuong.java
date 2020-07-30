package com.example.doanappdoctruyen;

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
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChuong extends Fragment implements ChapAdapter.ClickedItem{
    RecyclerView recyclerView;
    ChapAdapter chapAdapter;
    String matruyen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chuong, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent.getExtras() !=null) {
            TruyenResponse userResponse = (TruyenResponse) intent.getSerializableExtra("data");
            matruyen = String.valueOf(userResponse.getMaTruyen());

        }
        recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        chapAdapter = new ChapAdapter(this::ClickedChap);

        getAllChap(matruyen);
    }
    public void getAllChap(String ma){

        Call<List<ChapterResponse>> userlist = ApiClient.getUserService().getAllChapter(ma);
        userlist.enqueue(new Callback<List<ChapterResponse>>() {
            @Override
            public void onResponse(Call<List<ChapterResponse>> call, Response<List<ChapterResponse>> response) {
                if(response.isSuccessful()){
                    List<ChapterResponse> chapterResponses = response.body();
                    chapAdapter.setData(chapterResponses);
                    recyclerView.setAdapter(chapAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<ChapterResponse>> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void ClickedChap(ChapterResponse chapResponse) {
        startActivity(new Intent(getContext(),DetailChapter.class).putExtra("detail",chapResponse));
    }
}
