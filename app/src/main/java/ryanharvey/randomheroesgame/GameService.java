package ryanharvey.randomheroesgame;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ryanharvey.randomheroesgame.Constants.Constants;
import ryanharvey.randomheroesgame.Models.Hero;

/**
 * Created by Ryan on 11/3/2016.
 */
public class GameService {

    public GameService(){}

    public void getAllHeroes(Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.heroesBaseURL).newBuilder();
        String url = urlBuilder.build().toString();
        Request request= new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

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
}
