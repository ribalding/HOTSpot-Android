package com.ryanharvey.randomheroesgame.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ryanharvey.randomheroesgame.MMRService;
import com.ryanharvey.randomheroesgame.Models.User;
import com.ryanharvey.randomheroesgame.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MMRResultsActivity extends AppCompatActivity {

    @BindView(R.id.userNameTextView) TextView userNameTextView;
    @BindView(R.id.teamLeagueTextView) TextView teamLeagueTextView;
    @BindView(R.id.quickMatchTextView) TextView quickMatchTextView;
    @BindView(R.id.unrankedDraftTextView) TextView unrankedDraftTextView;
    @BindView(R.id.heroLeagueTextView) TextView heroLeagueTextView;

    private MMRService mmrs = new MMRService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmr_results);
        ButterKnife.bind(this);

        String name = getIntent().getStringExtra("nameInput");
        String number = getIntent().getStringExtra("numberInput");

        mmrs.getMMRJSON(name, number, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final User user = mmrs.processUser(response);
                MMRResultsActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        userNameTextView.setText(user.getName());
                        heroLeagueTextView.setText("Hero League: " + user.getHeroLeagueMMR());
                        quickMatchTextView.setText("Quick Match: " + user.getQuickMatchMMR());
                        teamLeagueTextView.setText("Team League: " + user.getTeamLeagueMMR());
                        unrankedDraftTextView.setText("Unranked Draft: " + user.getUnrankedDraftMMR());
                    }
                });
            }
        });
    }
}
