package com.example.cyborggaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    EditText username, password, confirmpassword;
    Button registerbutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    username = (EditText) findViewById(R.id.name1);
    password = (EditText) findViewById(R.id.password1);
    registerbutton = (Button) findViewById(R.id.registerbutton);
    confirmpassword = (EditText) findViewById(R.id.confirm_password);
    DB = new DBHelper(this);

    registerbutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = username.getText().toString();
            String pass = password.getText().toString();
            String confirm = confirmpassword.getText().toString();

            if (name.equals("") || pass.equals("") || confirm.equals(""))
                Toast.makeText( RegisterActivity.this,"Please enter all the fields!", Toast.LENGTH_SHORT).show();
            else {
                if (pass.equals(confirm)) {
                    Boolean checkuser = DB.checkname(name);
                    if (checkuser == false) {
                        Boolean insert = DB.insertuserdata(name, pass);
                        if (insert == true) {
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Password not matching!", Toast.LENGTH_SHORT).show();
                }
        }
    };

    });

}}