package com.example.marty.esprimo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Primo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primo);
    }

    public void esPrimo(View view){
        EditText e = (EditText)findViewById(R.id.Nprimo);
        if(e.getText().length() > 0) {
            int E = Integer.parseInt(e.getText().toString());
            if (E % 2 == 0) {
                Toast.makeText(this, E + " es par.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, E + " es primo.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
