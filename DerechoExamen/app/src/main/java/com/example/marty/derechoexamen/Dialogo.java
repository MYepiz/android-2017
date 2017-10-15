package com.example.marty.derechoexamen;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Dialogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogo);

        Button popup = (Button)findViewById(R.id.popup);

        popup.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog settingsDialog = new Dialog(v.getContext());
                    settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.imagepopup, null));
                    settingsDialog.setCanceledOnTouchOutside(true);
                    settingsDialog.show();
                }
            }
        );
    }
}
