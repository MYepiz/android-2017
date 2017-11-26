package com.example.marty.gafette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Index extends AppCompatActivity {

    String JsonURL = "https://api.myjson.com/bins/175hin";
    String data = "";
    RequestQueue requestQueue;
    JSONObject O;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        requestQueue = Volley.newRequestQueue(this);


        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("personal");

                            String nombre = "Empleado: "+ obj.getString("nombre") + " " +
                                    obj.getString("paterno")+ " " + obj.getString("materno");

                            String sangre = "Tipo de sangre: "+ obj.getString("sangre");

                            String direccion = "Dirección: "+obj.getString("direccion");

                            byte[] decodedString = Base64.decode( obj.getString("foto"), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                            ((ImageView)findViewById(R.id.foto)).setImageBitmap(decodedByte);
                            ((TextView)findViewById(R.id.nombreCompleto)).setText(nombre);
                            ((TextView)findViewById(R.id.sangre)).setText(sangre);
                            ((TextView)findViewById(R.id.direccion)).setText(direccion);

                            O = obj;

                            ((Button)findViewById(R.id.mapa)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent I = new Intent(Index.this, Mapa.class);
                                    try {
                                        I.putExtra("Longitud", O.getString("longitud"));
                                        I.putExtra("Latitud", O.getString("latitud"));
                                        I.putExtra("Nombre", O.getString("nombre"));

                                    } catch (JSONException e) {
                                        I.putExtra("Longitud", "0");
                                        I.putExtra("Latitud", "0");
                                        I.putExtra("Nombre", "Empleado");
                                    }
                                    startActivity(I);
                                }
                            });
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        requestQueue.add(obreq);
    }
}
