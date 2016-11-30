package com.ryanharvey.randomheroesgame.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.Services.MMRService;
import com.ryanharvey.randomheroesgame.Models.User;
import com.ryanharvey.randomheroesgame.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MMRResultsActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.userNameTextView) TextView userNameTextView;
    @BindView(R.id.teamLeagueTextView) TextView teamLeagueTextView;
    @BindView(R.id.quickMatchTextView) TextView quickMatchTextView;
    @BindView(R.id.unrankedDraftTextView) TextView unrankedDraftTextView;
    @BindView(R.id.heroLeagueTextView) TextView heroLeagueTextView;
    @BindView(R.id.mmrResultsBackButton) Button mmrResultsBackButton;

    private MMRService mmrs = new MMRService();
    private ProgressDialog dialog;
    private DatabaseReference usersDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmr_results);
        ButterKnife.bind(this);

        usersDatabaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.USERS_FIREBASE_REFERENCE);

        String name = getIntent().getStringExtra("nameInput");
        String number = getIntent().getStringExtra("numberInput");

        mmrResultsBackButton.setOnClickListener(this);

        Typeface font = Typeface.createFromAsset(getAssets(), getString(R.string.decima_font_path));
        userNameTextView.setTypeface(font);

        dialog = ProgressDialog.show(this, getString(R.string.please_wait), "", true);

        mmrs.getMMRJSON(name, number, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final User user = mmrs.processUser(response);
                final DatabaseReference userReference = usersDatabaseReference.child(user.getPlayerID());

                MMRResultsActivity.this.runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        if(user.getName() != null) {
                            userNameTextView.setText(user.getName());
                            heroLeagueTextView.setText(getString(R.string.hero_league_colon, user.getHeroLeagueMMR().getNumber()));
                            quickMatchTextView.setText(getString(R.string.quick_match_colon, user.getQuickMatchMMR().getNumber()));
                            teamLeagueTextView.setText(getString(R.string.team_league_colon, user.getTeamLeagueMMR().getNumber()));
                            unrankedDraftTextView.setText(getString(R.string.unranked_draft_colon, user.getUnrankedDraftMMR().getNumber()));
                            dialog.dismiss();

                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.user_not_found), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MMRResultsActivity.this, MMRInputActivity.class);
                            startActivity(intent);
                        }
                    }
                });

                userReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            User foundUser = dataSnapshot.getValue(User.class);
                            user.getMMRHistory().addAll(foundUser.getMMRHistory());
                        }
                        userReference.setValue(user);
                        userReference.removeEventListener(this);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == mmrResultsBackButton){
            onBackPressed();
        }
    }
}
