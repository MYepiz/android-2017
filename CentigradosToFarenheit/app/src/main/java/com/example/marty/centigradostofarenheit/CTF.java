package com.example.marty.centigradostofarenheit;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CTF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctf);
    }

    public void calcularClick(View view){
        ((TextView)findViewById(R.id.resultado)).setText("");
        calcularCtF(
                ((EditText)findViewById(R.id.cel)).getText().toString()
        );
    }

    private void calcularCtF(String celcius){
        Toast t = null;
        if (celcius.length() > 0)
        {
            Double d =0.0;
            try{
                d = Double.parseDouble(celcius);
                String tip;

                Double r = (d * 1.8) + 32;
                if(r >= 100){
                    tip = "Oooy, bájale a tu verano oye.";
                    ((LinearLayout)findViewById(R.id.layoutl)).setBackgroundColor(Color.parseColor("#FFFF5252"));
                }else{
                    tip = "Oye, pero que frío, wow.";
                    ((LinearLayout)findViewById(R.id.layoutl)).setBackgroundColor(Color.parseColor("#FF4CDFEC"));
                }

                ((TextView)findViewById(R.id.far)).setText(r.toString());
                ((TextView)findViewById(R.id.resultado)).setText("Resultado: "+tip);
            }catch (Exception e){
                ((TextView)findViewById(R.id.resultado)).setText(e.toString());
                t = Toast.makeText(this, "Solo se aceptan decimales. "+e.getMessage(), Toast.LENGTH_SHORT);
                t.show();
            }
        }else{
            t = Toast.makeText(this, "Introduzca ambos valores...", Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
