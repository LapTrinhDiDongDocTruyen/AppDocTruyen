package com.example.doanappdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class DetailChapter extends AppCompatActivity {
    TextView tenchap,nd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chapter_detail);

        tenchap  = findViewById(R.id.tenchap);
        nd  = findViewById(R.id.noidung);
        Intent intent = getIntent();
        if(intent.getExtras() !=null) {
            ChapterResponse chapterResponse = (ChapterResponse) intent.getSerializableExtra("detail");
            String tench = chapterResponse.getTenChap();
            String noiDung = chapterResponse.getNoiDung();
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(tench) ;
            actionBar.setDisplayHomeAsUpEnabled(true);
            tenchap.setText(tench);
            nd.setText(noiDung);
        }




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



    }


//    public void getAllChap(String ma){
//
//        Call<ChapterResponse> userlist = ApiClient.getUserService().getAllChapterMa(ma);
//        userlist.enqueue(new Callback<ChapterResponse>() {
//            @Override
//            public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
//                if(response.isSuccessful()){
//                    ChapterResponse chapterResponses = response.body();
//                    chapAdapter.setData(chapterResponses);
//                    recyclerView.setAdapter(chapAdapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ChapterResponse> call, Throwable t) {
//                Log.e("failure",t.getLocalizedMessage());
//            }
//        });
//    }

//    public void ClickedChap(ChapterResponse chapterResponse) {
//
//
//    }
