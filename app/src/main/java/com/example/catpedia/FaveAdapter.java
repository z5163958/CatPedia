package com.example.catpedia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FaveAdapter extends RecyclerView.Adapter<FaveAdapter.FaveAdapterViewHolder> {
    private static ArrayList<CatPhoto> catList = new ArrayList<>();

    public void setData(ArrayList<CatPhoto> breedsList){
        this.catList = catList;
    }

    @NonNull
    @Override
    public FaveAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_view, parent, false);
        FaveAdapterViewHolder faveAdapterViewHolder = new FaveAdapterViewHolder(view);
        return faveAdapterViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FaveAdapterViewHolder holder, int position) {
        final String cat = catList.get(position).getBreeds().get(0).getId();
        if (catList.isEmpty()) {
            holder.text.setText("Add cats to favourite list :)");
        }
        else {
            holder.text.setText(catList.get(position).getBreeds().get(0).getName());
        }
    }

    @Override
    public int getItemCount() { return catList.size(); }

    public class FaveAdapterViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView text;

        public FaveAdapterViewHolder(@NonNull View v){
            super(v);
            view = v;
            text = view.findViewById(R.id.textName);
        }
    }
}


