package ryanharvey.randomheroesgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ryanharvey.randomheroesgame.Constants.Constants;
import ryanharvey.randomheroesgame.Models.GameMap;
import ryanharvey.randomheroesgame.Models.Hero;

/**
 * Created by Ryan on 11/3/2016.
 */
public class GameService {

    public GameService(){}

    //Make An HTTP Request to retrieve the entire Heroes JSON Array
    public void getAllHeroes(Callback callback){
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

    public ArrayList<Hero> generateTeam(ArrayList<Hero> allHeroes, ArrayList<Hero> selectedHeroes){
        ArrayList<Hero> team = new ArrayList<>();
        for(Hero hero : selectedHeroes){
            team.add(hero);
        }
        for(int i = 0; i < 5 - selectedHeroes.size(); i++){
            int randomNumber = this.generateRandomNumber(allHeroes.size());
            Hero selectedHero = allHeroes.get(randomNumber);
            team.add(selectedHero);
        }
        return team;
    }

    public GameMap generateRandomMap(ArrayList<GameMap> allMaps){
        int randomNumber = this.generateRandomNumber(allMaps.size());
        return allMaps.get(randomNumber);
    }

    public int generateRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public ArrayList<String> getAllHeroNames(ArrayList<Hero> heroes){
        ArrayList<String> heroNames = new ArrayList<>();
        heroNames.add("None");
        for(Hero hero : heroes){
            heroNames.add(hero.getPrimaryName());
        }
        return heroNames;
    }

    public ArrayList<String> getAllMapNames(ArrayList<GameMap> maps){
        ArrayList<String> mapNames = new ArrayList<>();
        mapNames.add("None");
        for(GameMap map : maps){
            mapNames.add(map.getPrimaryName());
        }
        return mapNames;
    }

    public Hero getHeroByName(String name, ArrayList<Hero> allHeroes){
        Hero newHero = new Hero();
        for(Hero hero: allHeroes){
            if(hero.getPrimaryName().equalsIgnoreCase(name)){
                newHero = hero;
            }
        }
        return newHero;
    }

    public GameMap getMapByName(String name, ArrayList<GameMap> allMaps){
        GameMap newMap = new GameMap();
        for (GameMap map : allMaps){
            if(map.getPrimaryName().equalsIgnoreCase(name)){
                newMap = map;
            }
        }
        return newMap;
    }
}
