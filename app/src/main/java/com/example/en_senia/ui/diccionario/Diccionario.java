package com.example.en_senia.ui.diccionario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.en_senia.MainActivity;
import com.example.en_senia.R;
import com.example.en_senia.adaptadores.AdaptadorTema;
import com.example.en_senia.objetos.Tema;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Diccionario extends AppCompatActivity {

    private Button btnRegresar,btnBuscar;
    private EditText etbusqueda;
    DatabaseReference bdReference;
    FirebaseDatabase bdFire;
    private ListView lvTemas;
    ArrayList<Tema> arrtemas = new ArrayList<Tema>();
    private AdaptadorTema adaptador;
    Tema temaSeleccionado = new Tema();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diccionario);
        asigComponentes();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarDatos(etbusqueda.getText().toString());
            }
        });


        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void asigComponentes(){
        btnBuscar = findViewById(R.id.DICbtnBuscar);
        btnRegresar = findViewById(R.id.DICbtnRegresar);

        etbusqueda = findViewById(R.id.DICetBusqueda);

        lvTemas = findViewById(R.id.DIClvResultados);

        bdFire = FirebaseDatabase.getInstance();
        bdReference = bdFire.getReference();
    }

    public void listarDatos(String busqueda) {
        Log.d("busqueda", busqueda);
        arrtemas.clear();
        bdReference.child("Lecciones").child("Abecedario").child(busqueda).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrtemas.add(snapshot.getValue(Tema.class));
                adaptador = new AdaptadorTema(arrtemas, Diccionario.this);
                lvTemas.setAdapter(adaptador);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}