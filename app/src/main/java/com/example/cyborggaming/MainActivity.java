package com.example.cyborggaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    EditText username, password, confirmpassword;
    Button Loginbtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        TextView registerconfirm = (TextView) findViewById(R.id.registerconfirm);
        DBHelper DB = new DBHelper(this);
        MaterialButton Loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //monica dan monica
    registerconfirm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
            startActivity(intent);
        }
    });

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String name = username.getText().toString();
                 String pass = password.getText().toString();

                 if (name.equals("") || pass.equals(""))
                     Toast.makeText(MainActivity.this,"Please enter all the fields!", Toast.LENGTH_SHORT).show();
                 else{
                     Boolean checkuserpass = DB.checknamepassword(name, pass);
                     if (checkuserpass==true){
                         Toast.makeText(MainActivity.this,"Sign in successfull", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                         startActivity(intent);
                     }else {
                         Toast.makeText(MainActivity.this,"Invalid Credentials", Toast.LENGTH_SHORT).show();
                     }
                 }
            }

        });
    }
}