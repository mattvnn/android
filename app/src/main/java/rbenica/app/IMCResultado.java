package rbenica.app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

    public class IMCResultado extends AppCompatActivity {

    TextView tvPeso, tvAltura, tvIMC, tvClassificacao;
    ImageView imgIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcresultado);

        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        tvIMC = findViewById(R.id.tvIMC);
        tvClassificacao = findViewById(R.id.tvClassificacao);

        imgIMC = findViewById(R.id.imgIMC);

        Bundle b = getIntent().getExtras();
        float peso = b.getFloat("peso");
        float altura = b.getFloat("altura");
        float imc = peso / (altura * altura);

        tvPeso.setText("Peso: " + peso + " Kg");
        tvAltura.setText("Altura: " + altura + " m");
        tvIMC.setText("IMC: " + String.format("%.2f", imc));



        if(imc < 18.5){
            tvClassificacao.setText("Abaixo do peso");
            imgIMC.setImageResource(R.drawable.abaixopeso);
        } else if(imc < 25){
            tvClassificacao.setText("Peso normal");
            imgIMC.setImageResource(R.drawable.normal);
        } else if(imc < 30){
            tvClassificacao.setText("Sobrepeso");
            imgIMC.setImageResource(R.drawable.sobrepeso);
        } else if(imc < 35){
            tvClassificacao.setText("Obesidade grau 1");
            imgIMC.setImageResource(R.drawable.obesidade1);
        } else if(imc < 40){
            tvClassificacao.setText("Obesidade grau 2");
            imgIMC.setImageResource(R.drawable.obesidade2);
        } else {
            tvClassificacao.setText("Obesidade grau 3");
            imgIMC.setImageResource(R.drawable.obesidade3);
        }

    }
}
