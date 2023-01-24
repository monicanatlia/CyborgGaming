package com.example.cyborggaming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList game_name_id, genre_name_id, developer_name_id, price_id, stock_id;

    public MyAdapter(Context context, ArrayList game_name_id, ArrayList genre_name_id, ArrayList developer_name_id, ArrayList price_id, ArrayList stock_id) {
        this.context = context;
        this.game_name_id = game_name_id;
        this.genre_name_id = genre_name_id;
        this.developer_name_id = developer_name_id;
        this.price_id = price_id;
        this.stock_id = stock_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.gameentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.game_name_id.setText(String.valueOf(game_name_id.get(position)));
        holder.genre_name_id.setText(String.valueOf(genre_name_id.get(position)));
        holder.developer_name_id.setText(String.valueOf(developer_name_id.get(position)));
        holder.price_id.setText(String.valueOf(price_id.get(position)));
        holder.stock_id.setText(String.valueOf(stock_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return game_name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView game_name_id, genre_name_id, developer_name_id, price_id, stock_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            game_name_id = itemView.findViewById(R.id.text_gamename);
            genre_name_id = itemView.findViewById(R.id.text_genrename);
            developer_name_id = itemView.findViewById(R.id.text_developername);
            price_id = itemView.findViewById(R.id.text_price);
            stock_id = itemView.findViewById(R.id.text_stock);

        }
    }
}
