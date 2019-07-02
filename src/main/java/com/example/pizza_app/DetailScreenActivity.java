package com.example.pizza_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

public class DetailScreenActivity extends AppCompatActivity {

    Intent intent;

    int cartId;
    String pizzaName;
    String pizzaImageUrl;
    float pizzaPrice;
    int pizzaQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);

        intent = getIntent();

        ImageView imageView = findViewById(R.id.largeImage);
        TextView textViewDetails = findViewById(R.id.details);
        TextView textViewName  = findViewById(R.id.name);
        TextView textViewPrice  = findViewById(R.id.price);

        pizzaImageUrl = intent.getStringExtra("imageUrl");
        Picasso.get().load(pizzaImageUrl).fit().into(imageView);

        textViewDetails.setText(intent.getStringExtra("description"));

        pizzaName = intent.getStringExtra("name");
        textViewName.setText(pizzaName);

        pizzaPrice = Float.parseFloat(intent.getStringExtra("price"));
        textViewPrice.setText(Float.toString(pizzaPrice));

    }

    public void order(View view) {
        cartId = Integer.parseInt(intent.getStringExtra("id"));

        String request_url = "http://10.0.2.2:8080/demo/addCart?pizzaName="+pizzaName+"&pizzaImageUrl="+pizzaImageUrl+"&pizzaPrice="+pizzaPrice+"&pizzaQuantity="+pizzaQuantity;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, request_url, null, null);
        queue.add(stringRequest);

        Intent intent = new Intent(DetailScreenActivity.this, PizzaListActivity.class);
        startActivity(intent);
    }

    public void cart(View view) {
        Intent intent = new Intent(DetailScreenActivity.this,CartActivity.class);
        startActivity(intent);
    }
}
