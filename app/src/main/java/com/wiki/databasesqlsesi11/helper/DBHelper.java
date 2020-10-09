package com.wiki.databasesqlsesi11.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DBHelper(Context context){
        super ((Context) context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }

    public ArrayList<HashMap<String, String>> getAllData (){
        ArrayList<HashMap<String, String>> wordlist ;
        wordlist = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c  = db.rawQuery(selectQuery, null);
        if ( c.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(KEY_ID, c.getString(0));
                map.put(KEY_NAME, c.getString(1));
                map.put(KEY_PH_NO, c.getString(2));
                wordlist.add(map);
            }while (c.moveToNext());
        }
        Log.e("select data", "" + wordlist);
        db.close();
        return null;
    }


    public void insert(String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase() ;
    String query = "INSERT INTO " + TABLE_CONTACTS  + " (name, address) " +
            " VALUES ('" + name +"', '" + phone + "')";

    Log.e("insert sqlite", "" + query);
    db.execSQL(query);
    db.close();
    }

    public void update(int id, String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_CONTACTS +" SET "
        + KEY_NAME + "='" + name + "',"
        + KEY_PH_NO + "='" + phone + "'"
        + " WHERE " + KEY_ID + "=" + "'" + id + "'";

        Log.e("update data", updateQuery);
        db.execSQL(updateQuery);
        db.close();

    }
    public  void  delete (int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + "=" + id + "'";
        Log.e("update Data", updateQuery);
        db.execSQL(updateQuery);
        db.close();
    }

}
