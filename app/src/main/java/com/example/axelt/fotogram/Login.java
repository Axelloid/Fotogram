package com.example.axelt.fotogram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    String url = "https://ewserver.di.unimi.it/mobicomp/fotogram/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.user_field);
        password = findViewById(R.id.password_field);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String musername = username.getText().toString();
                final String mpassword = password.getText().toString();
                Log.d("tag", "Username: " + musername + " Password: " + mpassword);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("tag", "la risposta è "+response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,"Credenziali errate",Toast.LENGTH_LONG).show();
                        Log.d("tag", "l'errore è "+error.getMessage());
                    }
                })
                {
                    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", musername);
                    params.put("password", mpassword);
                    return params;
                };
                };
                queue.add(stringRequest);
            }
        });
    }
}
