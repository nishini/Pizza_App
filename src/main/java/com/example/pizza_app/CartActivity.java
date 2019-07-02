package com.example.pizza_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    String request_url;

    RequestQueue queue;

    List<Cart> cart;

    RecyclerView recyclerView;
    RecyclerView.Adapter pAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        request_url = "http://10.0.2.2:8080/demo/cartAll";

        queue = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        cart = new ArrayList<>();

        Request();
    }

    private void Request() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Cart cartRequest = new Cart();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        cartRequest.setCartId(Integer.parseInt(jsonObject.get("cartId").toString()));
                        cartRequest.setPizzaName(jsonObject.get("pizzaName").toString());
                        cartRequest.setPizzaPrice(Float.parseFloat(jsonObject.get("pizzaPrice").toString()));
                        cartRequest.setPizzaImageurl(jsonObject.get("pizzaImageUrl").toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    cart.add(cartRequest);

                }

                pAdapter = new CustomCartAdapter(CartActivity.this, cart);
                recyclerView.setAdapter(pAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonArrayRequest);
    }

    public void order(View view) {
        Intent intent = new Intent(CartActivity.this, OrderActivity.class);
        startActivity(intent);
    }
}
