package com.dblab.yeom.fibonacci;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Yeom on 2017-12-06.
 */

public class DBManager extends SQLiteOpenHelper{
    ArrayList<String> history_list = new ArrayList<String>();
    Cursor cursor;
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE RESULTBOOK (_id INTEGER PRIMARY KEY AUTOINCREMENT, result TEXT);");

    }

    public void insert(ArrayList<Long> result){
        String m_result = "";
        for (int i = 0; i<result.size();i++){
            m_result += ", "+result.get(i).toString();

        }

        history_list.add(m_result);

        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("INSERT INTO RESULTBOOK VALUES(null,'"+ m_result +"');");
    }

    public ArrayList<String> getResult()
    {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        cursor = db.rawQuery("SELECT * FROM RESULTBOOK", null);
        while (cursor.moveToNext())
        {

            history_list.add(cursor.getString(1));
        }
        return history_list;
    }

}
