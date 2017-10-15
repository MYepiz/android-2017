package com.example.marty.derechoexamen;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Index extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        lista = (ListView) findViewById(R.id.lista);

        String[] opciones ={"Lector QR","Datos entre actividades","Diálogos","Grid","Salir"};

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(lista.getContext(),android.R.layout.simple_list_item_1,opciones);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {

                String selectedFromList =(String) (lista.getItemAtPosition(myItemInt));
                Intent I = new Intent();
                switch (selectedFromList){
                    case "Lector QR":
                        I = new Intent(Index.this, LectorQR.class);
                        startActivity(I);
                        break;
                    case "Datos entre actividades":
                        I = new Intent(Index.this, Textos.class);
                        startActivity(I);
                        break;
                    case "Diálogos":
                        I = new Intent(Index.this, Dialogo.class);
                        startActivity(I);
                        break;
                    case "Grid":
                        I = new Intent(Index.this, Grid.class);
                        startActivity(I);
                        break;
                    case "Salir":
                        createExitDialog();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void createExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage("ABANDONAR LA APLICACION")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
