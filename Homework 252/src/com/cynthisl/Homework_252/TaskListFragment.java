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
    TaskDBHelper mTaskDBHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("TASK_FRAGMENT", "onCreate called");
        super.onCreate(savedInstanceState);

        mTaskDBHelper = new TaskDBHelper(getActivity());
        ArrayList<Task> tasks = mTaskDBHelper.getAllTasks();
        mTaskAA = new TaskAdapter (getActivity(), android.R.layout.simple_list_item_1, tasks);
        setListAdapter(mTaskAA);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tasklist, container, false);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {

        int task_id = mTaskAA.getItem(position).id;
        ((MyActivity) getActivity()).showDetail(task_id);

        super.onListItemClick(listView, view, position, id);
    }

    /**
     * gets all tasks from DB and refreshes list
     */
    public void refreshList(){
        ArrayList<Task> tl;
        tl = mTaskDBHelper.getAllTasks();
        mTaskAA = new TaskAdapter (getActivity(), android.R.layout.simple_list_item_1, tl);
        setListAdapter(mTaskAA);
    }

    //use local broadcast manager


}