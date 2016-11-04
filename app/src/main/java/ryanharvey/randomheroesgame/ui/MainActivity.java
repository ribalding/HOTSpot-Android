package ryanharvey.randomheroesgame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import ryanharvey.randomheroesgame.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.generateButton) Button generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        generateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == generateButton){
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }
    }
}
