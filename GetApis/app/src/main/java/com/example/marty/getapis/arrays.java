package com.example.marty.getapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class arrays extends AppCompatActivity {

    TextView results;
    String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Array.JSON";
    String data = "";
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);

        requestQueue = Volley.newRequestQueue(this);
        results = (TextView) findViewById(R.id.jsonData);
        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,

                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject colorObj = response.getJSONObject(0);
                            JSONArray colorArry = colorObj.getJSONArray("colorArray");

                            for (int i = 0; i < colorArry.length(); i++) {
                                JSONObject jsonObject = colorArry.getJSONObject(i);
                                String color = jsonObject.getString("colorName");
                                String hex = jsonObject.getString("hexValue");

                                data += "Color Number " + (i + 1) + "\nnColor Name: " + color +
                                        "\nnHex Value : " + hex + "nnn \n\n";
                            }
                            results.setText(data);
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
        requestQueue.add(arrayreq);
    }

    public void changeView(View view){
        Intent I = new Intent(arrays.this, Index.class);
        startActivity(I);
    }
}
