package com.example.marty.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Listas extends AppCompatActivity {
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);
        lista = (ListView) findViewById(R.id.lista);

        String[] companeros={"Flaca","Bryan","Chuy","Victor","Ian","Yepiz","Jacob"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(lista.getContext(),android.R.layout.simple_list_item_1,companeros);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {

                String selectedFromList =(String) (lista.getItemAtPosition(myItemInt));
                Intent I = new Intent(Listas.this, AfterClick.class);

                switch (selectedFromList){
                    case "Flaca":
                        I.putExtra("Mensaje", "Acosadora");
                        break;
                    case "Bryan":
                        I.putExtra("Mensaje", "Rockstar de Monterrey");
                        break;
                    case "Chuy":
                        I.putExtra("Mensaje", "Chuyditas");
                        break;
                    case "Ian":
                        I.putExtra("Mensaje", "Lord Vladi");
                        break;
                    case "Yepiz":
                        I.putExtra("Mensaje", "Rompe cuellos");
                        break;
                    case "Jacob":
                        I.putExtra("Mensaje", "Barbas");
                        break;
                    case "Victor":
                        I.putExtra("Mensaje", "Videos");
                        break;
                }
                startActivity(I);
            }
        });
    }
}
