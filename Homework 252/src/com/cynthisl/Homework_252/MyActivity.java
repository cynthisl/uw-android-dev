package com.cynthisl.Homework_252;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.DialogInterface;
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

                                mDbHelper = new TaskSQLiteOpenHelper(getApplicationContext());
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
    public void showDetail(){

        //Intent i = new Intent(this, TaskDisplayActivity.class);
        //startActivity(i);

        Fragment details = getFragmentManager().findFragmentById(R.id.taskDisplayFragment);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.taskDisplayFragment, details);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }


}
