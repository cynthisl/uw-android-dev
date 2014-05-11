package com.cynthisl.Homework_252;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Cynthia on 5/10/2014.
 */
public class TaskDisplayFragment extends Fragment {
    public Task mTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTask = null;
        return inflater.inflate(R.layout.fragment_taskdisplay, container, false);
    }

    public void setTask(int id){
        TextView tv = (TextView) getView().findViewById(R.id.task_display_text);
        mTask = getTaskFromDB(id);
        tv.setText(mTask.name);
    }

    private Task getTaskFromDB(int id){

        Task t = new Task();
        TaskSQLiteOpenHelper mDbHelper = new TaskSQLiteOpenHelper(getActivity());
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