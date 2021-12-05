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
import android.widget.Toast;

import com.example.en_senia.MainActivity;
import com.example.en_senia.R;
import com.example.en_senia.adaptadores.AdaptadorTema;
import com.example.en_senia.objetos.Tema;
import com.example.en_senia.ui.Lista_temas.ListaTemas;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Diccionario extends AppCompatActivity {

    private Button btnRegresar,btnBuscar;
    private EditText etbusqueda;
    DatabaseReference dbReference;
    FirebaseDatabase dbFire;
    private ListView lvListTemas;
    ArrayList<Tema> temas = new ArrayList<Tema>();
    private AdaptadorTema adaptador;
    Tema temaSeleccionado = new Tema();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diccionario);

        dbFire = FirebaseDatabase.getInstance();
        dbReference = dbFire.getReference();

        etbusqueda = findViewById(R.id.DICetBusqueda);
        btnBuscar = findViewById(R.id.DICbtnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarDatos(etbusqueda.getText().toString());
            }
        });

        lvListTemas = findViewById(R.id.DIClvResultados);


        btnRegresar = findViewById(R.id.DICbtnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void listarDatos(String busqueda) {
        Log.d("busqueda", busqueda);
        dbReference.child("Lecciones").child("a").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                temas.clear();
                for (DataSnapshot objectSnapshot : snapshot.getChildren()) {
                     temas.add(objectSnapshot.getValue(Tema.class));
                    Log.d("diccionario", "Entro ");
                    Log.d("diccionario_key",String.valueOf(objectSnapshot.getKey()) );
                }
                //Log.d("diccionario", String.valueOf(temas.size()));
                //adaptador = new AdaptadorTema(temas, Diccionario.this);
                //lvListTemas.setAdapter(adaptador);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}