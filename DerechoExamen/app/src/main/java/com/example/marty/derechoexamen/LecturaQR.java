package com.example.marty.derechoexamen;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.EditText;

public class LecturaQR extends AppCompatActivity {
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectura_qr);
        txt =(EditText)findViewById(R.id.txtLeido);
        String s = getIntent().getStringExtra("TEXTO");
        txt.setText(s);
    }
}
