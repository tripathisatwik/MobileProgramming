package com.example.chapter7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydb.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String createQuery  = "CREATE TABLE mytable(id INTEGER PRIMARY KEY,name TEXT,address TEXT)";
        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long insertData(int id, String name, String address){
        SQLiteDatabase db = this.getWritableDatabase();
        //Checks if id already exists
        Cursor cursor = db.rawQuery("SELECT id FROM mytable WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor.getCount()>0){
            //Show prompt/message to user
            Toast.makeText(context,"ID already exists! Please use a digfferent ID",Toast.LENGTH_LONG).show();
        }else{
            //Proceed with insert
            ContentValues contentValues = new ContentValues();
            contentValues.put("id",id);
            contentValues.put("name",name);
            contentValues.put("address",address);

            db.insert("mytable",null,contentValues);
            Toast.makeText(context,"Data inserted sucessfully",Toast.LENGTH_LONG).show();
        }
        return 0;
    }
    public Cursor selectData(){
        SQLiteDatabase db  = this.getWritableDatabase();
        String query = "SELECT * FROM mytable";
        return db.rawQuery(query,null);
    }
    public int updateData(String id, String name, String address){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        db.update("mytable",contentValues,"id=?",new String[]{id});
        db.close();
        return 0;
    }
    public int deleteData(String id){
        SQLiteDatabase db  = this.getWritableDatabase();
        db.delete("mytable","id=?",new String[]{id});
        return 0;
    }
}
