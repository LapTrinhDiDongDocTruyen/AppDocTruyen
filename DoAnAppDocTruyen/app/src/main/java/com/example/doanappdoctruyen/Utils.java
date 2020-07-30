package com.example.doanappdoctruyen;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    Context context;
    static List<TruyenResponse> truyenHistory = new ArrayList<>();
    public Utils(Context context) {         this.context = context;     }
    public void addFurintureHistorry(TruyenResponse truyenResponse) {
        if(truyenHistory.indexOf(truyenResponse) > 0) {
            this.truyenHistory.add(0,truyenResponse);
        }
    }
    public List<TruyenResponse> getFurnitureHistory(){ return this.truyenHistory; }
}
