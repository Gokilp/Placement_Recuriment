package com.example.seatt.placementapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AddmarkDetails extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5, e6,deptinfo,e7;
    ImageView imageView;
    FileOutputStream fileoutputstream;
    ByteArrayOutputStream b1;
    File file;
    String photoPath = null;
    byte[] img, img1;
    public final static int QRcodeWidth = 500 ;
    String rp,staff_id;
    Bitmap bitmap ;
    Spinner s1,s2;
    String dbrno;
    int dbflag = 0;
    int PICK_IMAGE;
    Cursor c;
    Button b11,b12;
    SmsManager sms;
    String rno, name, phone, classs, address;
    int fg = 0;
    private DatePickerDialog fromDatePickerDialog2;
    Calendar newCalendar;
    Calendar newDate;
    int fg2 = 0;
    int xyr, xday, xmonth;
    String t1,t2;
    int RESULT_CODE;
    List dept=new ArrayList();
    private SimpleDateFormat dateFormatter;
    ArrayAdapter sp,sp1;
    List gend = new ArrayList();
    Random rand = new Random();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark_details);


        staff_id=getIntent().getExtras().getString("staff_id");



        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.editText);

        e7 = (EditText) findViewById(R.id.e6);


        b11 = (Button) findViewById(R.id.button);


        b12 = (Button) findViewById(R.id.b1);
        s2=(Spinner)findViewById(R.id.spinner);


        dept.add("Bsc-cs");
        dept.add("Bsc-ct");
        dept.add("Bca");

        dept.add("MCA");
        dept.add("Msc-cs");
        dept.add("Msc-IT");



        sp1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept);
        sp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(sp1);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               t2=(String)s2.getSelectedItem();
                Toast.makeText(getApplicationContext(),"Dept:"+t2,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });





        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    SQLiteDatabase db=openOrCreateDatabase("student", Context.MODE_PRIVATE,null);
                    try{

                        String q1="CREATE TABLE markinfo(s1 TEXT,s2 TEXT,s3 INTEGER,s4 INTEGER,s5 INTEGER,s6 INTEGER,s7 INTEGER,s8 INTEGER,s9 INTEGER);";

                        db.execSQL(q1);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Exception in creating table "+e,Toast.LENGTH_SHORT).show();
                    }


                String insert="INSERT INTO  markinfo(s1,s2,s3,s4,s5,s6,s7,s8,s9) VALUES('"+e1.getText().toString()+"','"+ t2+"','"+ Integer.valueOf(e2.getText().toString())+"','"+Integer.valueOf(e3.getText().toString())+"','"+Integer.valueOf(e4.getText().toString())+"','"+Integer.valueOf(e5.getText().toString())+"','"+Integer.valueOf(e6.getText().toString())+"','"+Integer.valueOf(e7.getText().toString())+"','"+staff_id+"')";



                try{
                    db.execSQL(insert);

                    Toast.makeText(getApplicationContext(),"Mark  Details Entry success ",Toast.LENGTH_SHORT).show();


                    Intent i=new Intent(AddmarkDetails.this,Adminpage.class);

                    startActivity(i);



                }
                catch (Exception e )
                {
                    //Toast.makeText(this,"Exeption in inserting "+e,Toast.LENGTH_SHORT).show();
                }






            }
        });
    }










}
