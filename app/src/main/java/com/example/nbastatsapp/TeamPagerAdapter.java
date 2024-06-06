package com.example.nbastatsapp;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;
public class TeamPagerAdapter extends FragmentPagerAdapter {
    private final List<Team> teams;

    public TeamPagerAdapter(@NonNull FragmentManager fm, int tabCount, List<Team> teams) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.teams = teams;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Team team = teams.get(position);
        return TeamFragment.newInstance(team.getId(), team.getName());
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return teams.get(position).getName();
    }
}
