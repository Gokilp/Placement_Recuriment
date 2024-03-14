package com.example.seatt.placementapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class staffpage extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6;
    String  emp_id;
    String phone_number;
    String  bus_no;
    String  stage_info;
    String staff_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffpage);

        staff_id=getIntent().getExtras().getString("staff_id");


        b1=(Button)findViewById(R.id.b1);

        b4=(Button)findViewById(R.id.button2);





        b6=(Button)findViewById(R.id.button10);






        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(staffpage.this,viewstudentinfo.class);
               // i.putExtra("staff_id", staff_id);
                startActivity(i);



            }
        });






        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(staffpage.this,AddmarkDetails.class);

                i.putExtra("staff_id", staff_id);

                startActivity(i);





            }
        });










        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i=new Intent(staffpage.this,viewplace.class);
                startActivity(i);




            }
        });










    }
}
