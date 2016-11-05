package ryanharvey.randomheroesgame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import ryanharvey.randomheroesgame.GameService;
import ryanharvey.randomheroesgame.Models.GameMap;
import ryanharvey.randomheroesgame.Models.Hero;
import ryanharvey.randomheroesgame.R;

public class GameOptionsActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.heroSpinner1) Spinner heroSpinner1;
    @Bind(R.id.heroSpinner2) Spinner heroSpinner2;
    @Bind(R.id.heroSpinner3) Spinner heroSpinner3;
    @Bind(R.id.heroSpinner4) Spinner heroSpinner4;
    @Bind(R.id.heroSpinner5) Spinner heroSpinner5;
    @Bind(R.id.heroSpinner6) Spinner heroSpinner6;
    @Bind(R.id.heroSpinner7) Spinner heroSpinner7;
    @Bind(R.id.heroSpinner8) Spinner heroSpinner8;
    @Bind(R.id.heroSpinner9) Spinner heroSpinner9;
    @Bind(R.id.heroSpinner10) Spinner heroSpinner10;
    @Bind(R.id.mapSelectSpinner) Spinner mapSelectSpinner;
    @Bind(R.id.submitButton) Button submitButton;

    private GameService gs = new GameService();
    private ArrayList<Hero> allHeroes;
    private ArrayList<String> allHeroNames;
    private ArrayList<GameMap> allMaps;
    private ArrayList<String> allMapNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);
        ButterKnife.bind(this);
        submitButton.setOnClickListener(this);


        gs.getAllHeroes(new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allHeroes = gs.processHeroes(response);
                allHeroNames = gs.getAllHeroNames(allHeroes);
                final ArrayAdapter<String> heroSpinnerAdapter = new ArrayAdapter<>(GameOptionsActivity.this, android.R.layout.simple_spinner_dropdown_item, allHeroNames);

                GameOptionsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        heroSpinner1.setAdapter(heroSpinnerAdapter);
                        heroSpinner2.setAdapter(heroSpinnerAdapter);
                        heroSpinner3.setAdapter(heroSpinnerAdapter);
                        heroSpinner4.setAdapter(heroSpinnerAdapter);
                        heroSpinner5.setAdapter(heroSpinnerAdapter);
                        heroSpinner6.setAdapter(heroSpinnerAdapter);
                        heroSpinner7.setAdapter(heroSpinnerAdapter);
                        heroSpinner8.setAdapter(heroSpinnerAdapter);
                        heroSpinner9.setAdapter(heroSpinnerAdapter);
                        heroSpinner10.setAdapter(heroSpinnerAdapter);
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
                allMapNames = gs.getAllMapNames(allMaps);
                final ArrayAdapter<String> mapSpinnerAdapter = new ArrayAdapter<>(GameOptionsActivity.this, android.R.layout.simple_spinner_dropdown_item, allMapNames);

                GameOptionsActivity.this.runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        mapSelectSpinner.setAdapter(mapSpinnerAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == submitButton){
            Intent intent = new Intent(GameOptionsActivity.this, GameResultsActivity.class);
            startActivity(intent);
        }
    }
}
