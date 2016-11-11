package com.ryanharvey.randomheroesgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Models.AllHeroes;
import com.ryanharvey.randomheroesgame.Models.GameMap;
import com.ryanharvey.randomheroesgame.Models.Hero;

/**
 * Created by Ryan on 11/3/2016.
 */
public class GameService {

    public GameService(){}

    //Make An HTTP Request to retrieve the entire Heroes JSON Array
    public void getAllHeroesJSON(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.heroesBaseURL).newBuilder();
        String url = urlBuilder.build().toString();
        Request request= new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    //Convert JSON Array to Array List of Hero Objects
    public ArrayList<Hero> processHeroes(Response response){
        ArrayList<Hero> heroes = new ArrayList<>();
        try{
            JSONArray fullResultsJSONArray = new JSONArray(response.body().string());
            for (int i = 0; i < fullResultsJSONArray.length(); i++){
                JSONObject heroJSONObject = fullResultsJSONArray.getJSONObject(i);
                String name = heroJSONObject.getString("PrimaryName");
                String imageURL = heroJSONObject.getString("ImageURL");
                String attributeName = heroJSONObject.getString("AttributeName");
                String group = heroJSONObject.getString("Group");
                String subGroup = heroJSONObject.getString("SubGroup");
                Hero newHero = new Hero(name, imageURL, attributeName, group, subGroup);
                heroes.add(newHero);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return heroes;
    }

    //Make An HTTP Request to retrieve the entire Maps JSON Array
    public void getAllMaps(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.mapsBaseURL).newBuilder();
        String url = urlBuilder.build().toString();
        Request request= new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    //Convert JSON Array to Array List of Map Objects
    public ArrayList<GameMap> processMaps(Response response){
        ArrayList<GameMap> maps = new ArrayList<>();
        try{
            JSONArray fullResultsJSONArray = new JSONArray(response.body().string());
            for (int i = 0; i < fullResultsJSONArray.length(); i++){
                JSONObject heroJSONObject = fullResultsJSONArray.getJSONObject(i);
                String name = heroJSONObject.getString("PrimaryName");
                String imageURL = heroJSONObject.getString("ImageURL");
                GameMap newMap = new GameMap(name, imageURL);
                maps.add(newMap);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return maps;
    }

    //Generate team based on user selections
    public ArrayList<Hero> generateTeam(AllHeroes allHeroes, ArrayList<Hero> selectedHeroes, boolean teamRestrictive){

        ArrayList<Hero> team = new ArrayList<>();
        for(Hero hero : selectedHeroes){
            team.add(hero);
        }
        for(int i = 0; i < 5 - selectedHeroes.size(); i++){
            int randomNumber = this.generateRandomNumber(allHeroes.getAllHeroes().size());
            Hero selectedHero = allHeroes.getAllHeroes().get(randomNumber);
            if (teamRestrictive) {
                while (team.contains(selectedHero)) {
                    selectedHero = allHeroes.getAllHeroes().get(this.generateRandomNumber(allHeroes.getAllHeroes().size()));
                }
            }
            team.add(selectedHero);
        }
        return team;
    }

    //Generate Random Map
    public GameMap generateRandomMap(ArrayList<GameMap> allMaps){
        int randomNumber = this.generateRandomNumber(allMaps.size());
        return allMaps.get(randomNumber);
    }

    //Generate Random Number Between 0 and a provided int
    public int generateRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    //Get All Map Names
    public ArrayList<String> getAllMapNames(ArrayList<GameMap> maps){
        ArrayList<String> mapNames = new ArrayList<>();
        mapNames.add("None");
        for(GameMap map : maps){
            mapNames.add(map.getPrimaryName());
        }
        return mapNames;
    }

    //Get Hero By Name
    public Hero getHeroByName(String name, ArrayList<Hero> allHeroes){
        Hero newHero = new Hero();
        for(Hero hero: allHeroes){
            if(hero.getPrimaryName().equalsIgnoreCase(name)){
                newHero = hero;
            }
        }
        return newHero;
    }

    //Get Map By Name
    public GameMap getMapByName(String name, ArrayList<GameMap> allMaps){
        GameMap newMap = new GameMap();
        for (GameMap map : allMaps){
            if(map.getPrimaryName().equalsIgnoreCase(name)){
                newMap = map;
            }
        }
        return newMap;
    }

    //Not Completed
    public Hero getWeightedHero(AllHeroes allHeroes, ArrayList<Hero> team) {
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
            if (i == 0 && nums.get(i) >= 1) {
                heroFilters.add("Warrior");
            } else if (i== 1 && assassin >= 2) {
                heroFilters.add("Assassin");
            } else if (i == 2 && spec >= 1) {
                heroFilters.add("Specialist");
            } else if (i == 3 && support >= 1) {
                heroFilters.add("Support");
            }
        }

        ArrayList<Hero> filteredHeroes = allHeroes.getFilteredHeroes(heroFilters);
        return filteredHeroes.get(generateRandomNumber(filteredHeroes.size()));
        }
}

