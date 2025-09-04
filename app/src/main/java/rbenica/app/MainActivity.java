package rbenica.app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText editTextMin, editTextMax;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editTextMin = findViewById(R.id.edMin);
        editTextMax = findViewById(R.id.edMax2);
        tv = findViewById(R.id.tv);

        button.setOnClickListener(v -> {
            int min = Integer.parseInt(editTextMin.getText().toString());
            int max = Integer.parseInt(editTextMax.getText().toString());
            int sorteado = 0;
            Random random = new Random();
            sorteado = (int) (Math.random() * (max - min) + min);

            tv.setText(Integer.toString(sorteado));
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("Sorteado", tv.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
            tv.setText(savedInstanceState.getString("Sorteado"));
        }
    }
}


