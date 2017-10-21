package com.example.marty.a2doparcial;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class LecturaQR extends AppCompatActivity {
    EditText txt;
    double deuda = 0;
    View v;
    RadioGroup RG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_qr);
        txt =(EditText)findViewById(R.id.txtLeido);
        String lectura = getIntent().getStringExtra("TEXTO");
        final String [] lineas = lectura.split(",");
        String [] etiquetas = {"Marca", "Modelo", "Año", "Deuda"};
        if (lineas.length > 0) {
            deuda = Double.parseDouble(lineas[lineas.length-1]);
            lectura = etiquetas[0]+ ": " + lineas[0].toUpperCase();
            for (int i = 1; i < lineas.length; i++) {
                lectura += "\n" + etiquetas[i]+ ": " + lineas[i].toUpperCase();
            }
        }
        txt.setText(lectura);

        Button verDeuda = (Button)findViewById(R.id.verDeuda);
        verDeuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lineas.length > 0) {
                    createLayerDialogo(deuda);
                }
            }
        });
    }

    public void createLayerDialogo(Double deuda) {
        final AlertDialog builder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        v = inflater.inflate(R.layout.activity_deuda, null);
        builder.setView(v);

        NumberFormat formater = NumberFormat.getCurrencyInstance();
        ((TextView)v.findViewById(R.id.monto)).setText(formater.format(deuda));

        Button pagar = (Button)v.findViewById(R.id.pagar);

        RG = (RadioGroup)v.findViewById(R.id.redioDeudaGroup);
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton radioButton = (RadioButton) v.findViewById(i);
                Toast.makeText(LecturaQR.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (RG.getCheckedRadioButtonId() > -1){
                    builder.dismiss();
                }else{
                    Toast.makeText(LecturaQR.this, "Favor de especificar una forma de pago.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.show();
    }
}
