package com.example.doanappdoctruyen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TruyenDetailActivity extends AppCompatActivity{
    private ViewPager mVpDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_detail);
        Intent intent = getIntent();
        if(intent.getExtras() !=null) {
            TruyenResponse userResponse = (TruyenResponse) intent.getSerializableExtra("data");
            String tenTruyen = userResponse.getTenTruyen();
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(tenTruyen) ;
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
    }
    private void initView() {
        mVpDemo = (ViewPager)findViewById(R.id.vp_demo1);
        mVpDemo.setAdapter(new MyAdapter1(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl_demo);
        tabLayout.setupWithViewPager(mVpDemo);
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