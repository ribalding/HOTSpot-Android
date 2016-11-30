package com.ryanharvey.randomheroesgame.Models;

import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Services.MathService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Ryan on 11/9/2016.
 */

public class AllHeroes {

    private ArrayList<Hero> allHeroesArrayList;

    public AllHeroes(){}

    public AllHeroes(ArrayList<Hero> heroes){
        this.allHeroesArrayList = heroes;
    }

    public ArrayList<Hero> getAllHeroes() {
        return allHeroesArrayList;
    }

    public void setAllHeroes(ArrayList<Hero> allHeroesArrayList) {
        this.allHeroesArrayList = allHeroesArrayList;
    }

    public ArrayList<Hero> getAllWarriors(){
        ArrayList<Hero> warriors = new ArrayList<>();
        for(Hero hero: this.allHeroesArrayList){
            if(hero.getGroup().equalsIgnoreCase("Warrior")){
                warriors.add(hero);
            }
        }
        return warriors;
    }

    public ArrayList<Hero> getAllSupports(){
        ArrayList<Hero> supports = new ArrayList<>();
        for(Hero hero: this.allHeroesArrayList){
            if(hero.getGroup().equalsIgnoreCase("Support")){
                supports.add(hero);
            }
        }
        return supports;
    }

    public ArrayList<Hero> getAllSpecialists(){
        ArrayList<Hero> specialists = new ArrayList<>();
        for(Hero hero: this.allHeroesArrayList){
            if(hero.getGroup().equalsIgnoreCase("Specialist")){
                specialists.add(hero);
            }
        }
        return specialists;
    }

    public ArrayList<Hero> getAllAssassins(){
        ArrayList<Hero> assassins = new ArrayList<>();
        for(Hero hero: this.allHeroesArrayList){
            if(hero.getGroup().equalsIgnoreCase("Assassin")){
                assassins.add(hero);
            }
        }
        return assassins;
    }

    //Get All Hero Names
    public ArrayList<String> getAllHeroNames(){
        ArrayList<String> heroNames = new ArrayList<>();
        heroNames.add("None");
        for(Hero hero : this.allHeroesArrayList){
            heroNames.add(hero.getPrimaryName());
        }
        return heroNames;
    }

    //Get Filtered Heroes
    private ArrayList<Hero> getFilteredHeroes(ArrayList<String> filters){

        ArrayList<Hero> filteredHeroes = new ArrayList<>();
        for(Hero hero : this.allHeroesArrayList){
            Boolean filterCheck = false;
            for(String filter : filters){
                if (hero.getGroup().equalsIgnoreCase(filter)){
                    filterCheck = true;
                }
            }
            if(!filterCheck){
                filteredHeroes.add(hero);
            }
        }
        return filteredHeroes;
    }

    //Get Hero By Name
    private Hero getHeroByName(String name){
        Hero newHero = new Hero();
        for(Hero hero: this.allHeroesArrayList){
            if(hero.getPrimaryName().equalsIgnoreCase(name)){
                newHero = hero;
            }
        }
        return newHero;
    }

    //Generate team based on user selections
    public ArrayList<Hero> generateTeam(ArrayList<String> selectedHeroNames, boolean teamRestrictive){

        ArrayList<Hero> selectedHeroes = this.generateSelectedHeroes(selectedHeroNames);
        ArrayList<Hero> team = new ArrayList<>();
        team.addAll(selectedHeroes);

        for(int i = 0; i < 5 - selectedHeroes.size(); i++){
            Hero selectedHero = this.getWeightedHero(team);
            if (teamRestrictive) {
                while (team.contains(selectedHero)) {
                    selectedHero = this.getWeightedHero(team);
                }
            }
            team.add(selectedHero);
        }
        return team;
    }

    private Hero getWeightedHero(ArrayList<Hero> team) {
        Integer warrior = 0;
        Integer assassin = 0;
        Integer spec = 0;
        Integer support = 0;

        for (Hero hero : team) {
            if (hero.getGroup().equalsIgnoreCase("Warrior")) {
                warrior++;
            } else if (hero.getGroup().equalsIgnoreCase("Assassin")) {
                assassin++;
            } else if (hero.getGroup().equalsIgnoreCase("Specialist")) {
                spec++;
            } else if (hero.getGroup().equalsIgnoreCase("Support")) {
                support++;
            }
        }

        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(warrior, assassin, spec, support));
        ArrayList<String> heroFilters = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            if (i == 0 && nums.get(i) > 1) {
                heroFilters.add("Warrior");
            } else if (i== 1 && assassin > 1) {
                heroFilters.add("Assassin");
            } else if (i == 2 && spec > 0) {
                heroFilters.add("Specialist");
            } else if (i == 3 && support > 1) {
                heroFilters.add("Support");
            }
        }

        ArrayList<Hero> filteredHeroes = this.getFilteredHeroes(heroFilters);
        if(filteredHeroes.size() > 0) {
            return filteredHeroes.get(MathService.generateRandomNumber(filteredHeroes.size() - 1));
        } else {
            return this.getAllHeroes().get(MathService.generateRandomNumber(this.getAllHeroes().size() - 1));
        }
    }

    //Replace Matching Heroes
    private ArrayList<Hero> replaceMatchingHeroes (ArrayList<Hero> team, ArrayList<Hero> otherTeam) {
        team.removeAll(otherTeam);
        while (team.size() < 5) {
            Hero newHero = this.getWeightedHero(team);
            team.add(newHero);
            team.removeAll(otherTeam);
        }
        return team;
    }

    //Global Restrict
    public void globalRestrict(ArrayList<Hero> teamA, ArrayList<Hero> teamB) {
        if (!Collections.disjoint(teamA, teamB)) {
            int coinFlip = MathService.generateRandomNumber(2);
            if (coinFlip == 0) {
                replaceMatchingHeroes(teamA, teamB);
            } else {
                replaceMatchingHeroes(teamB, teamA);
            }
        }
    }

    private ArrayList<Hero> generateSelectedHeroes(ArrayList<String> choices){
        ArrayList<Hero> selectedHeroes = new ArrayList<>();
        for (String choice : choices){
            if(!choice.equalsIgnoreCase(Constants.NONE)){
                selectedHeroes.add(this.getHeroByName(choice));
            }
        }
        return selectedHeroes;
    }
}
