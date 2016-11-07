package com.ryanharvey.randomheroesgame.Models;

import java.util.ArrayList;

/**
 * Created by Ryan on 11/6/2016.
 */

public class Team {
    private Hero hero1 = new Hero();
    private Hero hero2 = new Hero();
    private Hero hero3 = new Hero();
    private Hero hero4 = new Hero();
    private Hero hero5 = new Hero();
    private boolean isTeamA;
    private ArrayList<Hero> heroes;



    public Team(){
//        heroes.add(hero1);
//        heroes.add(hero2);
//        heroes.add(hero3);
//        heroes.add(hero4);
//        heroes.add(hero5);
    }

    public Team(boolean isTeamA){
        this.isTeamA = isTeamA;
    }

    public Hero getHero1() {
        return hero1;
    }

    public void setHero1(String name, ArrayList<Hero> allHeroes){
        for(Hero hero : allHeroes){
            if (hero.getPrimaryName().equals(name)){
                this.hero1 = hero;
            }
        }
    }

    public Hero getHero2() {
        return hero2;
    }

    public void setHero2(String name, ArrayList<Hero> allHeroes){
        for(Hero hero : allHeroes){
            if (hero.getPrimaryName().equals(name)){
                this.hero2 = hero;
            }
        }
    }

    public Hero getHero3() {
        return hero3;
    }

    public void setHero3(String name, ArrayList<Hero> allHeroes){
        for(Hero hero : allHeroes){
            if (hero.getPrimaryName().equals(name)){
                this.hero3 = hero;
            }
        }
    }

    public Hero getHero4() {
        return hero4;
    }

    public void setHero4(String name, ArrayList<Hero> allHeroes){
        for(Hero hero : allHeroes){
            if (hero.getPrimaryName().equals(name)){
                this.hero4 = hero;
            }
        }
    }

    public Hero getHero5() {
        return hero5;
    }

    public void setHero5(String name, ArrayList<Hero> allHeroes){
        for(Hero hero : allHeroes){
            if (hero.getPrimaryName().equals(name)){
                this.hero5 = hero;
            }
        }
    }

    public ArrayList<Hero> getHeroes(){
        return this.heroes;
    }

}
