package com.example.seatt.placementapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        i1=(ImageView)findViewById(R.id.i2);


        i3=(ImageView)findViewById(R.id.imageView);


        i1.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                // user
                Intent i=new Intent(MainActivity2.this,Adminlogin.class);
                startActivity(i);
            }

        });



        i3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {




                Intent i=new Intent(MainActivity2.this,Stafflogin.class);
                startActivity(i);
            }

        });


    }
}
