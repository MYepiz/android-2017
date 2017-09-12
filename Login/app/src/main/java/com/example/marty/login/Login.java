package com.example.marty.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loguear(View view){
        String E = ((EditText)findViewById(R.id.email)).getText().toString();
        String P = ((EditText)findViewById(R.id.contrasena)).getText().toString();
        if ( E.length() > 0 && P.length() > 0 ){
            if (E.equals("myepiz96@gmail.com") && P.equals("contra5")){
                Intent I = new Intent(getApplicationContext(), Inicio.class);
                startActivity(I);
            }else{
                Toast.makeText(this, "Contraseña o correo incorrectos.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Imtroduzca ambos campos.", Toast.LENGTH_SHORT).show();
        }
    }
}
