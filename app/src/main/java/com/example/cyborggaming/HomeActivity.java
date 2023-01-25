package com.example.cyborggaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    EditText gameName, genreName, developerName, price, stock;
    Button insertgamedata, viewgamedata, updategamedata, deletegamedata, Logoutbtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gameName = findViewById(R.id.game_name);
        genreName = findViewById(R.id.genre_name);
        developerName = findViewById(R.id.developer_name);
        price = findViewById(R.id.price);
        stock = findViewById(R.id.stock);
        insertgamedata = findViewById(R.id.registergamebtn);
        viewgamedata = findViewById(R.id.registergamebtn1);
        updategamedata = findViewById(R.id.updatebutton);
        deletegamedata = findViewById(R.id.deletebutton);
        Logoutbtn = findViewById(R.id.logoutbtn);


        DB = new DBHelper(this);

        Logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Successfully Log Out!", Toast.LENGTH_SHORT).show();
            }
        });

        insertgamedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameNametxt = gameName.getText().toString();
                String genreNametxt = genreName.getText().toString();
                String developerNametxt = developerName.getText().toString();
                String pricetxt = price.getText().toString();
                String stocktxt = stock.getText().toString();

                Boolean checkinsertgamedata = DB.insertgamedata(gameNametxt, genreNametxt, developerNametxt, pricetxt, stocktxt);
                if (checkinsertgamedata){
                    Toast.makeText(HomeActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(HomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewgamedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Gamelist.class);
                startActivity(intent);
            }
        });

        updategamedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameNametxt = gameName.getText().toString();
                String genreNametxt = genreName.getText().toString();
                String developerNametxt = developerName.getText().toString();
                String pricetxt = price.getText().toString();
                String stocktxt = stock.getText().toString();

                Boolean checkupdatedata = DB.updategamedetails(gameNametxt, genreNametxt, developerNametxt, pricetxt, stocktxt);
                if (checkupdatedata==true)
                    Toast.makeText(HomeActivity.this, "Data was successfully updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Data was not successfully updated", Toast.LENGTH_SHORT).show();
            }
        });

        deletegamedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameNametxt = gameName.getText().toString();

                Boolean checkdeletedata = DB.deletedata(gameNametxt);
                if (checkdeletedata==true)
                    Toast.makeText(HomeActivity.this, "Data successfully deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Data was not deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }
}