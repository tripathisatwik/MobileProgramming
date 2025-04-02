package com.example.chaptersix;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chaptersix.R;

public class SimpleListAdapter extends ArrayAdapter<String> {
    Activity context;
    String[] title;
    String[] description;
    int[] image;

    public SimpleListAdapter(Activity context,String[] title, String[] description, int[] image){
        super(context, R.layout.simple_custom_list_item,title);
        this.context = context;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.simple_custom_list_item,null,true);
        TextView txtTitle = rowView.findViewById(R.id.titeTv);
        TextView descTitle = rowView.findViewById(R.id.descTv);
        txtTitle.setText(title[position]);
        descTitle.setText(description[position]);
        return rowView;
    }
}
