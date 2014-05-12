package com.cynthisl.Homework_252;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;

public class MyActivity extends Activity {

    TaskListFragment mTaskListFragment;
    TaskDisplayFragment mTaskDescriptionFragment;
    TaskDBHelper mTaskDBHelper;
    boolean isDoublePaned;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTaskListFragment = (TaskListFragment)getFragmentManager().findFragmentById(R.id.taskListFragment);
        mTaskDescriptionFragment = (TaskDisplayFragment)getFragmentManager().findFragmentById(R.id.taskDisplayFragment);
        // set variable for if double pane or not
        if(mTaskDescriptionFragment != null){
            isDoublePaned = true;
        }
        else{
            isDoublePaned = false;
        }

        mTaskDBHelper = new TaskDBHelper(this);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if(isDoublePaned){
            // both buttons if two pane
            menu.findItem(R.id.menu_action_delete).setVisible(true);
            menu.findItem(R.id.menu_action_new).setVisible(true);
        }
        else{
            // only add, doesn't make sense to delete on one pane
            menu.findItem(R.id.menu_action_new).setVisible(true);
        }
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
            case R.id.menu_action_new:
                addNewTask();
                return true;
            case R.id.menu_action_delete:
                deleteTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        mTaskListFragment.refreshList();
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
                                Task t = new Task();
                                t.name = userInput.getText().toString();
                                mTaskDBHelper.addTask(t);

                                // refresh task list
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
        if(isDoublePaned) {
            TaskDisplayFragment task_display = (TaskDisplayFragment) getFragmentManager().findFragmentById(R.id.taskDisplayFragment);
            if (task_display != null) {
                Task t = task_display.mTask;
                if (t != null) {
                    // delete the task
                    mTaskDBHelper.deleteTask(t.id);
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
        //if not double paned, nothing selected

    }

    public void showDetail(int task_id){

        if(isDoublePaned){
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
