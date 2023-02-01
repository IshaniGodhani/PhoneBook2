package com.example.phonebook2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    Recycler_Adapter recyclerAdapter;
    ArrayList<User> userList= new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        fab = findViewById(R.id.fab);



        DBHelper dbHelper = new DBHelper(MainActivity2.this);
        Cursor cursor = dbHelper.viewData();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String contact = cursor.getString(2);
            User user = new User(id, name, contact);
            userList.add(user);
        }

         recyclerAdapter= new Recycler_Adapter(this, userList);
        recyclerView= findViewById(R.id.list_item);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity2.this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }


}
