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

public class AddstudentDetails extends AppCompatActivity implements View.OnClickListener{
    EditText e1, e2, e3, e4, e5, e6,deptinfo;
    ImageView imageView;
    FileOutputStream fileoutputstream;
    ByteArrayOutputStream b1;
    File file;
    String photoPath = null;
    byte[] img, img1;
    public final static int QRcodeWidth = 500 ;
    String rp;
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
        setContentView(R.layout.activity_add_student_details);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        e1 = (EditText) findViewById(R.id.e1);
        e2 = (EditText) findViewById(R.id.e2);
        e3 = (EditText) findViewById(R.id.e3);
        e4 = (EditText) findViewById(R.id.e4);
        e5 = (EditText) findViewById(R.id.e5);
        e6 = (EditText) findViewById(R.id.e6);
        s1 = (Spinner) findViewById(R.id.s1);
        b11 = (Button) findViewById(R.id.button);


        b12 = (Button) findViewById(R.id.b1);
        s2=(Spinner)findViewById(R.id.spinner);


        dept.add("Bsc-cs");
        dept.add("Bsc-ct");
        dept.add("Bca");

        dept.add("MCA");
        dept.add("Msc-cs");
        dept.add("Msc-IT");

        gend.add("Male");
        gend.add("Female");
        sp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gend);
        sp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(sp);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t1 = (String) s1.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Gender:" + t1, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



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



        fg2 = 0;
        try {

            e3.setInputType(InputType.TYPE_NULL);
            e3.requestFocus();
            e3.setOnClickListener((View.OnClickListener) this);
            newCalendar = Calendar.getInstance();

            fromDatePickerDialog2 = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    newDate = Calendar.getInstance();
                    newDate.set(year, monthOfYear, dayOfMonth);

                    e3.setText(dateFormatter.format(newDate.getTime()));
                    fg2 = 1;
                    xyr = newDate.get(Calendar.YEAR);
                    xday = newDate.get(Calendar.DATE);
                    xmonth = newDate.get(Calendar.MONTH) + 1;
                    // Toast.makeText(getApplicationContext(), "Year "+xyr+" "+xday+" "+xmonth,Toast.LENGTH_SHORT).show();


                }

            }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        } catch (Exception e) {
            Toast.makeText(this, "Exception" + e, Toast.LENGTH_SHORT).show();
        }



        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);


            }
        });





        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    SQLiteDatabase db=openOrCreateDatabase("student", Context.MODE_PRIVATE,null);
                    try{

                        String q1="CREATE TABLE studentinfo(staffid TEXT PRIMARY KEY,name TEXT,gender TEXT,day INTEGER,month INTEGER,year INTEGER,phone TEXT,classs TEXT,address TEXT,password TEXT,dept TEXT);";
                        db.execSQL(q1);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Exception in creating table "+e,Toast.LENGTH_SHORT).show();
                    }




                SQLiteDatabase mydb=openOrCreateDatabase("student",Context.MODE_PRIVATE,null);

                int rn = rand.nextInt((1000 - 10) + 1) + 10;




                sms= SmsManager.getDefault();

                try{
                    //Toast.makeText(getApplicationContext(),"check phone "+phone,Toast.LENGTH_SHORT).show();


                   // sms.sendTextMessage(e4.getText().toString(), null,"Hi "+e2.getText().toString()+" Your login id:"+e1.getText().toString()+" .Password:"+rn, null, null);
                    //Toast.makeText(getApplicationContext()," Message Sent Successfully.",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Excepion in sending sms"+e,Toast.LENGTH_SHORT).show();
                }




                String insert="INSERT INTO studentinfo(staffid,name,gender,day,month,year,phone,classs,address,password,dept) VALUES('"+e1.getText().toString()+"','"+ e2.getText().toString()+"','"+t1+"','"+xday+"','"+xmonth+"','"+xyr+"','"+e4.getText().toString()+"','"+classs+"','"+e6.getText().toString()+"','"+rn+"','"+t2+"')";
                try{
                    mydb.execSQL(insert);
                    Toast.makeText(getApplicationContext(),"Student Details Entry success ",Toast.LENGTH_SHORT).show();


                    Intent i=new Intent(AddstudentDetails.this,Adminpage.class);

                    startActivity(i);



                }
                catch (Exception e )
                {
                   // Toast.makeText(this,"Exeption in inserting "+e,Toast.LENGTH_SHORT).show();
                }






            }
        });
    }


    @Override
    public void onClick(View v) {
        if(v==e3)
            fromDatePickerDialog2.show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){


            try {
                if (resultCode == RESULT_OK)
                {
                    Uri uri = data.getData();
                    String[] projection = { MediaStore.Images.Media.DATA };
                    Cursor cursor = managedQuery(uri, projection, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path= cursor.getString(column_index);
                    Toast.makeText(getApplicationContext(), "path info" + path, Toast.LENGTH_SHORT).show();

                    // Toast.makeText(getApplicationContext(), path, 500).show();

                    try {
                        File f= new File(path);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    insert(bitmap);

                    if (bitmap != null) {
                        bitmap.recycle();
                        bitmap = null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    private void insert(Bitmap bitmap) {



        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        img = stream.toByteArray();

        String stfff_Id=e1.getText().toString().concat(".png");
        file = new File(Environment.getExternalStorageDirectory() + "/" + stfff_Id);

        rp = Environment.getExternalStorageDirectory() + "/" + stfff_Id;

        //  Toast.makeText(getApplicationContext(), "path info" + rp, Toast.LENGTH_SHORT).show();


        try

        {
            file.createNewFile();

            fileoutputstream = new FileOutputStream(file);

            fileoutputstream.write(stream.toByteArray());

            fileoutputstream.close();
            // Toast.makeText(getApplicationContext(), "path info" + rp, Toast.LENGTH_SHORT).show();


            // Toast.makeText(this,"Path "+rp,Toast.LENGTH_SHORT).show();


        } catch (Exception e)

        {

            e.printStackTrace();

        }




    }





}
