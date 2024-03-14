package com.example.seatt.placementapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class staffhome extends AppCompatActivity {

    EditText e1,e2;
    int fg=0,fg1=0;
    String user,pass;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffhome);
        e1=(EditText)findViewById(R.id.editText1);

        b1 = (Button)findViewById(R.id.button1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




               // Intent i=new Intent(staffhome.this,MainActivity.class);

               // i.putExtra("user",e1.getText().toString());


               // startActivity(i);


    
            }
        });

    }




}
