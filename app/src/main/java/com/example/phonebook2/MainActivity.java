package com.example.phonebook2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etxtname,etxtcontact;
    Button btnsubmit,btndisplay;
    int id;

    String txt1,txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtname=findViewById(R.id.etxtname);
        etxtcontact=findViewById(R.id.etxtcontact);
        btnsubmit=findViewById(R.id.btnsubmit);
        btndisplay=findViewById(R.id.btndisplay);
        if(getIntent().getExtras()!=null)
        {
            id = getIntent().getIntExtra("Id", 0);
            txt1 = getIntent().getStringExtra("Name");
            txt2 = getIntent().getStringExtra("Contact");
            System.out.println("id=" + id);
            etxtname.setText("" + txt1);
            etxtcontact.setText("" + txt2);


            findViewById(R.id.btndisplay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt1 = etxtname.getText().toString();
                    String txt2 = etxtcontact.getText().toString();

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            });
        }

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxtname.getText().toString();
                String contact = etxtcontact.getText().toString();
                DBHelper dbHelper=new DBHelper(MainActivity.this);
                if(getIntent().getExtras()==null)
                {
                        dbHelper.insertData(name, contact);
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                }
                else if(getIntent().getExtras()!=null)
                {
                    DBHelper dbHelper1=new DBHelper(MainActivity.this);
                    String str1=etxtname.getText().toString();
                    String str2=etxtcontact.getText().toString();
                    dbHelper1.updateData(id, str1, str2);
                    System.out.println("id="+id+"\tName="+str1+"\tContact="+str2);
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}