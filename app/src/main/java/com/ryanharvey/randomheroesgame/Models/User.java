package com.ryanharvey.randomheroesgame.Models;

import java.util.ArrayList;

/**
 * Created by Ryan on 11/8/2016.
 */

public class User {
    private String playerID;
    private String name;
    private MMR quickMatchMMR;
    private MMR heroLeagueMMR;
    private MMR teamLeagueMMR;
    private MMR unrankedDraftMMR;
    private ArrayList<MMRSet> mmrHistory = new ArrayList<MMRSet>();

    public User(){}

    public User(String playerID, String name, MMRSet set){
        this.playerID = playerID;
        this.name = name;
        this.quickMatchMMR = set.getQuickMatch();
        this.teamLeagueMMR = set.getTeamLeague();
        this.heroLeagueMMR = set.getHeroLeague();
        this.unrankedDraftMMR = set.getUnrankedDraft();
        this.mmrHistory.add(set);
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MMR getQuickMatchMMR() {
        return quickMatchMMR;
    }

    public void setQuickMatchMMR(MMR quickMatchMMR) {
        this.quickMatchMMR = quickMatchMMR;
    }

    public MMR getHeroLeagueMMR() {
        return heroLeagueMMR;
    }

    public void setHeroLeagueMMR(MMR heroLeagueMMR) {
        this.heroLeagueMMR = heroLeagueMMR;
    }

    public MMR getTeamLeagueMMR() {
        return teamLeagueMMR;
    }

    public void setTeamLeagueMMR(MMR teamLeagueMMR) {
        this.teamLeagueMMR = teamLeagueMMR;
    }

    public MMR getUnrankedDraftMMR() {
        return unrankedDraftMMR;
    }

    public void setUnrankedDraftMMR(MMR unrankedDraftMMR) {
        this.unrankedDraftMMR = unrankedDraftMMR;
    }

    public void addMMRRecord(MMRSet set){
        this.mmrHistory.add(set);
    }
}
