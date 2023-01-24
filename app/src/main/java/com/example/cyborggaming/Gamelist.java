package com.example.cyborggaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Gamelist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> gamename, genrename, developername, stock, price;
    DBHelper DB;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);
        DB = new DBHelper(this);
        gamename = new ArrayList<>();
        genrename = new ArrayList<>();
        developername = new ArrayList<>();
        stock = new ArrayList<>();
        price = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, gamename, genrename, developername, stock, price);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }
        else {
             StringBuilder buffer = new StringBuilder();
            while (cursor.moveToNext()){
                buffer.append("Game Name:   " + gamename.add(cursor.getString(0)));
                buffer.append("Genre Name:   " + genrename.add(cursor.getString(1)));
                buffer.append("Developer Name:   " +developername.add(cursor.getString(2)));
                buffer.append("Stock Name:   " +  stock.add(cursor.getString(3)));
                buffer.append("Price Name:   " + price.add(cursor.getString(4)));

                /*developername.add(cursor.getString(2));
                stock.add(cursor.getString(3));
                price.add(cursor.getString(4));*/
            }
        }
    }
}