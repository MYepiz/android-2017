package com.example.marty.examenparcial1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class parcial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcial);
    }

    public void calcularRejasActuales(View view){
        String rejasString = ((EditText)findViewById(R.id.ProdAct)).getText().toString();
        if (rejasString.length() > 0) {
            int actuales = Integer.parseInt(rejasString);
            Toast.makeText(this, "Rejas actuales: "+actuales*80, Toast.LENGTH_LONG).show();
        }
    }

    public void calcularRejasTotales(View view){
        String rejasString = ((EditText)findViewById(R.id.ProdTot)).getText().toString();
        if (rejasString.length() > 0) {
            int totales = Integer.parseInt(rejasString);
            Toast.makeText(this, "Rejas totales: "+totales*80, Toast.LENGTH_LONG).show();
        }
    }

    public void masCinco(View view){
        String rejasString = ((EditText)findViewById(R.id.ProdAct)).getText().toString();
        if (rejasString.length() > 0) {
            int actuales = Integer.parseInt(rejasString) + 5;
            ((EditText)findViewById(R.id.ProdAct)).setText(""+actuales);
        }else{
            ((EditText)findViewById(R.id.ProdAct)).setText("5");
        }
    }

    public void masQuince(View view){
        String rejasString = ((EditText)findViewById(R.id.ProdAct)).getText().toString();
        if (rejasString.length() > 0) {
            int actuales = Integer.parseInt(rejasString) + 15;
            ((EditText)findViewById(R.id.ProdAct)).setText(""+actuales);
        }else{
            ((EditText)findViewById(R.id.ProdAct)).setText("15");
        }
    }

    public void masTreinta(View view){
        String rejasString = ((EditText)findViewById(R.id.ProdAct)).getText().toString();
        if (rejasString.length() > 0) {
            int actuales = Integer.parseInt(rejasString) + 30;
            ((EditText)findViewById(R.id.ProdAct)).setText(""+actuales);
        }else{
            ((EditText)findViewById(R.id.ProdAct)).setText("30");
        }
    }

    public void masCincuenta(View view){
        String rejasString = ((EditText)findViewById(R.id.ProdAct)).getText().toString();
        if (rejasString.length() > 0) {
            int actuales = Integer.parseInt(rejasString) + 50;
            ((EditText)findViewById(R.id.ProdAct)).setText(""+actuales);
        }else{
            ((EditText)findViewById(R.id.ProdAct)).setText("50");
        }
    }

    public void calcularPorcentajeActual(View view){
        String ahora = ((EditText)findViewById(R.id.ProdAct)).getText().toString();
        String total = ((EditText)findViewById(R.id.ProdTot)).getText().toString();

        if (ahora.length() > 0 && total.length() > 0) {
            int actuales = Integer.parseInt(ahora);
            int totales = Integer.parseInt(total);

            float r = (actuales * 100) / totales;
            ((TextView)findViewById(R.id.Porcent)).setText(r+"%");
            if (r >= 70){
                ((RelativeLayout)findViewById(R.id.layrel)).setBackgroundColor(Color.parseColor("#FFEF5350"));
            }else{
                ((RelativeLayout)findViewById(R.id.layrel)).setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            }
        }else{
            ((TextView)findViewById(R.id.Porcent)).setText("0%");
        }
    }
}
