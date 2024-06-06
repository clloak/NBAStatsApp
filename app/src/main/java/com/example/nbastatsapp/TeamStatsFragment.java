package com.example.nbastatsapp;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeamStatsFragment extends Fragment {
    private static final String ARG_TEAM_STATS = "team_stats";
    private List<TeamStats> teamStatsList;

    public TeamStatsFragment() {
        // Required empty public constructor
    }

    public static TeamStatsFragment newInstance(List<TeamStats> teamStatsList) {
        TeamStatsFragment fragment = new TeamStatsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_TEAM_STATS, new ArrayList<>(teamStatsList));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            teamStatsList = getArguments().getParcelableArrayList(ARG_TEAM_STATS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team_stats, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.teamStatsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TeamStatsAdapter adapter = new TeamStatsAdapter(teamStatsList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
