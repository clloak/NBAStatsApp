package com.example.nbastatsapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class BoxScoreAdapter extends RecyclerView.Adapter<BoxScoreAdapter.BoxScoreViewHolder> {
    private List<PlayerStats> playerStats;

    public BoxScoreAdapter(List<PlayerStats> playerStats) {
        this.playerStats = playerStats;
    }

    @NonNull
    @Override
    public BoxScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_player_stats, parent, false);
        return new BoxScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxScoreViewHolder holder, int position) {
        PlayerStats stats = playerStats.get(position);
        holder.playerName.setText(stats.getPlayerName());
        holder.minutes.setText(String.valueOf(stats.getMinutes()));
        holder.points.setText(String.valueOf(stats.getPoints()));
        holder.rebounds.setText(String.valueOf(stats.getRebounds()));
        holder.assists.setText(String.valueOf(stats.getAssists()));
        holder.fieldGoals.setText(String.valueOf(stats.getFieldGoals()));
        holder.threePointers.setText(String.valueOf(stats.getThreePointers()));
        holder.plusMinus.setText(String.valueOf(stats.getPlusMinus()));
    }

    @Override
    public int getItemCount() {
        return playerStats.size();
    }

    public static class BoxScoreViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, minutes, points, rebounds, assists, fieldGoals, threePointers, plusMinus;

        public BoxScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            minutes = itemView.findViewById(R.id.minutes);
            points = itemView.findViewById(R.id.points);
            rebounds = itemView.findViewById(R.id.rebounds);
            assists = itemView.findViewById(R.id.assists);
            fieldGoals = itemView.findViewById(R.id.fieldGoals);
            threePointers = itemView.findViewById(R.id.threePointers);
            plusMinus = itemView.findViewById(R.id.plusMinus);
        }
    }
}
