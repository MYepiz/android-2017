package com.example.marty.practicacheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class checkboxes extends AppCompatActivity {
    CheckBox checkRock;
    EditText editPassword;
    EditText editZero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxes);
        checkRock = (CheckBox)findViewById(R.id.checkRock);
        editPassword = (EditText)findViewById(R.id.editPassword);
        editZero = (EditText)findViewById(R.id.editZero);

        if (checkRock.isChecked()) {
            Toast.makeText(this, "Puro rock!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Todo tonto no tiene rock.", Toast.LENGTH_SHORT).show();
        }

        editZero.addTextChangedListener(new CustomWatcher());
    }

    public void checkRockValue(View v){
        if (checkRock.isChecked()) {
            editPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            Toast.makeText(this, "Puro rock!", Toast.LENGTH_SHORT).show();
        }else{
            editPassword.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            Toast.makeText(this, "Todo tonto no tiene rock.", Toast.LENGTH_SHORT).show();
        }
    }

    public class CustomWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() > 0) {
                if (Integer.parseInt(s.toString()) > 1000) {
                    checkRock.setChecked(true);
                } else {
                    checkRock.setChecked(false);
                }
            }
        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    }

}
