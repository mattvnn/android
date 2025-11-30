package com.example.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;






public class FragmentB extends Fragment {

    TextView txtResultado;
    Button btnVoltar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        txtResultado = view.findViewById(R.id.txtResultado);
        btnVoltar = view.findViewById(R.id.btnVoltar);

        if (getArguments() != null) {
            double dolar = getArguments().getDouble("resultado");
            double reais = getArguments().getDouble("valorOriginal");

            String msg = "R$ " + String.format("%.2f", reais) +
                    " equivalem a\nUS$ " + String.format("%.2f", dolar);

            txtResultado.setText(msg);
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.frameConteudo, new FragmentA());
                ft.commit();
            }
        });

        return view;
    }
}
