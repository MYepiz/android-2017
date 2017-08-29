package com.example.marty.prctica4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String nombre ="Raul Alfonso";
        String apellido = "Tejada Castañeda";
        //String completo = String.join(" ", apellido, nombre);
        Toast toast = Toast.makeText(this, nombre+" "+apellido, Toast.LENGTH_LONG);
        toast.show();
    }
}
