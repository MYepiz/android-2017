package com.example.marty.radiobuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioButtons extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_buttons);

        boolean M = ((RadioButton)findViewById(R.id.masculino)).isChecked();
        boolean F = ((RadioButton)findViewById(R.id.femenino)).isChecked();
        Toast.makeText(this, "Masculino: "+ M + " || Femenino: " + F, Toast.LENGTH_SHORT).show();

        ((RadioButton)findViewById(R.id.masculino)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b == true) {
                    (findViewById(R.id.text1)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.text2)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.text3)).setVisibility(View.VISIBLE);
                }
            }
        });
        ((RadioButton)findViewById(R.id.femenino)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if (b == true) {
                    (findViewById(R.id.text3)).setVisibility(View.GONE);
                    (findViewById(R.id.text1)).setVisibility(View.VISIBLE);
                    (findViewById(R.id.text2)).setVisibility(View.VISIBLE);
                }
            }
        });
    }


}
