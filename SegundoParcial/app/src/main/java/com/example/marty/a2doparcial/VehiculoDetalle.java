package com.example.marty.a2doparcial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class VehiculoDetalle extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "com.herprogramacion.coches2015.extra.ID";
    public static final String VIEW_NAME_HEADER_IMAGE = "imagen_compartida";
    private GridItems itemDetallado;
    private ImageView imagenExtendida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo_detalle);

        usarToolbar();

        // Obtener el coche con el identificador establecido en la actividad principal
        itemDetallado = GridItems.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));

        imagenExtendida = (ImageView) findViewById(R.id.imagen_extendida);

        cargarImagenExtendida();
    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);
    }

    private void usarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
