package com.example.nbastatsapp;

import android.view.LayoutInflater;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{
    private List<Game> gameList;
    private Context context;

    public GameAdapter(List<Game> gameList, Context context) {
        this.gameList = gameList;
        this.context = context;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.homeTeamName.setText(game.getHomeTeam().getName());
        holder.awayTeamName.setText(game.getAwayTeam().getName());
        holder.gameTime.setText(game.getGameTime());

        Picasso.get().load(game.getHomeTeam().getLogo()).into(holder.homeTeamLogo);
        Picasso.get().load(game.getAwayTeam().getLogo()).into(holder.awayTeamLogo);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView homeTeamLogo;
        ImageView awayTeamLogo;
        TextView homeTeamName;
        TextView awayTeamName;
        TextView gameTime;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTeamLogo = itemView.findViewById(R.id.home_team_logo);
            awayTeamLogo = itemView.findViewById(R.id.away_team_logo);
            homeTeamName = itemView.findViewById(R.id.home_team_name);
            awayTeamName = itemView.findViewById(R.id.away_team_name);
            gameTime = itemView.findViewById(R.id.game_time);
        }
    }
}
