package com.example.seatt.placementapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class Adminpage extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b11,b12;
    String st_phone;
    String staffid,dept;
    Random rand = new Random();

    String phone="";
    Cursor c,c1;
    SmsManager sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage);

        b1=(Button)findViewById(R.id.b1);

        b3=(Button)findViewById(R.id.button2);

        b4=(Button)findViewById(R.id.button8);


        b5=(Button)findViewById(R.id.button4);

        b6=(Button)findViewById(R.id.button5);

        b7=(Button)findViewById(R.id.button6);


        b8=(Button)findViewById(R.id.button7);


        b9=(Button)findViewById(R.id.button9);

        b11=(Button)findViewById(R.id.button11);

        b12=(Button)findViewById(R.id.button12);




        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //in section

                Intent i=new Intent(Adminpage.this,remove2.class);

                startActivity(i);







            }
        });






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //in section

                Intent i=new Intent(Adminpage.this,AddstaffDetails.class);

                startActivity(i);







            }
        });




        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //in section

                Intent i=new Intent(Adminpage.this,viewattendace.class);

                startActivity(i);







            }
        });






        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                Intent i=new Intent(Adminpage.this,remove1.class);

                startActivity(i);

            }
        });











        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                Intent i=new Intent(Adminpage.this,viewplace.class);

                startActivity(i);


            }
        });


        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                Intent i=new Intent(Adminpage.this,remove.class);

                startActivity(i);


            }
        });




















        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(Adminpage.this,viewstaffinfo.class);

                startActivity(i);

            }
        });



        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // logout

                Intent i=new Intent(Adminpage.this,MainActivity2.class);
                startActivity(i);
            }
        });




        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // logout

                Intent i=new Intent(Adminpage.this,AddstudentDetails.class);
                startActivity(i);
            }
        });



        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Adminpage.this,AddcompanyDetails.class);
                startActivity(i);


            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // logout

                Intent i=new Intent(Adminpage.this,viewattendace .class);
                startActivity(i);
            }
        });












    }
}
