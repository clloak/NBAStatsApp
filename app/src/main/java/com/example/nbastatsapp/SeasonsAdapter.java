package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.SeasonViewHolder> {
    private List<String> seasons;

    public SeasonsAdapter(List<String> seasons) {
        this.seasons = seasons;
    }

    @NonNull
    @Override
    public SeasonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_season, parent, false);
        return new SeasonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeasonViewHolder holder, int position) {
        String season = seasons.get(position);
        holder.seasonTextView.setText(season);
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    public static class SeasonViewHolder extends RecyclerView.ViewHolder {
        TextView seasonTextView;

        public SeasonViewHolder(@NonNull View itemView) {
            super(itemView);
            seasonTextView = itemView.findViewById(R.id.seasonTextView);
        }
    }
}
