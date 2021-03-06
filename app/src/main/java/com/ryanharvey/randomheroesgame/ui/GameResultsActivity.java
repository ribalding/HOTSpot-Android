package com.ryanharvey.randomheroesgame.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.ryanharvey.randomheroesgame.Models.AllGameMaps;
import com.ryanharvey.randomheroesgame.Models.AllHeroes;

import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Models.GameRoster;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.ryanharvey.randomheroesgame.Services.GameService;
import com.ryanharvey.randomheroesgame.Models.GameMap;
import com.ryanharvey.randomheroesgame.Models.Hero;
import com.ryanharvey.randomheroesgame.R;

public class GameResultsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.hero1ResultTextView) TextView hero1ResultTextView;
    @BindView(R.id.hero2ResultTextView) TextView hero2ResultTextView;
    @BindView(R.id.hero3ResultTextView) TextView hero3ResultTextView;
    @BindView(R.id.hero4ResultTextView) TextView hero4ResultTextView;
    @BindView(R.id.hero5ResultTextView) TextView hero5ResultTextView;
    @BindView(R.id.hero6ResultTextView) TextView hero6ResultTextView;
    @BindView(R.id.hero7ResultTextView) TextView hero7ResultTextView;
    @BindView(R.id.hero8ResultTextView) TextView hero8ResultTextView;
    @BindView(R.id.hero9ResultTextView) TextView hero9ResultTextView;
    @BindView(R.id.hero10ResultTextView) TextView hero10ResultTextView;

    @BindView((R.id.hero1Image)) ImageView hero1Image;
    @BindView((R.id.hero2Image)) ImageView hero2Image;
    @BindView((R.id.hero3Image)) ImageView hero3Image;
    @BindView((R.id.hero4Image)) ImageView hero4Image;
    @BindView((R.id.hero5Image)) ImageView hero5Image;
    @BindView((R.id.hero6Image)) ImageView hero6Image;
    @BindView((R.id.hero7Image)) ImageView hero7Image;
    @BindView((R.id.hero8Image)) ImageView hero8Image;
    @BindView((R.id.hero9Image)) ImageView hero9Image;
    @BindView((R.id.hero10Image)) ImageView hero10Image;

    @BindView(R.id.mapResultTextView) TextView mapResultTextView;
    @BindView(R.id.mapImageView) ImageView mapImageView;

    @BindView(R.id.teamAResultsTextView) TextView teamAResultsTextView;
    @BindView(R.id.teamBResultsTextView) TextView teamBResultsTextView;

    private AllHeroes allHeroes = new AllHeroes();
    private AllGameMaps allMaps;
    private ArrayList<Hero> teamA;
    private ArrayList<Hero> teamB;
    private GameMap selectedMap;
    private GameService gs = new GameService();
    private GameRoster gameRoster;
    private ArrayList<View> viewsList = new ArrayList<>();
    private ArrayList<String> teamAChoices = new ArrayList<>();
    private ArrayList<String> teamBChoices = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private boolean teamRestrictionIsOn;
    private boolean globalRestrictionIsOn;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        teamRestrictionIsOn = sharedPreferences.getBoolean(Constants.PREFERENCES_TEAM_RESTRICTIVE, true);
        globalRestrictionIsOn = sharedPreferences.getBoolean(Constants.PREFERENCES_GLOBAL_RESTRICTIVE, false);

        Typeface font = Typeface.createFromAsset(getAssets(), getString(R.string.decima_font_path));
        mapResultTextView.setTypeface(font);
        teamAResultsTextView.setTypeface(font);
        teamBResultsTextView.setTypeface(font);

        dialog = ProgressDialog.show(this, getString(R.string.generating_game), "",true);

        hero1Image.setOnClickListener(this);
        hero2Image.setOnClickListener(this);
        hero3Image.setOnClickListener(this);
        hero4Image.setOnClickListener(this);
        hero5Image.setOnClickListener(this);
        hero6Image.setOnClickListener(this);
        hero7Image.setOnClickListener(this);
        hero8Image.setOnClickListener(this);
        hero9Image.setOnClickListener(this);
        hero10Image.setOnClickListener(this);

        View[] views = new View[] {
                hero1Image, hero2Image, hero3Image, hero4Image, hero5Image,
                hero6Image, hero7Image, hero8Image, hero9Image, hero10Image
        };
        viewsList.addAll(Arrays.asList(views));

        teamAChoices.add(sharedPreferences.getString("heroSpinner1Choice", Constants.NONE));
        teamAChoices.add(sharedPreferences.getString("heroSpinner2Choice", Constants.NONE));
        teamAChoices.add(sharedPreferences.getString("heroSpinner3Choice", Constants.NONE));
        teamAChoices.add(sharedPreferences.getString("heroSpinner4Choice", Constants.NONE));
        teamAChoices.add(sharedPreferences.getString("heroSpinner5Choice", Constants.NONE));
        teamBChoices.add(sharedPreferences.getString("heroSpinner6Choice", Constants.NONE));
        teamBChoices.add(sharedPreferences.getString("heroSpinner7Choice", Constants.NONE));
        teamBChoices.add(sharedPreferences.getString("heroSpinner8Choice", Constants.NONE));
        teamBChoices.add(sharedPreferences.getString("heroSpinner9Choice", Constants.NONE));
        teamBChoices.add(sharedPreferences.getString("heroSpinner10Choice", Constants.NONE));

        gs.getAllHeroesJSON(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allHeroes.setAllHeroes(gs.processHeroes(response));

                teamA = allHeroes.generateTeam(teamAChoices, teamRestrictionIsOn);
                teamB = allHeroes.generateTeam(teamBChoices, teamRestrictionIsOn);

                if (globalRestrictionIsOn) {
                    allHeroes.globalRestrict(teamA, teamB);
                }

                gameRoster = new GameRoster(teamA, teamB);

                GameResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTeamTextViews();
                        setTeamImageViews();
                        dialog.dismiss();
                    }
                });
            }
        });

        gs.getAllMaps(new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mapName = sharedPreferences.getString("mapSpinnerChoice", Constants.NONE);
                allMaps = new AllGameMaps(gs.processMaps(response));
                if(!mapName.equalsIgnoreCase(Constants.NONE)){
                    selectedMap = allMaps.getMapByName(mapName);
                } else {
                    selectedMap = allMaps.generateRandomMap();
                }
                GameResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setMapResultTextView();
                        setMapImageView();

                    }
                });
            }
        });
    }

    public void setTeamTextViews(){
            hero1ResultTextView.setText(teamA.get(0).getPrimaryName());
            hero2ResultTextView.setText(teamA.get(1).getPrimaryName());
            hero3ResultTextView.setText(teamA.get(2).getPrimaryName());
            hero4ResultTextView.setText(teamA.get(3).getPrimaryName());
            hero5ResultTextView.setText(teamA.get(4).getPrimaryName());
            hero6ResultTextView.setText(teamB.get(0).getPrimaryName());
            hero7ResultTextView.setText(teamB.get(1).getPrimaryName());
            hero8ResultTextView.setText(teamB.get(2).getPrimaryName());
            hero9ResultTextView.setText(teamB.get(3).getPrimaryName());
            hero10ResultTextView.setText(teamB.get(4).getPrimaryName());
    }

    public void setTeamImageViews() {

        Picasso.with(this).load(teamA.get(0).getImageURL()).resize(160, 160).into(hero1Image);
        Picasso.with(this).load(teamA.get(1).getImageURL()).resize(160, 160).into(hero2Image);
        Picasso.with(this).load(teamA.get(2).getImageURL()).resize(160, 160).into(hero3Image);
        Picasso.with(this).load(teamA.get(3).getImageURL()).resize(160, 160).into(hero4Image);
        Picasso.with(this).load(teamA.get(4).getImageURL()).resize(160, 160).into(hero5Image);

        Picasso.with(this).load(teamB.get(0).getImageURL()).resize(160, 160).into(hero6Image);
        Picasso.with(this).load(teamB.get(1).getImageURL()).resize(160, 160).into(hero7Image);
        Picasso.with(this).load(teamB.get(2).getImageURL()).resize(160, 160).into(hero8Image);
        Picasso.with(this).load(teamB.get(3).getImageURL()).resize(160, 160).into(hero9Image);
        Picasso.with(this).load(teamB.get(4).getImageURL()).resize(160, 160).into(hero10Image);

    }

    public void setMapImageView(){
        Picasso.with(this).load(selectedMap.getImageURL()).into(mapImageView);
    }

    public void setMapResultTextView(){
        mapResultTextView.setText(selectedMap.getPrimaryName());
    }


    @Override
    public void onClick(View view) {
        if (viewsList.contains(view)) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(gameRoster.getRoster().get(viewsList.indexOf(view)).getHotsLogsURI()));
            startActivity(i);
        }
    }
}

