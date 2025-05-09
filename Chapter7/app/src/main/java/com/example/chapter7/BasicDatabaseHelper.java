package com.example.chapter7;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BasicDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "SimpleDB_db";
    private static final int DATABASE_VESION = 1;
    public BasicDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public void insertUser(String name){
        SQLiteDatabase db =  getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        db.insert("users",null,values);
        db.close();
    }

    public ArrayList<String> getALlUsers(){
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users",null);

        if(cursor.moveToFirst()){
            do{
                int index = cursor.getColumnIndex("name");
                if(index != -1){
                    list.add(cursor.getString(index));
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
