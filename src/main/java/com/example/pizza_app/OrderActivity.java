package com.example.pizza_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
    }

    public void submit(View view) {
        Toast.makeText(this,"Orderd Complete",Toast.LENGTH_LONG).show();

        String request_url = "http://10.0.2.2:8080/pizzaApp/deleteCartAll";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request_url, null, null);
        queue.add(stringRequest);

        Intent intent = new Intent(OrderActivity.this, PizzaListActivity.class);
        startActivity(intent);
    }
}

