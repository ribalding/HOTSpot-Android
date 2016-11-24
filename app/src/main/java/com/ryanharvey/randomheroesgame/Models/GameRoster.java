package com.ryanharvey.randomheroesgame.Models;

import java.util.ArrayList;

/**
 * Created by Ryan on 11/24/2016.
 */

public class GameRoster {

    public GameRoster(){}
    private ArrayList<Hero> teamA = new ArrayList<>();
    private ArrayList<Hero> teamB = new ArrayList<>();

    public GameRoster(ArrayList<Hero> heroesA, ArrayList<Hero> heroesB){
        this.teamA = heroesA;
        this.teamB = heroesB;
    }

    public ArrayList<Hero> getTeamA() {
        return teamA;
    }

    public ArrayList<Hero> getTeamB(){
        return teamB;
    }

    public ArrayList<Hero> getRoster(){
        ArrayList<Hero> roster = new ArrayList<>();
        roster.addAll(teamA);
        roster.addAll(teamB);
        return roster;
    }
}
