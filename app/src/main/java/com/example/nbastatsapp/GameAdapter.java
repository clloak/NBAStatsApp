package com.example.nbastatsapp;

import android.view.LayoutInflater;
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

    public GameAdapter(List<Game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.gameTitle.setText(game.getHomeTeam() + " vs " + game.getAwayTeam());
        holder.gameDate.setText(game.getDate());
        Picasso.get().load(game.getHomeTeamLogo()).into(holder.team1Logo);
        Picasso.get().load(game.getAwayTeamLogo()).into(holder.team2Logo);
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class GameViewHolder extends RecyclerView.ViewHolder {
        TextView gameTitle;
        TextView gameDate;
        ImageView team1Logo;
        ImageView team2Logo;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitle);
            gameDate = itemView.findViewById(R.id.gameDate);
            team1Logo = itemView.findViewById(R.id.team1Logo);
            team2Logo = itemView.findViewById(R.id.team2Logo);
        }
    }
}
