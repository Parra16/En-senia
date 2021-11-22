package com.example.en_senia.ui.Lista_temas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class ListaTemas extends AppCompatActivity {

    private Button btnRegresar;
    private ListView lvListTemas;
    DatabaseReference dbReference;
    FirebaseDatabase dbFire;
    ArrayList<Tema> temas = new ArrayList<Tema>();
    private AdaptadorTema adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_temas);

        dbFire = FirebaseDatabase.getInstance();
        dbReference = dbFire.getReference();


        lvListTemas = findViewById(R.id.LISTEMlvtemas);
        btnRegresar = findViewById(R.id.LISTEMbtnRegresar);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        listarDatos();
    }

    public void listarDatos() {
        dbReference.child("Tema").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                temas.clear();
                for (DataSnapshot objectSnapshot : snapshot.getChildren()) {
                    Tema p = objectSnapshot.getValue(Tema.class);
                    temas.add(p);
                }
                adaptador = new AdaptadorTema(temas, ListaTemas.this);
                lvListTemas.setAdapter(adaptador);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
