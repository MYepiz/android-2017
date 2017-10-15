package com.example.marty.derechoexamen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Concatenados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concatenados);

        String nombre;
        String apellido;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            apellido = extras.getString("Apellido");
            nombre = extras.getString("Nombre");
            ((TextView)findViewById(R.id.tv)).setText(nombre+' '+apellido);
        }

    }
}
