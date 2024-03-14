package com.example.seatt.placementapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AddcompanyDetails extends AppCompatActivity {
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
    Cursor c,c1;
    Button b11,b12;
    SmsManager sms;
    String rno, name, phone, classs, address,rollno;
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
    String st_name,st_phone,deptinfo1;

    List gend = new ArrayList();
    Random rand = new Random();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mark_fillter);






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


        dept.add("IT");
        dept.add("NONIT");
        dept.add("BPO");



        sp1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept);
        sp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(sp1);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               t2=(String)s2.getSelectedItem();
                Toast.makeText(getApplicationContext(),"Type:"+t2,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(AddcompanyDetails.this,MainActivity2.class);

                startActivity(i);

            }
        });





        b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    StringBuilder sb=new StringBuilder();


                    SQLiteDatabase db=openOrCreateDatabase("student", Context.MODE_PRIVATE,null);



                    try{

                        if(db!=null){

                            c=db.rawQuery("SELECT * from markinfo where s3>'"+Integer.valueOf(e2.getText().toString())+"' AND  s4>'"+Integer.valueOf(e3.getText().toString())+"' AND s5>'"+Integer.valueOf(e4.getText().toString())+"' AND s5>'"+Integer.valueOf(e5.getText().toString())+"' AND s6>'"+Integer.valueOf(e6.getText().toString())+"' ",null);
                            if(c.moveToFirst()){
                                do{



                                    rollno=c.getString(c.getColumnIndexOrThrow("s1"));



                                    Toast.makeText(getApplicationContext()," Fillteration  success "+ rollno,Toast.LENGTH_SHORT).show();




                                            c=db.rawQuery("SELECT * from studentinfo where (staffid='"+rollno+"')",null);
                                            if(c.moveToFirst()){
                                                do{



                                                    st_name=c.getString(c.getColumnIndexOrThrow("name"));
                                                    st_phone=c.getString(c.getColumnIndexOrThrow("phone"));

                                                    deptinfo1=c.getString(c.getColumnIndexOrThrow("dept"));

                                                    Toast.makeText(getApplicationContext(),"Exception in creating table "+st_phone,Toast.LENGTH_SHORT).show();
                                                     sb=new StringBuilder();

                                                    sb.append(" ");
                                                    sb.append(" Hi Student You are Short listed to  Company .The   Name of the Company is   "+e1.getText().toString() +" Please Contact Placement Cell soon");
                                                    sb.append(" ");



                                                    //insert
                                                    SQLiteDatabase db1=openOrCreateDatabase("collegeplacement", Context.MODE_PRIVATE,null);
                                                    try{

                                                        String q1="CREATE TABLE placement(s1 TEXT,s2 TEXT,s3 TEXT,s4 TEXT,s5 TEXT);";
                                                        db1.execSQL(q1);
                                                    }
                                                    catch (Exception e)
                                                    {
                                                        // Toast.makeText(getApplicationContext(),"Exception in creating table "+e,Toast.LENGTH_SHORT).show();
                                                    }

                                                    String insert="INSERT INTO placement(s1,s2,s3,s4,s5) VALUES('"+ rollno+"','"+ st_name+"','"+st_phone+"','"+e1.getText().toString()+"','"+deptinfo1+"')";
                                                    try{
                                                        db1.execSQL(insert);
                                                        Toast.makeText(getApplicationContext()," Fillteration  success ",Toast.LENGTH_SHORT).show();


                                                       // Intent i=new Intent(UploadstoryDetails.this,adminpage.class);

                                                       // startActivity(i);

                                                    }
                                                    catch (Exception e )
                                                    {
                                                        //Toast.makeText(getApplicationContext()," Error "+e,Toast.LENGTH_SHORT).show();

                                                    }


                                                    SmsManager smsManager = SmsManager.getDefault();

                                                    ArrayList<String> parts =smsManager .divideMessage( sb.toString());

                                                    //smsManager.sendMultipartTextMessage(phone_number, null, parts, null, null);

                                                    // SmsManager smsManager = SmsManager.getDefault();
                                                    try {


                                                        smsManager.sendMultipartTextMessage(st_phone, null, parts, null, null);

                                                    }
                                                    catch(Exception e)
                                                    {
                                                        // Toast.makeText(this," Message Exception "+e,Toast.LENGTH_SHORT).show();
                                                    }



                                                }while(c.moveToNext());



                                    }






                                }while(c.moveToNext());

                            }
                        }
                    }
                    catch (Exception e){
                        // Toast.makeText(getApplicationContext(),"Exception in extracting "+e,Toast.LENGTH_SHORT).show();
                    }






                }
            });
    }










}
