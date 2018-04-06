package com.example.lenovo.jobscheduler_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by LENOVO on 26-03-2018.
 */

public class MyDataBase extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public static final String DATABASE_NAME="DATA.db";

    public static final String TABLE_NAME="MY_TABLE";

    public static final String COL_1="ID";
    public static final String COL_2="NAME";

    public MyDataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_NAME);

        onCreate(db);

    }
    public boolean InsertData (String _name, String _phone_no){

        db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, _name);

        long result=db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if(result==-1){

            Log.e("insert","no data");

            return false;
        }
        else {

            Log.e("insert","data");

            return true;
        }
    }

    public Cursor getData()
    {
        db=this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME,null);

                /*if(){

                }*/
        return res;
    }

}
