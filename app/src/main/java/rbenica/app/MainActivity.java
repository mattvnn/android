package rbenica.app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    EditText editTextMin, editTextMax;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        editTextMin=findViewById(R.id.edMin);
        editTextMax=findViewById(R.id.edMax2);
        textViewResult=findViewById(R.id.textviewresult);

        button.setOnClickListener(v -> {
            int min=Integer.parseInt( editTextMin.getText().toString() );
            int max=Integer.parseInt( editTextMax.getText().toString() );
            int sorteado=0;
            Random random = new Random();
            sorteado = (int) (Math.random()*(max-min)+min);

            textViewResult.setText(Integer.toString(sorteado));
                });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}