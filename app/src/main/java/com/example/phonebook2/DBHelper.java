package com.example.phonebook2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {super(context,"PhoneBook", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table PhoneBook (id integer primary key autoincrement, name text, contact text)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(String name, String contact) {
        String query="insert into PhoneBook (name,contact) values('"+name+"','"+contact+"')";
        SQLiteDatabase database;
        database=getWritableDatabase();
        database.execSQL(query);
    }
}
