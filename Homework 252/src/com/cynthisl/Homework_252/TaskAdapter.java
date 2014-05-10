package com.cynthisl.Homework_252;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cynthia on 5/8/2014.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    List<Task> items;

    public TaskAdapter(Context context, int textViewResourceId, ArrayList<Task> objects) {
        super(context, textViewResourceId);
        items = objects;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        View v = convertView;

        if(v==null){
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.task_list_row,null);
        }

        /*Task t = items.get(pos);
        if(t!=null){
            TextView text = (TextView) v.findViewById(R.id.task_name);
            text.setText(t.name);
        }*/

        TextView text = (TextView) v.findViewById(R.id.task_name);
        text.setText("spam");
        return v;
    }
}
