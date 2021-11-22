package com.example.en_senia.ui.mainPractica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.en_senia.MainActivity;
import com.example.en_senia.R;
import com.example.en_senia.ui.diccionario.Diccionario;

public class Practicas extends AppCompatActivity {

    private Button btnRegresar,btnIniciar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicas);


        btnRegresar = findViewById(R.id.PRACbtnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnIniciar = findViewById(R.id.PRACbtnIniciar);
    }




}