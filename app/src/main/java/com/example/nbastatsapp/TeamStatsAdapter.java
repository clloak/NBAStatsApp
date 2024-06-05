package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.TeamStatsViewHolder> {
    private List<TeamStats> teamStatsList;

    public TeamStatsAdapter(List<TeamStats> teamStatsList) {
        this.teamStatsList = teamStatsList;
    }

    @NonNull
    @Override
    public TeamStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team_stats, parent, false);
        return new TeamStatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamStatsViewHolder holder, int position) {
        TeamStats teamStats = teamStatsList.get(position);
        holder.playerName.setText(teamStats.getPlayerName());
        holder.gamesPlayed.setText(String.valueOf(teamStats.getGamesPlayed()));
        holder.points.setText(String.valueOf(teamStats.getPoints()));
        holder.rebounds.setText(String.valueOf(teamStats.getRebounds()));
        holder.assists.setText(String.valueOf(teamStats.getAssists()));
        holder.steals.setText(String.valueOf(teamStats.getSteals()));
        holder.blocks.setText(String.valueOf(teamStats.getBlocks()));
        holder.turnovers.setText(String.valueOf(teamStats.getTurnovers()));
        holder.personalFouls.setText(String.valueOf(teamStats.getPersonalFouls()));
        holder.assistToTurnoverRatio.setText(String.valueOf(teamStats.getAssistToTurnoverRatio()));
    }

    @Override
    public int getItemCount() {
        return teamStatsList.size();
    }

    static class TeamStatsViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, gamesPlayed, points, rebounds, assists, steals, blocks, turnovers, personalFouls, assistToTurnoverRatio;

        public TeamStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerNameTextView);
            gamesPlayed = itemView.findViewById(R.id.gamesPlayedTextView);
            points = itemView.findViewById(R.id.pointsTextView);
            rebounds = itemView.findViewById(R.id.reboundsTextView);
            assists = itemView.findViewById(R.id.assistsTextView);
            steals = itemView.findViewById(R.id.stealsTextView);
            blocks = itemView.findViewById(R.id.blocksTextView);
            turnovers = itemView.findViewById(R.id.turnoversTextView);
            personalFouls = itemView.findViewById(R.id.personalFoulsTextView);
            assistToTurnoverRatio = itemView.findViewById(R.id.assistToTurnoverRatioTextView);
        }
    }
}

