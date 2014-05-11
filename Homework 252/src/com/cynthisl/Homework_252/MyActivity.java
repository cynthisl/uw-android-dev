package com.cynthisl.Homework_252;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;

public class MyActivity extends Activity {


    private TaskSQLiteOpenHelper mDbHelper;
    private SQLiteDatabase mTaskDB;
    TaskListFragment mTaskListFragment;
    TaskDisplayFragment mTaskDescriptionFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTaskListFragment = (TaskListFragment)getFragmentManager().findFragmentById(R.id.taskListFragment);
        mTaskDescriptionFragment = (TaskDisplayFragment)getFragmentManager().findFragmentById(R.id.taskDisplayFragment);
        mDbHelper = new TaskSQLiteOpenHelper(getApplicationContext());

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
            case R.id.action_new:
                addNewTask();
                return true;
            case R.id.action_delete:
                deleteTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewTask(){

        LayoutInflater li = LayoutInflater.from(this);
        View promptView = li.inflate(R.layout.new_task_prompt, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptView);

        final EditText userInput = (EditText) promptView
                .findViewById(R.id.newTaskPromptInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {

                                mTaskDB = mDbHelper.getWritableDatabase();

                                ContentValues cv = new ContentValues();
                                cv.put(Task.TABLE_ROW_NAME, userInput.getText().toString());

                                mTaskDB.insert(Task.TABLE_NAME, null, cv);
                                mTaskDB.close();

                                mTaskListFragment.refreshList();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }

    private void deleteTask(){
        TaskDisplayFragment task_display = (TaskDisplayFragment) getFragmentManager().findFragmentById(R.id.taskDisplayFragment);
        if(task_display != null) {
            Task t = task_display.mTask;
            if(t != null){
                // delete the task
                mTaskDB = mDbHelper.getWritableDatabase();
                mTaskDB.delete(Task.TABLE_NAME, "id="+t.id, null);
                mTaskDB.close();

                mTaskListFragment.refreshList();

                //clear the fragment
                TaskDisplayFragment placeholder = new TaskDisplayFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.taskDisplayFragment, placeholder)
                        .commit();
            }
        }

    }
    public void showDetail(int task_id){

        if(mTaskDescriptionFragment != null){
            // double paned, add to fragment
            TaskDisplayFragment details = (TaskDisplayFragment) getFragmentManager().findFragmentById(R.id.taskDisplayFragment);
            details.setTask(task_id);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.taskDisplayFragment, details)
                    .commit();
        }
        else {
            // no other fragment - launch new activity
            Intent i = new Intent(this, TaskDisplayActivity.class);
            i.putExtra("id", task_id);
            startActivity(i);
        }


    }


}
