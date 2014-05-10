package com.cynthisl.Homework_252;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MyActivity extends Activity {

    private TaskListFragment mTaskListFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Add fragment
        mTaskListFragment = (TaskListFragment) getFragmentManager().findFragmentByTag("TaskListFragment");
        if (mTaskListFragment == null) {
            mTaskListFragment = new TaskListFragment();

            if (mTaskListFragment.isAdded()) {

                getFragmentManager()
                        .beginTransaction()
                        .show(mTaskListFragment)
                        .commit();

            } else {

                /*getFragmentManager()
                        .beginTransaction()
                        .add(R.id.titlePlaceholder, mTaskListFragment, "TaskListFragment")
                        .commit();
               */
            }

        }

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
                return true;
            case R.id.action_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewTask(){


    }
    public void showDetail(){

        Intent i = new Intent(this, TaskDisplayActivity.class);
        startActivity(i);

    }


}
