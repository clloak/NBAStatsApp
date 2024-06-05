package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    private List<Game> games;

    public GameAdapter(List<Game> games) {
        this.games = games;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Game game = games.get(position);
        holder.bind(game);
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void addGame(Game game) {
        games.add(game);
        notifyItemInserted(games.size() - 1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView homeTeamLogo;
        private ImageView awayTeamLogo;
        private TextView homeTeamName;
        private TextView awayTeamName;
        private TextView gameTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeamLogo = itemView.findViewById(R.id.homeTeamLogo);
            awayTeamLogo = itemView.findViewById(R.id.awayTeamLogo);
            homeTeamName = itemView.findViewById(R.id.homeTeamName);
            awayTeamName = itemView.findViewById(R.id.awayTeamName);
            gameTime = itemView.findViewById(R.id.gameTime);
        }

        public void bind(Game game) {
            homeTeamName.setText(game.getHomeTeam().getName());
            awayTeamName.setText(game.getAwayTeam().getName());
            gameTime.setText(game.getGameTime());

            // Load team logos using Glide or any other image loading library
            Glide.with(itemView.getContext()).load(game.getHomeTeam().getLogo()).into(homeTeamLogo);
            Glide.with(itemView.getContext()).load(game.getAwayTeam().getLogo()).into(awayTeamLogo);
        }
    }
}
