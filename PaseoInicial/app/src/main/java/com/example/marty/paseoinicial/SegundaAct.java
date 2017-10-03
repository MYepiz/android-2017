package com.example.marty.paseoinicial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SegundaAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("cadena");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("cadena");
        }
        ((TextView)findViewById(R.id.TV)).setText(newString);
    }
}
