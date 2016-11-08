package com.ryanharvey.randomheroesgame.Models;

/**
 * Created by Ryan on 11/8/2016.
 */

public class User {
    private String playerID;
    private String name;
    private String quickMatchMMR;
    private String heroLeagueMMR;
    private String teamLeagueMMR;
    private String unrankedDraftMMR;

    public User(){}

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

    public String getQuickMatchMMR() {
        return quickMatchMMR;
    }

    public void setQuickMatchMMR(String quickMatchMMR) {
        this.quickMatchMMR = quickMatchMMR;
    }

    public String getHeroLeagueMMR() {
        return heroLeagueMMR;
    }

    public void setHeroLeagueMMR(String heroLeagueMMR) {
        this.heroLeagueMMR = heroLeagueMMR;
    }

    public String getTeamLeagueMMR() {
        return teamLeagueMMR;
    }

    public void setTeamLeagueMMR(String teamLeagueMMR) {
        this.teamLeagueMMR = teamLeagueMMR;
    }

    public String getUnrankedDraftMMR() {
        return unrankedDraftMMR;
    }

    public void setUnrankedDraftMMR(String unrankedDraftMMR) {
        this.unrankedDraftMMR = unrankedDraftMMR;
    }
}
