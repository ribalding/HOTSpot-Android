package com.ryanharvey.randomheroesgame.Models;

import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Services.MathService;

import java.util.ArrayList;

/**
 * Created by Ryan on 11/27/2016.
 */

public class AllGameMaps {
    private ArrayList<GameMap> allMaps = new ArrayList<>();
    public AllGameMaps(){}

    public AllGameMaps(ArrayList<GameMap> allMaps){
        this.allMaps = allMaps;
    }

    public ArrayList<GameMap> getAllMaps() {
        return allMaps;
    }

    public void setAllMaps(ArrayList<GameMap> allMaps) {
        this.allMaps = allMaps;
    }

    //Get Map By Name
    public  GameMap getMapByName(String name){
        GameMap newMap = new GameMap();
        for (GameMap map : this.allMaps){
            if(map.getPrimaryName().equalsIgnoreCase(name)){
                newMap = map;
            }
        }
        return newMap;
    }

    //Generate Random Map
    public GameMap generateRandomMap(){
        int randomNumber =  MathService.generateRandomNumber(this.allMaps.size());
        return allMaps.get(randomNumber);
    }

    //Get All Map Names
    public ArrayList<String> getAllMapNames(){
        ArrayList<String> mapNames = new ArrayList<>();
        mapNames.add(Constants.NONE);
        for(GameMap map : this.allMaps){
            mapNames.add(map.getPrimaryName());
        }
        return mapNames;
    }

}
