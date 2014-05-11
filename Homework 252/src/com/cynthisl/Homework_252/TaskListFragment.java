package com.cynthisl.Homework_252;

import android.app.ActionBar;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Cynthia on 5/5/2014.
 */
public class TaskListFragment extends ListFragment {

    public final static String TAG = "TaskListFragment";
    //public ArrayAdapter<String> mTaskAA;
    public TaskAdapter mTaskAA;
    private TaskSQLiteOpenHelper mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("TASK_FRAGMENT", "onCreate called");
        super.onCreate(savedInstanceState);

        mDbHelper = new TaskSQLiteOpenHelper(getActivity());
        ArrayList<Task> tasks = getAllTasks();
        mTaskAA = new TaskAdapter (getActivity(), android.R.layout.simple_list_item_1, tasks);
        setListAdapter(mTaskAA);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tasklist, container, false);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {

        ((MyActivity) getActivity()).showDetail();

        super.onListItemClick(listView, view, position, id);
    }

    public ArrayList<Task> getAllTasks(){
        ArrayList<Task> tasks = new ArrayList<Task>();

        SQLiteDatabase mTaskDB = mDbHelper.getReadableDatabase();
        Cursor c = mTaskDB.query(Task.TABLE_NAME,
                new String[]{Task.TABLE_ROW_ID,Task.TABLE_ROW_NAME},
                null, null, null, null, null);

        //c.moveToFirst();
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

    public void refreshList(){
        ArrayList<Task> tl;
        SQLiteDatabase mTaskDB = mDbHelper.getReadableDatabase();
        tl = getAllTasks();mTaskAA = new TaskAdapter (getActivity(), android.R.layout.simple_list_item_1, tl);
        setListAdapter(mTaskAA);
        //mTaskAA.notifyDataSetChanged();
    }

    //use local broadcast manager


}