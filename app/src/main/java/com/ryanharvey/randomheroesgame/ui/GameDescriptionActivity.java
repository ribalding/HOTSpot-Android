package com.ryanharvey.randomheroesgame.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ryanharvey.randomheroesgame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameDescriptionActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView (R.id.gameGeneratorTitleTextView) TextView gameGeneratorTitleTextView;
    @BindView (R.id.gameDescriptionContinueButton) Button gameDescriptionContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_description);
        ButterKnife.bind(this);

        gameDescriptionContinueButton.setOnClickListener(this);

        Typeface descriptionLogoFont = Typeface.createFromAsset(getAssets(), getString(R.string.decima_font_path));
        gameGeneratorTitleTextView.setTypeface(descriptionLogoFont);
    }

    @Override
    public void onClick(View view) {
        if(view == gameDescriptionContinueButton){
            Intent intent = new Intent(GameDescriptionActivity.this, GameOptionsActivity.class);
            startActivity(intent);
        }
    }
}
