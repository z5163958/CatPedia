package com.example.catpedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class FaveRecyclerFragment extends Fragment {

    private RecyclerView recyclerView;
    ArrayList<CatPhoto> faveList = CatDetailActivity.dList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_fave_recycler, container, false);
        recyclerView = view.findViewById(R.id.fave_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        FaveAdapter faveAdapter = new FaveAdapter();
        faveAdapter.setData(faveList);
        recyclerView.setAdapter(faveAdapter);
        return view;
    }
}
