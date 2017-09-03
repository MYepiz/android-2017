package com.example.marty.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IMC extends AppCompatActivity {
    String estatusDelPaciente = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);
    }

    public void calcular(View view){
        estatusDelPaciente = "";
        ((TextView)findViewById(R.id.resultado)).setText(estatusDelPaciente);
        calcularMasaCorporal(
                ((EditText)findViewById(R.id.peso)).getText().toString(),
                ((EditText)findViewById(R.id.estatura)).getText().toString()
        );
    }

    private void calcularMasaCorporal(String pesoEnKg, String estaturaEnMetros){
        Toast t = null;
        if (pesoEnKg.length() > 0 && estaturaEnMetros.length() > 0)
        {
            Double d =0.0;
            Double b =0.0;
            try{
                d = Double.parseDouble(pesoEnKg);
                b = Double.parseDouble(estaturaEnMetros);

                Double r = (d/(b*b));
                Double min = 19.0;
                Double max = 24.9;
                String tip = "";
                if(r >= max){
                    tip = "Se encuentra "+(r-max)+" por sobre el límite ("+max+").";
                }else if(r <= min){
                    tip = "Se encuentra "+(min-r)+" por debajo del límite ("+min+").";
                }

                if ( r >= min && r <= max ){
                    estatusDelPaciente = "El paciente se encuentra en el peso adecuado.\n"+ tip;
                }else{
                    estatusDelPaciente = "El paciente NO se encuentra en el peso adecuado.\n"+tip;
                }
                ((TextView)findViewById(R.id.resultado)).setText("Resultado: "+r.toString()+"\n"+estatusDelPaciente);
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
