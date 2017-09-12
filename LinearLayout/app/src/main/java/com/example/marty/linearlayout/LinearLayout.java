package com.example.marty.linearlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LinearLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
    }
    public void varValor(View v){
        EditText numero1 = (EditText)findViewById(R.id.texto1);
        String n1 = numero1.getText().toString();
        Log.d("numero1", n1);
        EditText numero2 = (EditText)findViewById(R.id.texto2);
        String n2 = numero2.getText().toString();
        Log.d("numero2", n2);
        if (n1.length() > 0 && n2.length() > 0)
        {
            Double d = Double.parseDouble(n1) + Double.parseDouble(n2);
            Log.d("suma", d.toString());
            Toast r = Toast.makeText(this, "Rseultado: "+ d.toString(), Toast.LENGTH_LONG);
            r.show();
        }else{
            Toast t = Toast.makeText(this, "Introduzca un valor...", Toast.LENGTH_SHORT);
            t.show();
        }

    }
}
