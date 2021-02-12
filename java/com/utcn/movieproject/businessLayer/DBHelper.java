package com.utcn.movieproject.businessLayer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.utcn.movieproject.presentationLayer.model.ClientModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "movieDFV.db";
    public static final String CLIENTS_TABLE_NAME = "clients";
    public static final String CLIENT_COLUMN_ID = "id";
    public static final String CLIENT_COLUMN_NAME = "name";
    public static final String CLIENT_COLUMN_SURNAME = "surname";
    public static final String CLIENT_COLUMN_EMAIL = "email";
    public static final String CLIENT_COLUMN_PASSWORD = "password";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table clients " +
                        "(id integer primary key, name text, surname text,email text,password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS clients");
        onCreate(db);
    }

    public boolean insertClient(int id, String name,String surname, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues clientValues = new ContentValues();
        clientValues.put(CLIENT_COLUMN_ID, id);
        clientValues.put(CLIENT_COLUMN_NAME, name);
        clientValues.put(CLIENT_COLUMN_SURNAME, surname);
        clientValues.put(CLIENT_COLUMN_EMAIL, email);
        clientValues.put(CLIENT_COLUMN_PASSWORD, password);
        db.insert(CLIENTS_TABLE_NAME, null, clientValues);
        return true;
    }


    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CLIENTS_TABLE_NAME);
        return numRows;
    }

    public Integer deleteClient(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CLIENTS_TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public Boolean findClient(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select id from clients where email=?", new String[]{email});
        if (res.getCount() == 0)
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }

    public Boolean validateClient(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select id from clients where email=? and password=?", new String[]{email, password});
        if (res.getCount() == 0)
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }


    public ArrayList<ClientModel> getAllClients() {
        ArrayList<ClientModel> arr = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from clients", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            arr.add(new ClientModel(res.getInt(res.getColumnIndex(CLIENT_COLUMN_ID)),
                    res.getString(res.getColumnIndex(CLIENT_COLUMN_NAME)),
                    res.getString(res.getColumnIndex(CLIENT_COLUMN_SURNAME)),
                    res.getString(res.getColumnIndex(CLIENT_COLUMN_EMAIL)),
                    res.getString(res.getColumnIndex(CLIENT_COLUMN_PASSWORD))));
            res.moveToNext();
        }
        return arr;
    }
}
