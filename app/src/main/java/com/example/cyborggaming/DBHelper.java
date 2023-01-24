package com.example.cyborggaming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DBHelper extends SQLiteOpenHelper {
    public static final String userdata = "userdata.db";

    public DBHelper(Context context) {
        super(context, "userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, password TEXT)");
        DB.execSQL("create Table Gamedetails(game_name TEXT primary key, genre_name TEXT, developer_name TEXT, price TEXT, stock TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists Gamedetails");
    }

    public Boolean insertuserdata(String name, String pass) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", pass);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertgamedata(String gameName, String genreName, String developerName, String price, String stock) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("game_name", gameName);
        contentValues.put("genre_name", genreName);
        contentValues.put("developer_name", developerName);
        contentValues.put("price", price);
        contentValues.put("stock", stock);
        long result = DB.insert("Gamedetails", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Gamedetails", null);
        return cursor;
    }

    public Boolean deletedata(String gameName) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Gamedetails WHERE game_name = ?", new String[]{gameName});
        if (cursor.getCount()>0){
            long result = DB.delete("Gamedetails", "game_name=?", new String[] {gameName});
            if(result==-1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public Boolean updategamedetails(String gameName, String genreName, String developerName, String price, String stock){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("genre_name", genreName);
        contentValues.put("developer_name", developerName);
        contentValues.put("price", price);
        contentValues.put("stock", stock);
        Cursor cursor = DB.rawQuery("SELECT * FROM Gamedetails where game_name=?", new String[]{gameName});
        if (cursor.getCount()>0){
            long result = DB.update("Gamedetails", contentValues, "game_name=?", new String[]{gameName});
            if (result == -1){
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean checkname(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[] {name});
        if(cursor.getCount() > 0)
                return true;
            else
                return false;
    }

    public Boolean checknamepassword(String name, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ? and password = ?", new String[]{name,password});
        if (cursor.getCount() > 0)
            return true;
           else
            return false;
    }

}