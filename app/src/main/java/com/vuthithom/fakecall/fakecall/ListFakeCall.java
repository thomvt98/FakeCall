package com.vuthithom.fakecall.fakecall;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.vuthithom.fakecall.R;

import java.util.ArrayList;

public class ListFakeCall extends AppCompatActivity{
 public static DatabaseHelper databaseHelper;
 ListView lvcall;
 ArrayList<Item_FakeCall> arrayList;
 AdapTerFakeCall adapTerCall;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fakecall);
        lvcall=findViewById(R.id.lvfakecall);
        arrayList=new ArrayList<>();
        adapTerCall=new AdapTerFakeCall(this,R.layout.item_fakecall,arrayList);
        lvcall.setAdapter(adapTerCall);

        databaseHelper=new DatabaseHelper(this,"Call.sqlite",null,1);
        databaseHelper.QueryData("CREATE TABLE IF NOT EXISTS TABLE_CALL(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150),Phone VARCHAR(250),Hinhanh BLOB)");

        Cursor cursor=databaseHelper.GetData("SELECT * FROM TABLE_CALL");
        while (cursor.moveToNext()){
            arrayList.add(new Item_FakeCall(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3)));
        }
        adapTerCall.notifyDataSetChanged();

    }
}
