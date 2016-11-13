package com.ryanharvey.randomheroesgame.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.ryanharvey.randomheroesgame.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.titleTextView) TextView titleTextView;
    @BindView(R.id.generateButton) Button generateButton;
    @BindView(R.id.findMyMMRButton) Button findMyMMRButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface fortySecondStreetFont = Typeface.createFromAsset(getAssets(), "fonts/FORTSSH_.ttf");
        titleTextView.setTypeface(fortySecondStreetFont);

        generateButton.setOnClickListener(this);
        findMyMMRButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == generateButton){
            Intent intent = new Intent(MainActivity.this, GameOptionsActivity.class);
            startActivity(intent);
        } else if (view == findMyMMRButton){
            Intent intent = new Intent (MainActivity.this, MMRInputActivity.class);
            startActivity(intent);
        }
    }
}
