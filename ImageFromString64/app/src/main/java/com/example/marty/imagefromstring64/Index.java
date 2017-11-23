package com.example.marty.imagefromstring64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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

    TextView TV;
    String JsonURL = "https://api.myjson.com/bins/cjpxr";
    String data = "";
    RequestQueue requestQueue;

    ImageView IV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        IV = (ImageView)findViewById(R.id.IV);
        TV = (TextView)findViewById(R.id.textView);

        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("escuela");
                            String texto = obj.getString("nombre") + "\n" + obj.getString("direccion") + "\n";
                            TV.setText(texto);
                            String imagen64 = obj.getString("imagen");

                            byte[] decodedString = Base64.decode(imagen64, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            IV.setImageBitmap(decodedByte);
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
