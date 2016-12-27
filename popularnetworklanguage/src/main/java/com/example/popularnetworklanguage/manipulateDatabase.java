package com.example.popularnetworklanguage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/25.
 */

public class manipulateDatabase {
    private ArrayList<Words>lst;
    private SQLiteDatabase database;
    public manipulateDatabase(File file) {
        database=SQLiteDatabase.openOrCreateDatabase(file,null);
    }

    /**
     * 读取database 文件然后进行操作
     * @return
     */
    public ArrayList readDataBase(){
        lst=new ArrayList<Words>();
        Cursor cursor=database.rawQuery("SELECT * FROM liuxingyu",null);
        while(cursor.moveToNext()){

            String name=cursor.getString(cursor.getColumnIndex("name"));
            String explanation=cursor.getString(cursor.getColumnIndex("explanation"));
            Words word=new Words(name,explanation);
            lst.add(word);
            Log.d("app", "readDataBase:one word added ");
        }
        Log.d("app", "readDataBase:success ");
        return lst;
    }
}
