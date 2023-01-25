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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxtname=findViewById(R.id.etxtname);
        etxtcontact=findViewById(R.id.etxtcontact);
        btnsubmit=findViewById(R.id.btnsubmit);
        btndisplay=findViewById(R.id.btndisplay);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxtname.getText().toString();
                String contact = etxtcontact.getText().toString();

                DBHelper dbHelper=new DBHelper(MainActivity.this);
                dbHelper.insertData(name, contact);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
                finish();


            }
        });
    }
}