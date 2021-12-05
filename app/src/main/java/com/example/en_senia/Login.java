package com.example.en_senia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.auth.UserInfo;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    EditText etCorreo, etPass;
    TextView tvRecuperar, tvCrear;
    Button btnIniciar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Ensenia_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        etCorreo = findViewById(R.id.LOGetcorreo);
        etPass = findViewById(R.id.LOGetpass);
        //tvRecuperar = findViewById(R.id.LOGtvrecuperar);
        btnIniciar = findViewById(R.id.LOGbtnIniciar);


        tvCrear = findViewById(R.id.LOGtvcrear);
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

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //reload();//usuario logeado
        } else {
            //usuario no logeado
        }

    }

    public void crearUsuario(String email, String password, View view) {
        if (email != null && password != null) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(view.getContext(), MainActivity.class);
                                intent.putExtra("usuario", user);
                                startActivity(intent);

                                //updateUI(user);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Fallo en crear usuario",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }
    }


    public void logear(String correo, String pass,View view){

        mAuth.signInWithEmailAndPassword(correo,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Se ha logeado", Toast.LENGTH_SHORT).show();

                    FirebaseUser user = mAuth.getCurrentUser();
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