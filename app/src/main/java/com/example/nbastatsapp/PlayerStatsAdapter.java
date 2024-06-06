package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayerStatsAdapter extends RecyclerView.Adapter<PlayerStatsAdapter.PlayerStatsViewHolder> {
    private final List<PlayerStats> playerStatsList;

    public PlayerStatsAdapter(List<PlayerStats> playerStatsList) {
        this.playerStatsList = playerStatsList;
    }

    @NonNull
    @Override
    public PlayerStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_stats, parent, false);
        return new PlayerStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerStatsViewHolder holder, int position) {
        PlayerStats playerStats = playerStatsList.get(position);
        holder.playerNameTextView.setText(playerStats.getFirstname() + " " + playerStats.getLastname());
        holder.minutesTextView.setText(String.valueOf(playerStats.getMinutes()));
        holder.pointsTextView.setText(String.valueOf(playerStats.getPoints()));
        holder.reboundsTextView.setText(String.valueOf(playerStats.getRebounds()));
        holder.assistsTextView.setText(String.valueOf(playerStats.getAssists()));
        holder.fieldGoalsTextView.setText(String.valueOf(playerStats.getFieldGoals()));
        holder.threePointersTextView.setText(String.valueOf(playerStats.getThreePointers()));
        holder.plusMinusTextView.setText(String.valueOf(playerStats.getPlusMinus()));
    }

    @Override
    public int getItemCount() {
        return playerStatsList.size();
    }

    public static class PlayerStatsViewHolder extends RecyclerView.ViewHolder {
        TextView playerNameTextView, minutesTextView, pointsTextView, reboundsTextView, assistsTextView;
        TextView fieldGoalsTextView, threePointersTextView, plusMinusTextView;

        public PlayerStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            playerNameTextView = itemView.findViewById(R.id.playerName);
            minutesTextView = itemView.findViewById(R.id.minutes);
            pointsTextView = itemView.findViewById(R.id.points);
            reboundsTextView = itemView.findViewById(R.id.rebounds);
            assistsTextView = itemView.findViewById(R.id.assists);
            fieldGoalsTextView = itemView.findViewById(R.id.field_goals);
            threePointersTextView = itemView.findViewById(R.id.three_pointers);
            plusMinusTextView = itemView.findViewById(R.id.plus_minus);

        }
    }
}
