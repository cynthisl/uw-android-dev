package com.cynthisl.Homework_252;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Cynthia on 5/10/2014.
 */
public class TaskDisplayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_taskdisplay, container, false);
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