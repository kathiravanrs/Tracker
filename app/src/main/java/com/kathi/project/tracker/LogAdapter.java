package com.kathi.project.tracker;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class LogAdapter extends ArrayAdapter<Log> {
    public LogAdapter(Context context, int resource, List<Log> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.list_item, parent, false);
        }

        TextView nameView = convertView.findViewById(R.id.name);
        TextView locationView = convertView.findViewById(R.id.location);
        TextView timeView = convertView.findViewById(R.id.time);
        TextView dateView = convertView.findViewById(R.id.date);

        Log message = getItem(position);
        nameView.setText(message.getName());
        timeView.setText(message.getTime());
        locationView.setText(message.getLocation());
        dateView.setText(message.getDate());
        return convertView;

    }
}
