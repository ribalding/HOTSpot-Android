package ryanharvey.randomheroesgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class GameResultsActivity extends AppCompatActivity {

    @Bind(R.id.hero1ResultTextView) TextView hero1ResultTextView;
    @Bind(R.id.hero2ResultTextView) TextView hero2ResultTextView;
    @Bind(R.id.hero3ResultTextView) TextView hero3ResultTextView;
    @Bind(R.id.hero4ResultTextView) TextView hero4ResultTextView;
    @Bind(R.id.hero5ResultTextView) TextView hero5ResultTextView;

    private ArrayList<Hero> allHeroes;
    private ArrayList<String> allHeroNames;
    private ArrayList<GameMap> allMaps;
    private ArrayList<String> allMapNames;
    private ArrayList<Hero> teamA;
    private ArrayList<Hero> teamB;
    private GameService gs = new GameService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
        ButterKnife.bind(this);

        gs.getAllHeroes(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {}

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                allHeroes = gs.processHeroes(response);
                allHeroNames = gs.getAllHeroNames(allHeroes);
                teamA = gs.generateCompletelyRandomTeam(allHeroes);

                GameResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hero1ResultTextView.setText(teamA.get(0).getPrimaryName());
                        hero2ResultTextView.setText(teamA.get(1).getPrimaryName());
                        hero3ResultTextView.setText(teamA.get(2).getPrimaryName());
                        hero4ResultTextView.setText(teamA.get(3).getPrimaryName());
                        hero5ResultTextView.setText(teamA.get(4).getPrimaryName());
                    }
                });
            }
        });
    }
}

