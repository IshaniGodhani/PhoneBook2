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
    RecyclerView recyclerView;
    int id;
    String name;
    String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtname=findViewById(R.id.etxtname);
        etxtcontact=findViewById(R.id.etxtcontact);
        btnsubmit=findViewById(R.id.btnsubmit);
        btndisplay=findViewById(R.id.btndisplay);
        if(getIntent().getExtras()!=null) {
            int id = getIntent().getIntExtra("Id", 0);
            String name = getIntent().getStringExtra("Name");
            String contact = getIntent().getStringExtra("Contact");
            System.out.println("id=" + id);
            etxtname.setText("" + name);
            etxtcontact.setText("" + contact);

            findViewById(R.id.btndisplay).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(getIntent().getExtras()!=null)
                    {
                        String txt1 = etxtname.getText().toString();
                        String txt2 = etxtcontact.getText().toString();
                        DBHelper dbHelper=new DBHelper(MainActivity.this);
                        dbHelper.updateData(id, txt1, txt2);
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }

                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
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
                    dbHelper.insertData(name,contact);
                    Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}