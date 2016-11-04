package ryanharvey.randomheroesgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import ryanharvey.randomheroesgame.GameService;
import ryanharvey.randomheroesgame.Models.Hero;
import ryanharvey.randomheroesgame.R;

public class GameActivity extends AppCompatActivity {

    private GameService gs = new GameService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
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
