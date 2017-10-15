package com.example.marty.derechoexamen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Textos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textos);
    }

    public void avanzarClick(View v){
        EditText nombre = (EditText)findViewById(R.id.nombre);
        EditText apellido = (EditText)findViewById(R.id.apellido);
        if (nombre.getText().length() > 0 && apellido.getText().length() > 0){
            Intent I = new Intent(Textos.this, Concatenados.class);
            I.putExtra("Nombre", nombre.getText().toString());
            I.putExtra("Apellido", apellido.getText().toString());
            startActivity(I);
        }else{
            Toast.makeText(this, "Por favor, llene ambos campos.", Toast.LENGTH_SHORT).show();
        }
    }
}
