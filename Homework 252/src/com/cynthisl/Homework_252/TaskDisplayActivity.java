package com.cynthisl.Homework_252;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Cynthia on 5/8/2014.
 */
public class TaskDisplayActivity extends Activity {

    Task mTask;
    TaskDBHelper mTaskDBHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdisplay);
        mTaskDBHelper = new TaskDBHelper(this);

        Bundle b = getIntent().getExtras();
        int id = b.getInt("id");
        setTask(id);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.menu_action_delete).setVisible(true);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_action_delete:
                deleteTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * set the task id for this activity.
     * if no task set, this activity will be blank.
     * @param id
     */
    public void setTask(int id){
        TextView tv = (TextView) findViewById(R.id.task_display_text);
        mTask = mTaskDBHelper.getTaskFromID(id);
        tv.setText(mTask.name);
    }

    /**
     * deletes the task of this activity
     */
    public void deleteTask(){
        if(mTask != null){
            mTaskDBHelper.deleteTask(mTask.id);
            finish();
        }
    }

}