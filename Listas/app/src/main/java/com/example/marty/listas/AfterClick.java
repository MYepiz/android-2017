package com.example.marty.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AfterClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_click);

        TextView t = (TextView)findViewById(R.id.tV);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            t.setText("");
        } else {
            t.setText(extras.getString("Mensaje"));
        }
    }
}
