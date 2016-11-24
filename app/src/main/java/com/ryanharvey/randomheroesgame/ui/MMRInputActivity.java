package com.ryanharvey.randomheroesgame.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.ryanharvey.randomheroesgame.Constants.Constants;
import com.ryanharvey.randomheroesgame.R;

public class MMRInputActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView (R.id.mmrSubmitButton) Button mmrSubmitButton;
    @BindView (R.id.nameInputEditText) EditText nameInputEditText;
    @BindView (R.id.numberInputEditText) EditText numberInputEditText;
    @BindView(R.id.mmrInfoLink) TextView mmrInfoLink;
    @BindView (R.id.findMyMMRTextView) TextView findMyMMRTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmrinput);
        ButterKnife.bind(this);

        mmrSubmitButton.setOnClickListener(this);
        mmrInfoLink.setOnClickListener(this);

        Typeface fortySecondStreetFont = Typeface.createFromAsset(getAssets(), getString(R.string.font_path));
        findMyMMRTextView.setTypeface(fortySecondStreetFont);

    }

    @Override
    public void onClick(View view) {
        if(view == mmrSubmitButton){
            if(isValidName(nameInputEditText.getText().toString())) {
                Intent intent = new Intent(MMRInputActivity.this, MMRResultsActivity.class);
                intent.putExtra("nameInput", nameInputEditText.getText().toString());
                intent.putExtra("numberInput", numberInputEditText.getText().toString());
                startActivity(intent);
            }
        } else if (view == mmrInfoLink) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.MMR_INFO_REDDIT_ARTICLE_LINK));
            startActivity(browserIntent);
        }
    }

    public boolean isValidName(String name){
        return !name.equalsIgnoreCase("");
    }
}
