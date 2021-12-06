package com.example.en_senia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.en_senia.objetos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroUsuario extends AppCompatActivity {

    Button btnRegistrarse;
    EditText etCorreo,etPass,etNombre;
    FirebaseAuth bdMauth;
    DatabaseReference bdReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        asignarComponentes();
                btnRegistrarse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(RegistroUsuario.this, "Iniciando proceso", Toast.LENGTH_LONG).show();
                        //mAuth.signOut();
                        bdMauth.createUserWithEmailAndPassword(etCorreo.getText().toString(),etPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistroUsuario.this, "Sesion registrada", Toast.LENGTH_LONG).show();
                                        Usuario us = new Usuario();
                                        us.setIdUsuario(bdMauth.getCurrentUser().getUid());
                                        us.setCorreo(etCorreo.getText().toString());
                                        us.setPass(etPass.getText().toString());
                                        us.setNombre(etNombre.getText().toString());
                                        bdReference
                                                .child("Usuario").child(us.getIdUsuario()).setValue(us);

                                        startActivity(new Intent(RegistroUsuario.this,Login.class));

                                        Toast.makeText(RegistroUsuario.this, "Registro Finalizado", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegistroUsuario.this, "Error con la bd", Toast.LENGTH_SHORT).show();
                                    }
                            }
                        });
                    }
                });
    }

    private void asignarComponentes(){
        btnRegistrarse = findViewById(R.id.REGbtnRegistrarse);

        etCorreo = findViewById(R.id.REGetcorreo);
        etNombre = findViewById(R.id.REGetnombre);
        etPass = findViewById(R.id.REGetpass);

        bdMauth = FirebaseAuth.getInstance();
        bdReference = FirebaseDatabase.getInstance().getReference();
        //databaseReferenceHotel = FirebaseDatabase.getInstance().getReference().child("RTDB_Hotels");
        //storageProfilePicsRef = FirebaseStorage.getInstance().getReference().child("STORAGE_Users_Profile_Pics");
    }
}