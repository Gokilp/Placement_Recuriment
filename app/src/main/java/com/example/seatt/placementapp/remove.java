package com.example.seatt.placementapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class remove extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    String user,pass,dbpass,dbuser,dbpass1,dbuser1;
    int fg=0;
    int flg=0;
    String empty="";
    Cursor c,c1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        e1=(EditText)findViewById(R.id.usrusr);


        b1=(Button)findViewById(R.id.sin);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isAllFieldsChecked = false;
                flg=0;
                fg=0;
                int status=0;
                user=e1.getText().toString();

                isAllFieldsChecked  = CheckAllFields();

              if(isAllFieldsChecked)
                {
                    SQLiteDatabase db = openOrCreateDatabase("student", Context.MODE_PRIVATE, null);

                    try {

                        if (db != null) {


                            db.execSQL("Delete from staffinfo where staffid='"+e1.getText().toString()+"'");
                            Toast.makeText(getApplicationContext(), " Staff Details Record Remove Succesfully" , Toast.LENGTH_SHORT).show();


                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Exception in extracting " + e, Toast.LENGTH_SHORT).show();
                    }




                }


                }


        });


    }

    private boolean CheckAllFields() {

        if (e1.length() == 0) {
            e1.setError("UserName field is required");
            return false;
        }


        // after all validation return true.
        return true;
    }













}
