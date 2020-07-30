package com.example.doanappdoctruyen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class TruyenAdapter extends RecyclerView.Adapter<TruyenAdapter.UserAdapterVH> implements Filterable {

    private List<TruyenResponse> truyenResponseList;
    private Context context;
    private ClickedItem clickedItem;
    Utils utils = new Utils(context);
    List<TruyenResponse> getTruyenResponsesFiltered;
    public TruyenAdapter(List<TruyenResponse> truyenResponses)
    {
        this.truyenResponseList = truyenResponses;
        this.getTruyenResponsesFiltered = truyenResponses;
    }
    public TruyenAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }
    public void TimKiem(String s)
    {
        s = s.toUpperCase();
        int k = 0;
        for(int i = 0; i < getTruyenResponsesFiltered.size();i++)
        {
            TruyenResponse t = getTruyenResponsesFiltered.get(i);
            String ten = t.getTenTruyen().toUpperCase();
            if(ten.indexOf(ten)>=0)
            {
                getTruyenResponsesFiltered.set(i,getTruyenResponsesFiltered.get(k));
                getTruyenResponsesFiltered.set(k,t);
                k++;
            }
        }
        notifyDataSetChanged();
    }
    public void setData(List<TruyenResponse> truyenResponseList) {
        this.truyenResponseList = truyenResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new TruyenAdapter.UserAdapterVH(LayoutInflater.from(context).inflate(R.layout.item_truyen,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
        View thu;
        TruyenResponse truyenResponse = truyenResponseList.get(position);

        String tenTruyen = truyenResponse.getTenTruyen();
        String tenTG = truyenResponse.getTenTG();
        String SoChuong = truyenResponse.getSoChuong();
        Picasso.with(context)
                .load(truyenResponse.getAnhBia())
                .into(holder.anhtruyen);

        holder.tentruyen.setText(tenTruyen);
        holder.tentg.setText(tenTG);
        holder.sochuong.setText(SoChuong+" Chương");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils.addFurintureHistorry(truyenResponse);
                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                clickedItem.ClickedTruyen(truyenResponse);
            }
        });
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null| constraint.length() ==0)
                {
                    filterResults.count = getTruyenResponsesFiltered.size();
                    filterResults.values=getTruyenResponsesFiltered;
                }else{
                    String search = constraint.toString().toLowerCase();
                    List<TruyenResponse> truyendata = new ArrayList<>();
                    for(TruyenResponse truyen: getTruyenResponsesFiltered){
                        if(truyen.getTenTruyen().toLowerCase().contains(search)){
                            truyendata.add(truyen);
                        }
                    }
                    filterResults.count = truyendata.size();
                    filterResults.values = truyendata;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                truyenResponseList = (List<TruyenResponse>)results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public interface ClickedItem{
        public void ClickedTruyen(TruyenResponse userResponse);
    }

    @Override
    public int getItemCount() {
        return truyenResponseList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder{
        View thu;
        TextView tentruyen;
        TextView tentg,sochuong;
        ImageView anhtruyen,imageMore;

        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            tentruyen = itemView.findViewById(R.id.tentruyen);
            tentg = itemView.findViewById(R.id.tentg);
            sochuong = itemView.findViewById(R.id.sochuong);
            anhtruyen = itemView.findViewById(R.id.anhtruyen);
            imageMore = itemView.findViewById(R.id.imageMore);
        }

    }
}
