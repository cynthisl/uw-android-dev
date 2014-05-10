package com.cynthisl.Homework_252;

import android.app.ActionBar;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    private ArrayAdapter<String> mTaskAA;
    private TaskSQLiteOpenHelper mDbHelper;
    private SQLiteDatabase mTaskDB;

    private ActionBar mActionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // start empty
        //setEmptyText("No Tasks!");
        mDbHelper = new TaskSQLiteOpenHelper(getActivity());
        mTaskDB = mDbHelper.getWritableDatabase();
        // Just dump some data into the List
        ArrayList<Task> tasks = getAllTasks();
        ArrayList<String> tasks_names = new ArrayList<String>();
        for(int i=0; i<tasks.size(); i++){
            tasks_names.add(tasks.get(i).name);
        }
        mTaskDB.close();
        mTaskAA = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_list_item_1, tasks_names);
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

        Cursor c = mTaskDB.query(Task.TABLE_NAME,
                new String[]{Task.TABLE_ROW_NAME},
                null, null, null, null, null);

        //c.moveToFirst();
        while(c.moveToNext()){
            Task t = new Task();
            t.name = c.getString(0);
            tasks.add(t);
        }
        c.close();

        return tasks;
    }


}