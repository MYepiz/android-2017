package com.example.marty.sendpost;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Index extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        texto = (TextView) findViewById(R.id.Text);
        try {
            RequestQueue queue = Volley.newRequestQueue(this);
            String URL ="http://httpbin.org/post";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("firstkey", "Manuel");
            jsonBody.put("secondkey", "Yépiz");
            final String mRequestBody = jsonBody.toString();

// Post params to be sent to the server
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("token", "token_value");
            params.put("login_id", "login_id_value");
            params.put("UN", "username");
            params.put("PW", "password");

            JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            texto.setText(response.toString().replace(",",",\n"));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    texto.setText("Error: "+ error.getMessage());
                }
            });

            queue.add(request_json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
