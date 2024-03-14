package com.example.seatt.placementapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Stafflogin extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
   int fg=0,fg1=0;
    Cursor c;
    String user,pass;
    String staffid,password,dept;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stafflogin);
        e1=(EditText)findViewById(R.id.e1);
        e2=(EditText)findViewById(R.id.e2);
        b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int status=0;


                if(e1.length()==0) {
                    fg = 1;
                    e1.setHint("Username");
                    e1.setHintTextColor(Color.RED);


                }
                fg1=0;
                if(e2.length()==0) {
                    fg1 = 1;
                    e2.setHint("Password");
                    e2.setHintTextColor(Color.RED);


                }
                if(fg==0 && fg1==0) {
                    user = e1.getText().toString();
                    pass = e2.getText().toString();
                    SQLiteDatabase db=openOrCreateDatabase("student", Context.MODE_PRIVATE,null);
                    try{

                        c=db.rawQuery("SELECT * from staffinfo",null);
                        if(c.moveToFirst())
                        {
                            do{
                               staffid=c.getString(c.getColumnIndexOrThrow("staffid"));
                                password=c.getString(c.getColumnIndexOrThrow("password"));




                                if(staffid.equals(user) && password.equalsIgnoreCase(pass))
                                {

                                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();

                                    Intent i=new Intent(Stafflogin.this,staffpage.class);
                                    i.putExtra("staff_id", staffid);
                                    startActivity(i);


                                }
                                else
                                {


                                  //  status=0;
                                    //e1.setHint("Username ");
                                    //e2.setHint("Password");
                                    //=1;
                                    //Toast.makeText(getApplicationContext(),"Invalid username/password.",Toast.LENGTH_LONG).show();
                                }

                            }while(c.moveToNext());

                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext()," Exception in extracting "+e,Toast.LENGTH_LONG).show();
                    }


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter the user name and password",Toast.LENGTH_LONG).show();
                }





            }
        });
    }
}
