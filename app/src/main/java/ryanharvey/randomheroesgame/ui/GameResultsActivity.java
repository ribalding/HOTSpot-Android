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

    @Bind(R.id.hero6ResultTextView) TextView hero6ResultTextView;
    @Bind(R.id.hero7ResultTextView) TextView hero7ResultTextView;
    @Bind(R.id.hero8ResultTextView) TextView hero8ResultTextView;
    @Bind(R.id.hero9ResultTextView) TextView hero9ResultTextView;
    @Bind(R.id.hero10ResultTextView) TextView hero10ResultTextView;

    @Bind(R.id.mapResultTextView) TextView mapResultTextView;

    private ArrayList<Hero> allHeroes;
    private ArrayList<GameMap> allMaps;
    private ArrayList<Hero> teamA;
    private ArrayList<Hero> teamB;
    private GameMap selectedMap;
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
                teamA = gs.generateCompletelyRandomTeam(allHeroes);
                teamB = gs.generateCompletelyRandomTeam(allHeroes);

                GameResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    setTeamTextViews();
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
                selectedMap = gs.generateRandomMap(allMaps);
                setMapResultTextView();
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

    public void setMapResultTextView(){
        mapResultTextView.setText(selectedMap.getPrimaryName());
    }
}

