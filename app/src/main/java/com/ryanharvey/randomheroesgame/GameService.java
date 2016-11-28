package com.ryanharvey.randomheroesgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.HEROES_BASE_URL).newBuilder();
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
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MAPS_BASE_URL).newBuilder();
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
}

