package com.example.lenovo.testfirstsqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.os.Build.ID;

public class MyDatabase extends SQLiteOpenHelper
{
    private static final String DatabaseName="MyDataBase";
    public static final String TableName="FirstTable";
    private static final int Version=1;
    public static final String COL1="ID";
    public static final String COL2="NAME";
    public static final String COL3="MOBILE_NO";
    public static final String COL4="ADDRESS";

    public MyDatabase(Context context)
    {
        super(context,DatabaseName, null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+TableName+"("+COL1+" TEXT,"+COL2+" TEXT,"+COL3+" TEXT,"+COL4+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor data=sqLiteDatabase.rawQuery("select *from "+TableName,null);
        return data;
    }
    public int delete(String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TableName,"ID = ?",new String[]{id});
    }
    public int updateData(String id,String name,String mob,String add)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL1,id);
        cv.put(COL2,name);
        cv.put(COL3,mob);
        cv.put(COL4,add);
        return sqLiteDatabase.update(TableName,cv,"ID =?",new String[]{id});
    }

}
