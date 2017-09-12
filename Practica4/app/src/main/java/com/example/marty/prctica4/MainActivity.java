package com.example.marty.prctica4;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String nombre ="Raul Alfonso";
        String apellido = "Tejada Castañeda";
        //String completo = String.join(" ", apellido, nombre);
        //Toast toast = Toast.makeText(this, nombre+" "+apellido, Toast.LENGTH_LONG);
        //toast.show();

        /*int torta = 40;

        int compra = 3;

        if (compra*torta > 80){
            Toast T = Toast.makeText(this, "Soda Gratis WUUU!", Toast.LENGTH_LONG);
            T.show();
        }else{
            Toast M = Toast.makeText(this, "Paguele men!", Toast.LENGTH_LONG);
            M.show( f);
        }*/
        ((Button)findViewById(R.id.button)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText price   = (EditText)findViewById(R.id.precio);
        EditText quantity   = (EditText)findViewById(R.id.cantidad);
        ImageView coca   = (ImageView) findViewById(R.id.coca);

        if(price.getText().length() > 0 || quantity.getText().length() > 0) {

            if (Integer.parseInt(price.getText().toString()) * Integer.parseInt(quantity.getText().toString()) > 80) {
                Toast T = Toast.makeText(this, "Soda Gratis WUUU!", Toast.LENGTH_LONG);
                T.show();
                coca.setVisibility(coca.VISIBLE);
            } else {
                Toast M = Toast.makeText(this, "Paguele men!", Toast.LENGTH_LONG);
                M.show();
                coca.setVisibility(coca.GONE);
            }
        }else{
            Toast T = Toast.makeText(this, "Introduzaca una cantidad.", Toast.LENGTH_LONG);
            T.show();
        }
    }


}
