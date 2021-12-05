package com.example.en_senia.ui.Lista_temas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class Lecciones extends AppCompatActivity {

    private Button btnAvanzar,btnRegresar;
    private DatabaseReference dbReference;
    private FirebaseDatabase dbFire;

    private ArrayList<Tema> lecciones = new ArrayList<Tema>();

    private int actual=0;
    private String tema;
    private TextView tvTitulo,tvDescripcion,tvTituloTem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecciones);


        asignaComponentes();
        consultarDatos();







        //cambiarLeccion();

        retorceder();
        avanzar();
    }

    public void retorceder(){
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnRegresar.getText().equals("Anterior")) {
                    if(actual > 1) {
                        actual--;
                        cambiarLeccion();
                    }
                }else{
                    Intent intent = new Intent(view.getContext(), ListaTemas.class);
                    startActivity(intent);
                }




            }
        });

    }
    public void avanzar(){
        btnAvanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAvanzar.getText().equals("Siguiente")){
                    if(actual < lecciones.size()-1) {
                        actual++;
                        cambiarLeccion();
                    }
                }else{
                    Intent intent = new Intent(view.getContext(), ListaTemas.class);
                    startActivity(intent);
                }


            }
        });

    }
    public void asignaComponentes(){
        dbFire = FirebaseDatabase.getInstance();
        dbReference = dbFire.getReference();
        btnRegresar = findViewById(R.id.LECCIONESbtnAnterior);
        btnAvanzar = findViewById(R.id.LECCIONESbtnSiguiente);
        //imgView = findViewById(R.id.LECIONESivImagen);
        tvTitulo = findViewById(R.id.LECIONEStvtitulo);
        tvDescripcion = findViewById(R.id.LECIONEStvDescripcion);
        tvTituloTem = findViewById(R.id.LECIONEStvTituloTema);

    }

    public void consultarDatos() {

        Bundle extras = getIntent().getExtras();
        tema = extras.getString("clave");
        Log.d("tema_recibido",tema);
        //tvTituloTem.setText(tema);

        dbReference.child("Lecciones").child(tema).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lecciones.clear();
                for (DataSnapshot objectSnapshot : snapshot.getChildren()) {
                    lecciones.add(objectSnapshot.getValue(Tema.class));
                    Log.d("tema_entro", "Entro "+ objectSnapshot.getKey().toString());
                    //Log.d("tema_key", objectSnapshot.getKey().toString());
                }
                Log.d("tema_tamanio", String.valueOf(lecciones.size()));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("tema_fallo", error.getDetails());
            }
        });
    }

    public void cambiarLeccion(){
        if (actual ==0){
            btnRegresar.setText("Temas");
        }else{
            btnRegresar.setText("Anterior");
        }
        if (actual == lecciones.size()-1){
            btnAvanzar.setText("Finalizar");
        }else{
            btnAvanzar.setText("Siguiente");
        }
        tvTitulo.setText(lecciones.get(actual).getTitulo());
        tvDescripcion.setText(lecciones.get(actual).getDescripcion());
    }
}