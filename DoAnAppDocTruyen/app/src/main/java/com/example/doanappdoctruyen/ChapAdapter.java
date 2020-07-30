package com.example.doanappdoctruyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChapAdapter extends RecyclerView.Adapter<ChapAdapter.ChapAdapterVH>{
    private List<ChapterResponse> chapResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public ChapAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<ChapterResponse> chapResponseList) {
        this.chapResponseList = chapResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChapAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ChapAdapter.ChapAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_users,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChapAdapter.ChapAdapterVH holder, int position) {

        ChapterResponse chapResponse = chapResponseList.get(position);

        String prefix = chapResponse.getTenChap();

        holder.ten.setText(prefix);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedChap(chapResponse);
            }
        });

    }

    public interface ClickedItem{
        void ClickedChap(ChapterResponse chapResponse);

    }

    @Override
    public int getItemCount() {
        return chapResponseList.size();
    }

    public static class ChapAdapterVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView ma;
        TextView ten;
        public ChapAdapterVH(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenchap);
            imageView = itemView.findViewById(R.id.imageMore);
        }
    }
}
