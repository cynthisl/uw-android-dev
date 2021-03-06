package com.cynthisl.Homework_252;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Cynthia on 5/8/2014.
 */
public class TaskSQLiteOpenHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mTasksDB;
    public final static String TABLE_NAME = "tasks";
    public final static String TABLE_ROW_ID = "id";
    public final static String TABLE_ROW_NAME = "name";

    public TaskSQLiteOpenHelper(Context context)
    {
        super(context, "tasks_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE "+TABLE_NAME+
                " ("+TABLE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    TABLE_ROW_NAME+" TEXT);";

        sqLiteDatabase.execSQL(createTableQuery);

        ContentValues cv = new ContentValues();
        cv.put(TABLE_ROW_NAME, "finish homework 252");
        sqLiteDatabase.insert(TABLE_NAME, null, cv);
        cv.clear();

        mTasksDB = sqLiteDatabase;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}