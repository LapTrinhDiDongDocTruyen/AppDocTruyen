package com.example.doanappdoctruyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LoaiAdapter extends RecyclerView.Adapter<LoaiAdapter.LoaiAdapterVH>{
    private List<LoaiResponse> loaiResponseList;
    private Context context;
    private LoaiAdapter.ClickedItem clickedItem;
    public LoaiAdapter(LoaiAdapter.ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }
    public void setData(List<LoaiResponse> loaiResponseList) {
        this.loaiResponseList = loaiResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiAdapter.LoaiAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new LoaiAdapter.LoaiAdapterVH(LayoutInflater.from(context).inflate(R.layout.item_loai,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiAdapter.LoaiAdapterVH holder, int position) {
        View thu;
        LoaiResponse loaiResponse = loaiResponseList.get(position);

        String tenLoai = loaiResponse.getTenLoai();


        holder.tenloai.setText(tenLoai);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedLoai(loaiResponse);
            }
        });
    }


    public interface ClickedItem{
        public void ClickedLoai(LoaiResponse loaiResponse);
    }

    @Override
    public int getItemCount() {
        return loaiResponseList.size();
    }

    public class LoaiAdapterVH extends RecyclerView.ViewHolder{
        TextView tenloai;

        public LoaiAdapterVH(@NonNull View itemView) {
            super(itemView);
            tenloai = itemView.findViewById(R.id.tenloai);
        }

    }
}
