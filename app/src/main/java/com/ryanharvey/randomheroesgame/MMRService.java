package com.ryanharvey.randomheroesgame;

import android.util.Log;

import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ryan on 11/8/2016.
 */

public class MMRService {
    public MMRService(){}

    public void getMMRJSON(String name, String number, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = Constants.userBaseURL + name + "_" + number;
        Request request= new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public User processUser(Response response){
        User newUser = new User();
        try {
            JSONObject userJSONObject = new JSONObject(response.body().string());
            Log.d("TESTOBJECT", userJSONObject.toString());
            JSONArray leaderboardRankingsJSONArray = userJSONObject.getJSONArray("LeaderboardRankings");
            JSONObject quickMatchRankingsJSONObject = leaderboardRankingsJSONArray.getJSONObject(0);
            JSONObject heroLeagueRankingsJSONObject = leaderboardRankingsJSONArray.getJSONObject(1);
            JSONObject teamLeagueRankingsJSONObject = leaderboardRankingsJSONArray.getJSONObject(2);
            JSONObject unrankedDraftRankingsJSONObject = leaderboardRankingsJSONArray.getJSONObject(3);


            String name = userJSONObject.getString("Name");
            String quickMatchRanking = quickMatchRankingsJSONObject.get("CurrentMMR").toString();
            String heroLeagueRanking = heroLeagueRankingsJSONObject.get("CurrentMMR").toString();
            String teamLeagueRanking = teamLeagueRankingsJSONObject.get("CurrentMMR").toString();
            String unrankedDraftRanking = unrankedDraftRankingsJSONObject.get("CurrentMMR").toString();

            newUser.setName(name);
            newUser.setQuickMatchMMR(quickMatchRanking);
            newUser.setHeroLeagueMMR(heroLeagueRanking);
            newUser.setTeamLeagueMMR(teamLeagueRanking);
            newUser.setUnrankedDraftMMR(unrankedDraftRanking);

        } catch (Exception e){
            e.printStackTrace();
        }

        return newUser;
    }
}
