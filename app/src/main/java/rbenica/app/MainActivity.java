package rbenica.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnCalcular;
    EditText edPeso, edAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalcular = findViewById(R.id.button);
        edPeso = findViewById(R.id.edpeso);
        edAltura = findViewById(R.id.edaltura);

        btnCalcular.setOnClickListener(v -> {
            float peso = Float.parseFloat(edPeso.getText().toString());
            float altura = Float.parseFloat(edAltura.getText().toString());
            Intent intent = new Intent(MainActivity.this, IMCResultado.class);
            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);
            startActivity(intent);
        });
    }
}
