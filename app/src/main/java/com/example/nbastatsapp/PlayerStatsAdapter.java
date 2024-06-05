package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlayerStatsAdapter extends RecyclerView.Adapter<PlayerStatsAdapter.PlayerStatsViewHolder> {
    private List<PlayerStats> playerStatsList;

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
        holder.playerName.setText(playerStats.getName());
        holder.minutes.setText(String.valueOf(playerStats.getMinutes()));
        holder.points.setText(String.valueOf(playerStats.getPoints()));
        holder.rebounds.setText(String.valueOf(playerStats.getRebounds()));
        holder.assists.setText(String.valueOf(playerStats.getAssists()));
        holder.fieldGoals.setText(String.valueOf(playerStats.getFieldGoals()));
        holder.threePointers.setText(String.valueOf(playerStats.getThreePointers()));
        holder.plusMinus.setText(String.valueOf(playerStats.getPlusMinus()));
    }

    @Override
    public int getItemCount() {
        return playerStatsList.size();
    }

    static class PlayerStatsViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, minutes, points, rebounds, assists, fieldGoals, threePointers, plusMinus;

        public PlayerStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            minutes = itemView.findViewById(R.id.minutes);
            points = itemView.findViewById(R.id.points);
            rebounds = itemView.findViewById(R.id.rebounds);
            assists = itemView.findViewById(R.id.assists);
            fieldGoals = itemView.findViewById(R.id.field_goals);
            threePointers = itemView.findViewById(R.id.three_pointers);
            plusMinus = itemView.findViewById(R.id.plus_minus);
        }
    }
}
