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
        //Toast toast = Toast.makeText(this, nombre+" "+apellido, Toast.LENGTH_LONG);
        //toast.show();

        int torta = 40;

        int compra = 3;

        if (compra*torta > 80){
            Toast T = Toast.makeText(this, "Soda Gratis WUUU!", Toast.LENGTH_LONG);
            T.show();
        }else{
            Toast M = Toast.makeText(this, "Paguele men!", Toast.LENGTH_LONG);
            M.show();
        }
    }
}
