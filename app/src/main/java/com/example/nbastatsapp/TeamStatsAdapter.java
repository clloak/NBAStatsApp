package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TeamStatsAdapter extends RecyclerView.Adapter<TeamStatsAdapter.TeamStatsViewHolder> {
    private final List<TeamStats> teamStatsList;

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
        holder.playerNameTextView.setText(teamStats.getPlayerName());
        holder.gamesPlayedTextView.setText(String.valueOf(teamStats.getGamesPlayed()));
        holder.pointsTextView.setText(String.valueOf(teamStats.getPoints()));
        holder.reboundsTextView.setText(String.valueOf(teamStats.getRebounds()));
        holder.assistsTextView.setText(String.valueOf(teamStats.getAssists()));
        holder.stealsTextView.setText(String.valueOf(teamStats.getSteals()));
        holder.blocksTextView.setText(String.valueOf(teamStats.getBlocks()));
        holder.turnoversTextView.setText(String.valueOf(teamStats.getTurnovers()));
        holder.personalFoulsTextView.setText(String.valueOf(teamStats.getPersonalFouls()));
        holder.assistToTurnoverRatioTextView.setText(String.valueOf(teamStats.getAssistToTurnoverRatio()));
    }

    @Override
    public int getItemCount() {
        return teamStatsList.size();
    }

    public static class TeamStatsViewHolder extends RecyclerView.ViewHolder {
        TextView playerNameTextView;
        TextView gamesPlayedTextView;
        TextView pointsTextView;
        TextView reboundsTextView;
        TextView assistsTextView;
        TextView stealsTextView;
        TextView blocksTextView;
        TextView turnoversTextView;
        TextView personalFoulsTextView;
        TextView assistToTurnoverRatioTextView;
        public TeamStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            playerNameTextView = itemView.findViewById(R.id.playerName);
            gamesPlayedTextView = itemView.findViewById(R.id.games_played);
            pointsTextView = itemView.findViewById(R.id.points);
            reboundsTextView = itemView.findViewById(R.id.rebounds);
            assistsTextView = itemView.findViewById(R.id.assists);
            stealsTextView = itemView.findViewById(R.id.steals);
            blocksTextView = itemView.findViewById(R.id.blocks);
            turnoversTextView = itemView.findViewById(R.id.turnovers);
            personalFoulsTextView = itemView.findViewById(R.id.personal_fouls);
            assistToTurnoverRatioTextView = itemView.findViewById(R.id.assist_to_turnover_ratio);
        }
    }
}

