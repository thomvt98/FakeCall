package com.vuthithom.fakecall.fakecall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS TABLE_CALL" +
            "(" +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Ten VARCHAR(150), " +
            "Phone VARCHAR(250)," +
            "Hinhanh BLOB" +
            ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        QueryData(CREATE_TABLE);
    }

    public void QueryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void INSERT_CALL(String name,String phone,byte[] hinh){
        SQLiteDatabase database=getWritableDatabase();
        String sql="INSERT INTO TABLE_CALL VALUES(null,?,?,?)";
        SQLiteStatement sqLiteStatement=database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1,name);
        sqLiteStatement.bindString(2,phone);
        sqLiteStatement.bindBlob(3,hinh);
        sqLiteStatement.executeInsert();
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    }
