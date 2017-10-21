package com.example.marty.a2doparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((Button)findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usuario = (EditText)findViewById(R.id.usuario);
                EditText contrasena = (EditText)findViewById(R.id.contrasena);
                if (usuario.length() > 0 && contrasena.length() > 0){
                    if ( usuario.getText().toString().equals("segundoparcial")
                            &&  contrasena.getText().toString().equals("1")) {
                        Intent I = new Intent(view.getContext(), Inicio.class);
                        startActivity(I);
                    }else{
                        Toast.makeText(Login.this, "Usuario y/o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this, "Por favor ingrese ambos compos.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
