package com.example.marty.a2doparcial;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        lista = (ListView) findViewById(R.id.lista);

        Field fields[] = R.string.class.getFields();
        //List<String> options = new ArrayList<String>();

        //for (Field field : fields) {
        //    if (field.getName().contains("inicioOpcion")){
        //        String getStringData = this.getResources().getString(this.getResources().getIdentifier(field.getName(), "string", this.getPackageName()));
        //        options.add( getStringData );
        //   }
        //}

        String [] opciones = {getString(R.string.inicioOpcion1), getString(R.string.inicioOpcion2),
                getString(R.string.inicioOpcion3), getString(R.string.inicioOpcion4)};
                //(String [])options.toArray();

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(lista.getContext(),android.R.layout.simple_list_item_1,opciones);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {

                String selectedFromList =(String) (lista.getItemAtPosition(myItemInt));
                Intent I = new Intent();
                switch (selectedFromList){
                    case "Leer placa":
                        I = new Intent(Inicio.this, LectorQR.class);
                        startActivity(I);
                        break;
                    case "Vehículos afiliados":
                        I = new Intent(Inicio.this, Vehiculos.class);
                        startActivity(I);
                        break;
                    case "Acerca de...":
                        createLayerDialogo();
                        break;
                    case "Cerrar sesión":
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
                .setMessage("TERMINAR SESIÓN")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent I = new Intent(Inicio.this, Login.class);
                        startActivity(I);
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

    public void createLayerDialogo() {
        final android.app.AlertDialog builder = new android.app.AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.activity_acerca, null);
        builder.setView(v);

        Button githubLink = (Button)v.findViewById(R.id.perfilLink);
        Button closeDialog = (Button)v.findViewById(R.id.cerrar);

        githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.githubLink)));
                startActivity(browserIntent);
            }
        });

        closeDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.dismiss();
                    }
                }
        );
        builder.show();
    }

}
