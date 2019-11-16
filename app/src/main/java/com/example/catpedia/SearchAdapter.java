package com.example.catpedia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.searchViewHolder> {
    public List<Breeds> catList;

    public void setData(List<Breeds> catList){
        this.catList = catList;
    }
    @NonNull
    @Override
    public SearchAdapter.searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_view, parent, false);
        searchViewHolder searchViewHolder = new searchViewHolder(view);
        return searchViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.searchViewHolder holder, final int position) {
        final Breeds catPosition = catList.get(position);
        holder.textView.setText(catPosition.getName());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("BreedID", catList.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }


    public static class searchViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView textView;

        public searchViewHolder(@NonNull View v) {
            super(v);
            view = v;
            textView = v.findViewById(R.id.textName);
        }
    }
}
