package com.ryanharvey.randomheroesgame;

import android.util.Log;

import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Models.User;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
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
        String url = Constants.USER_BASE_URL + name + "_" + number;
        Request request= new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public User processUser(Response response){
        User newUser = new User();
        JSONObject quickMatchRankingsJSONObject = null;
        JSONObject heroLeagueRankingsJSONObject = null;
        JSONObject teamLeagueRankingsJSONObject = null;
        JSONObject unrankedDraftRankingsJSONObject = null;
        try {
            JSONObject userJSONObject = new JSONObject(response.body().string());
            Log.d("TESTOBJECT", userJSONObject.toString());
            JSONArray leaderboardRankingsJSONArray = userJSONObject.getJSONArray("LeaderboardRankings");
            for (int i = 0; i < leaderboardRankingsJSONArray.length(); i++) {
                JSONObject gameMode = leaderboardRankingsJSONArray.getJSONObject(i);
                if (gameMode.getString("GameMode").equals("QuickMatch")) {
                    quickMatchRankingsJSONObject = gameMode;
                } else if (gameMode.getString("GameMode").equals("HeroLeague")) {
                    heroLeagueRankingsJSONObject = gameMode;
                } else if (gameMode.getString("GameMode").equals("TeamLeague")) {
                    teamLeagueRankingsJSONObject = gameMode;
                } else if (gameMode.getString("GameMode").equals("UnrankedDraft")) {
                    unrankedDraftRankingsJSONObject = gameMode;
                }
            }

            String name = userJSONObject.getString("Name");
            String quickMatchRanking = (quickMatchRankingsJSONObject == null) ? "Not Available" : quickMatchRankingsJSONObject.get("CurrentMMR").toString();
            String heroLeagueRanking = (heroLeagueRankingsJSONObject == null) ? "Not Available" : heroLeagueRankingsJSONObject.get("CurrentMMR").toString();
            String teamLeagueRanking = (teamLeagueRankingsJSONObject == null) ? "Not Available" : teamLeagueRankingsJSONObject.get("CurrentMMR").toString();
            String unrankedDraftRanking = (unrankedDraftRankingsJSONObject == null) ? "Not Available" : unrankedDraftRankingsJSONObject.get("CurrentMMR").toString();

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
