package ryanharvey.randomheroesgame.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import ryanharvey.randomheroesgame.R;

public class MMRInputActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView (R.id.mmrSubmitButton) Button mmrSubmitButton;
    @BindView (R.id.nameInputEditText) EditText nameInputEditText;
    @BindView (R.id.numberInputEditText) EditText numberInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmrinput);
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mmrSubmitButton){
            if(isValidName(nameInputEditText.getText().toString())) {
                Intent intent = new Intent(MMRInputActivity.this, mmrResultsActivity.class);
                intent.putExtra("nameInput", nameInputEditText.getText().toString());
                intent.putExtra("numberInput", numberInputEditText.getText().toString());
                startActivity(intent);
            }
        }
    }

    public boolean isValidName(String name){
        return !name.equalsIgnoreCase("");
    }
}
