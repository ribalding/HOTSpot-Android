package com.ryanharvey.randomheroesgame.Models;

/**
 * Created by Ryan on 11/29/2016.
 */

public class MMRSet {
    private MMR heroLeague;
    private MMR teamLeague;
    private MMR quickMatch;
    private MMR unrankedDraft;
    private String dateTime;

    public MMRSet(){}

    public MMRSet(MMR hl, MMR tl, MMR qm, MMR ud, String dateTime){
        this.heroLeague = hl;
        this.teamLeague = tl;
        this.quickMatch = qm;
        this.unrankedDraft = ud;
        this.dateTime = dateTime;

    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public MMR getHeroLeague() {
        return heroLeague;
    }

    public void setHeroLeague(MMR heroLeague) {
        this.heroLeague = heroLeague;
    }

    public MMR getTeamLeague() {
        return teamLeague;
    }

    public void setTeamLeague(MMR teamLeague) {
        this.teamLeague = teamLeague;
    }

    public MMR getQuickMatch() {
        return quickMatch;
    }

    public void setQuickMatch(MMR quickMatch) {
        this.quickMatch = quickMatch;
    }

    public MMR getUnrankedDraft() {
        return unrankedDraft;
    }

    public void setUnrankedDraft(MMR unrankedDraft) {
        this.unrankedDraft = unrankedDraft;
    }
}
