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

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {

    private List<Breeds> catsToAdapt;

    public void setData(List<Breeds> catsToAdapt){
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_view, parent, false);
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Breeds catAtPosition = catsToAdapt.get(position);
        holder.name.setText(catAtPosition.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("BreedID", catAtPosition.getId());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() { return catsToAdapt.size(); }
    public static class CatViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public TextView name;

        public CatViewHolder(View v){
            super(v);
            view =v;
            name = v.findViewById(R.id.textName);
        }
    }

}
