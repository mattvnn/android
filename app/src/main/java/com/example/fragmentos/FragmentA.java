package com.example.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentA extends Fragment {

    EditText edValor; // Mude o nome da variável
    Button btnConverter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);

        edValor = view.findViewById(R.id.edValor); // Agora bate com o ID
        btnConverter = view.findViewById(R.id.btnConverter);

        btnConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto = edValor.getText().toString().trim(); // Use edValor aqui

                if (texto.isEmpty()) {
                    edValor.setError("Digite um valor"); // E aqui também
                    return;
                }

                double valorReais = Double.parseDouble(texto);
                double valorDolar = valorReais / 5.50;

                FragmentB fragmentB = new FragmentB();
                Bundle bundle = new Bundle();
                bundle.putDouble("resultado", valorDolar);
                bundle.putDouble("valorOriginal", valorReais);

                fragmentB.setArguments(bundle);

                FragmentTransaction ft = getParentFragmentManager().beginTransaction();
                ft.replace(R.id.frameConteudo, fragmentB);
                ft.commit();
            }
        });

        return view;
    }
}