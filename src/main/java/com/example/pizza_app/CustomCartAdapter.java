package com.example.pizza_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CustomCartAdapter extends RecyclerView.Adapter<CustomCartAdapter.ViewHolder> {

    private Context context;
    private List<Cart> cart;


    public CustomCartAdapter(Context context, List cart) {
        this.context = context;
        this.cart = cart;
    }

    @Override
    public CustomCartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(cart.get(position));

        Cart cartGet = cart.get(position);

        holder.name.setText(cartGet.getPizzaName());
        float price = cartGet.getPizzaPrice();

        holder.price.setText(Float.toString(price));
        Picasso.get().load(cartGet.getPizzaImageurl()).resize(500,500).into(holder.imageUrl);

    }

    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView price;
        public ImageView imageUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.pizzaName);
            price = (TextView) itemView.findViewById(R.id.pizzaPrice);
            imageUrl = (ImageView) itemView.findViewById(R.id.pizzaImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cart send = (Cart) v.getTag();

                    String sendId = Integer.toString(send.getCartId());

                    String request_url = "http://10.0.2.2:8080/pizzaApp/deleteByCartId?id="+sendId;

                    RequestQueue queue = Volley.newRequestQueue(context);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, request_url, null, null);
                    queue.add(stringRequest);

                    cart.remove(getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

        }
    }

}

