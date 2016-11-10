package com.ryanharvey.randomheroesgame.Models;

import java.util.ArrayList;

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

}
