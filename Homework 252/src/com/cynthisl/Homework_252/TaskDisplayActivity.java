package com.cynthisl.Homework_252;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Cynthia on 5/8/2014.
 */
public class TaskDisplayActivity extends Activity {

    Task mTask;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdisplay);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        setTask(id);

    }


    public void setTask(int id){
        TextView tv = (TextView) findViewById(R.id.task_display_text);
        mTask = getTaskFromDB(id);
        tv.setText(mTask.name);
    }

    private Task getTaskFromDB(int id){

        Task t = new Task();
        TaskSQLiteOpenHelper mDbHelper = new TaskSQLiteOpenHelper(this);
        SQLiteDatabase mTaskDB = mDbHelper.getReadableDatabase();

        Cursor c = mTaskDB.query(Task.TABLE_NAME,
                new String[]{Task.TABLE_ROW_ID, Task.TABLE_ROW_NAME},
                "id=" + id,
                null, null, null, null);


        while(c.moveToNext()){
            t.id = c.getInt(0);
            t.name = c.getString(1);
        }
        c.close();

        return t;

    }
}