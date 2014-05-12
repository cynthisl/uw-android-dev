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
    TaskDBHelper mTaskDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTask = null;
        mTaskDBHelper = new TaskDBHelper(getActivity());
        return inflater.inflate(R.layout.fragment_taskdisplay, container, false);
    }

    /**
     * sets task displayed by this fragment
     * @param id task id
     */
    public void setTask(int id){
        TextView tv = (TextView) getView().findViewById(R.id.task_display_text);
        mTask = mTaskDBHelper.getTaskFromID(id);
        tv.setText(mTask.name);
    }

}