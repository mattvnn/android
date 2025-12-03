package com.example.paint;  // MUDAR PARA ISSO

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SimplePaint simplePaint;
    private ImageButton btnPen, btnSquare, btnCircle;
    private Button btnClear, btnUndo, btnRedo;
    private SeekBar seekBarStroke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplePaint = findViewById(R.id.simplePaint);
        btnPen = findViewById(R.id.btnPen);
        btnSquare = findViewById(R.id.btnSquare);
        btnCircle = findViewById(R.id.btnCircle);
        btnClear = findViewById(R.id.btnClear);
        btnUndo = findViewById(R.id.btnUndo);
        btnRedo = findViewById(R.id.btnRedo);
        seekBarStroke = findViewById(R.id.seekBarStroke);

        btnPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setMode(0);  // 0 = Pincel
                highlightButton(btnPen);
                Toast.makeText(MainActivity.this, "Pincel", Toast.LENGTH_SHORT).show();
            }
        });

        btnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setMode(1);  // 1 = Retângulo
                highlightButton(btnSquare);
                Toast.makeText(MainActivity.this, "Retângulo", Toast.LENGTH_SHORT).show();
            }
        });

        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.setMode(2);  // 2 = Círculo
                highlightButton(btnCircle);
                Toast.makeText(MainActivity.this, "Círculo", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.clear();
                Toast.makeText(MainActivity.this, "Limpo", Toast.LENGTH_SHORT).show();
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.undo();
                Toast.makeText(MainActivity.this, "Desfeito", Toast.LENGTH_SHORT).show();
            }
        });

        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simplePaint.redo();
                Toast.makeText(MainActivity.this, "Refeito", Toast.LENGTH_SHORT).show();
            }
        });

        seekBarStroke.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                simplePaint.setStroke(progress + 5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnPen.performClick();
    }

    private void highlightButton(ImageButton active) {
        btnPen.setSelected(active == btnPen);
        btnSquare.setSelected(active == btnSquare);
        btnCircle.setSelected(active == btnCircle);
    }
}