package com.example.en_senia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth bdMauth;
    private FirebaseUser bdCurrentUser;

    EditText etCorreo, etPass;
    TextView tvRecuperar, tvCrear;
    Button btnIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Ensenia_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        asignaComponentes();

        tvCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,RegistroUsuario.class));
                //crearUsuario(etCorreo.getText().toString(), etPass.getText().toString(), view);
            }
        });


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logear(etCorreo.getText().toString(),etPass.getText().toString(),view);
            }
        });
    }

    public void asignaComponentes(){
        btnIniciar = findViewById(R.id.LOGbtnIniciar);

        etCorreo = findViewById(R.id.LOGetcorreo);
        etPass = findViewById(R.id.LOGetpass);

        tvCrear = findViewById(R.id.LOGtvcrear);

        bdMauth = FirebaseAuth.getInstance();
        bdCurrentUser = bdMauth.getCurrentUser();
    }




    public void logear(String correo, String pass,View view){
        bdMauth.signInWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Se ha logeado", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = bdMauth.getCurrentUser();
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("usuario", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Correo no encontrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}