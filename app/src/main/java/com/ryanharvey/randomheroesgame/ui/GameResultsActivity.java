package com.ryanharvey.randomheroesgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ryanharvey.randomheroesgame.Models.AllHeroes;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import com.ryanharvey.randomheroesgame.GameService;
import com.ryanharvey.randomheroesgame.Models.GameMap;
import com.ryanharvey.randomheroesgame.Models.Hero;
import com.ryanharvey.randomheroesgame.R;

public class GameResultsActivity extends AppCompatActivity {

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

    @BindView((R.id.hero1Image)) ImageButton hero1Image;
    @BindView((R.id.hero2Image)) ImageButton hero2Image;
    @BindView((R.id.hero3Image)) ImageButton hero3Image;
    @BindView((R.id.hero4Image)) ImageButton hero4Image;
    @BindView((R.id.hero5Image)) ImageButton hero5Image;
    @BindView((R.id.hero6Image)) ImageButton hero6Image;
    @BindView((R.id.hero7Image)) ImageButton hero7Image;
    @BindView((R.id.hero8Image)) ImageButton hero8Image;
    @BindView((R.id.hero9Image)) ImageButton hero9Image;
    @BindView((R.id.hero10Image)) ImageButton hero10Image;

    @BindView(R.id.mapResultTextView) TextView mapResultTextView;

    private AllHeroes allHeroes = new AllHeroes();
    private ArrayList<GameMap> allMaps;
    private ArrayList<Hero> teamA;
    private ArrayList<Hero> teamB;
    private GameMap selectedMap;
    private GameService gs = new GameService();

    private ArrayList<String> teamAChoices = new ArrayList<>();
    private ArrayList<String> teamBChoices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
        ButterKnife.bind(this);

        teamAChoices.add(getIntent().getStringExtra("heroSpinner1Choice"));
        teamAChoices.add(getIntent().getStringExtra("heroSpinner2Choice"));
        teamAChoices.add(getIntent().getStringExtra("heroSpinner3Choice"));
        teamAChoices.add(getIntent().getStringExtra("heroSpinner4Choice"));
        teamAChoices.add(getIntent().getStringExtra("heroSpinner5Choice"));
        teamBChoices.add(getIntent().getStringExtra("heroSpinner6Choice"));
        teamBChoices.add(getIntent().getStringExtra("heroSpinner7Choice"));
        teamBChoices.add(getIntent().getStringExtra("heroSpinner8Choice"));
        teamBChoices.add(getIntent().getStringExtra("heroSpinner9Choice"));
        teamBChoices.add(getIntent().getStringExtra("heroSpinner10Choice"));

        gs.getAllHeroesJSON(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allHeroes.setAllHeroes(gs.processHeroes(response));

                    teamA = gs.generateTeam(allHeroes.getAllHeroes(), generateSelectedHeroes(teamAChoices));
                    teamB = gs.generateTeam(allHeroes.getAllHeroes(), generateSelectedHeroes(teamBChoices));

                GameResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setTeamTextViews();
                        setTeamImageViews();
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
                allMaps = gs.processMaps(response);
                if(!getIntent().getStringExtra("mapSpinnerChoice").equalsIgnoreCase("None")){
                    selectedMap = gs.getMapByName(getIntent().getStringExtra("mapSpinnerChoice"), allMaps);
                } else {
                    selectedMap = gs.generateRandomMap(allMaps);
                }
                GameResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setMapResultTextView();

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

        Picasso.with(this).load(teamA.get(0).getImageURL()).into(hero1Image);
        Picasso.with(this).load(teamA.get(1).getImageURL()).into(hero2Image);
        Picasso.with(this).load(teamA.get(2).getImageURL()).into(hero3Image);
        Picasso.with(this).load(teamA.get(3).getImageURL()).into(hero4Image);
        Picasso.with(this).load(teamA.get(4).getImageURL()).into(hero5Image);

        Picasso.with(this).load(teamB.get(0).getImageURL()).into(hero6Image);
        Picasso.with(this).load(teamB.get(1).getImageURL()).into(hero7Image);
        Picasso.with(this).load(teamB.get(2).getImageURL()).into(hero8Image);
        Picasso.with(this).load(teamB.get(3).getImageURL()).into(hero9Image);
        Picasso.with(this).load(teamB.get(4).getImageURL()).into(hero10Image);
    }

    public void setMapResultTextView(){
        mapResultTextView.setText(selectedMap.getPrimaryName());
    }

    public ArrayList<Hero> generateSelectedHeroes(ArrayList<String> choices){
        ArrayList<Hero> selectedHeroes = new ArrayList<>();
        for (String choice : choices){
            if(!choice.equalsIgnoreCase("None")){
                selectedHeroes.add(gs.getHeroByName(choice, allHeroes.getAllHeroes()));
            }
        }
        return selectedHeroes;
    }
}

