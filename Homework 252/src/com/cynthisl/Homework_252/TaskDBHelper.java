package com.cynthisl.Homework_252;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Cynthia on 5/11/2014.
 */
public class TaskDBHelper {

    private TaskSQLiteOpenHelper mDbHelper;
    private SQLiteDatabase mTaskDB;

    TaskDBHelper(Context context) {
        mDbHelper=new TaskSQLiteOpenHelper(context);
    }

    /**
     * Adds task to database. Assigns id number when added.
     * @param t
     */
    public void addTask(Task t){

        mTaskDB = mDbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Task.TABLE_ROW_NAME, t.name);

        mTaskDB.insert(Task.TABLE_NAME, null, cv);
        mTaskDB.close();
    }

    /**
     * deletes task matching selected id from db
     * @param id
     */
    public void deleteTask(int id){
        // delete the task
        mTaskDB = mDbHelper.getWritableDatabase();
        mTaskDB.delete(Task.TABLE_NAME, "id="+id, null);
        mTaskDB.close();
    }

    /**
     * gets all tasks in database
     * @return ArrayList<Task>
     */
    ArrayList<Task> getAllTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();

        mTaskDB = mDbHelper.getReadableDatabase();
        Cursor c = mTaskDB.query(Task.TABLE_NAME,
                new String[]{Task.TABLE_ROW_ID,Task.TABLE_ROW_NAME},
                null, null, null, null, null);

        while(c.moveToNext()){
            Task t = new Task();
            t.id = c.getInt(0);
            t.name = c.getString(1);
            tasks.add(t);
        }
        c.close();
        mTaskDB.close();

        return tasks;
    }

    /**
     * gets task given id from db
     * @param id
     * @return Task
     */
    Task getTaskFromID(int id){

        Task t = new Task();
        mTaskDB = mDbHelper.getReadableDatabase();

        Cursor c = mTaskDB.query(Task.TABLE_NAME,
                new String[]{Task.TABLE_ROW_ID, Task.TABLE_ROW_NAME},
                "id=" + id,
                null, null, null, null);

        while(c.moveToNext()){
            t.id = c.getInt(0);
            t.name = c.getString(1);
        }
        c.close();
        mTaskDB.close();

        return t;
    }
}
