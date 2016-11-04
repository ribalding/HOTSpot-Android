package ryanharvey.randomheroesgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import ryanharvey.randomheroesgame.GameService;
import ryanharvey.randomheroesgame.Models.Hero;
import ryanharvey.randomheroesgame.R;

public class GameOptionsActivity extends AppCompatActivity {

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

    private GameService gs = new GameService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);
        gs.getAllHeroes(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ArrayList<Hero> heroes = gs.processHeroes(response);
                ArrayList<Hero> team = gs.generateRandomTeam(heroes);
                for(int i = 0; i < team.size(); i++){
                    Log.d("TEST", team.get(i).getPrimaryName());
                }
            }
        });
    }
}
