package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseAdapter {

    myHelper helper;
    public myDatabaseAdapter(Context context){
        helper = new myHelper(context);
    }

    public long inserData(String name, String password){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("password",password);
        long data = db.insert(myHelper.TABLE_NAME,null,contentValues);
        return data;
    }

    static class myHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "dinas_lingkungan_hidup";
        private static final String TABLE_NAME = "admin";
        private Context context;

        public myHelper(Context context){
            super(context,DATABASE_NAME,null,3);
            this.context  = context;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
