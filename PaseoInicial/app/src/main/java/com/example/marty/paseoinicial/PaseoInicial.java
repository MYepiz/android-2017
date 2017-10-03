package com.example.marty.paseoinicial;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PaseoInicial extends AppCompatActivity {
    SharedPreferences MY_PREFS_NAME = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paseo_inicial);
        MY_PREFS_NAME = getSharedPreferences("firstrun", MODE_PRIVATE);

        if (MY_PREFS_NAME.getBoolean("firstrun", true)){
            MY_PREFS_NAME.edit().putBoolean("firstrun", false).commit();
            Intent P = new Intent(this, Paseo.class);
            startActivity(P);
        }
    }

    public void segunda(View view){
        Intent S = new Intent(view.getContext(), SegundaAct.class);
        EditText ET = (EditText)findViewById(R.id.variable);
        S.putExtra("cadena", ET.getText().toString());
        startActivity(S);
    }
}
