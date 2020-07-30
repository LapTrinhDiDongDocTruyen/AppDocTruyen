package com.example.doanappdoctruyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doanappdoctruyen.R.menu.menu;

public class TruyenFragment extends Fragment implements TruyenAdapter.ClickedItem{
    RecyclerView recyclerView;
    TruyenAdapter usersAdapter;
    Utils utils;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_truyen, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_truyens);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        usersAdapter = new TruyenAdapter(this::ClickedTruyen);
        getAllTruyen();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                usersAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search_view){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getAllTruyen(){

        retrofit2.Call<List<TruyenResponse>> userlist = ApiClient.getUserService().getAllUsers();

        userlist.enqueue(new Callback<List<TruyenResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<TruyenResponse>> call, Response<List<TruyenResponse>> response) {

                if(response.isSuccessful()){
                    List<TruyenResponse> userResponses = response.body();
                    usersAdapter.setData(userResponses);
                    recyclerView.setAdapter(usersAdapter);

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
        utils.addFurintureHistorry(truyenResponse);
        startActivity(new Intent(getContext(),TruyenDetailActivity.class).putExtra("data",truyenResponse));
    }

}
