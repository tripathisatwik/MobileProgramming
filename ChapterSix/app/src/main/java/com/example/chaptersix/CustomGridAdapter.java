package com.example.chaptersix;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomGridAdapter extends ArrayAdapter<String> {
    Activity context;
    String[] title;
    String[] description;
    int[] image;

    public CustomGridAdapter(Activity context, String[] title, String[] description, int[] image){
        super(context, R.layout.simple_custom_list_item,title);
        this.context = context;
        this.title = title;
        this.description = description;
        this.image = image;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_grid_item,null,true);

        TextView gridTitleTxtView = view.findViewById(R.id.gridTitleItem);
        TextView girdDesctxtView = view.findViewById(R.id.gridDescItem);
        ImageView gridImgView = view.findViewById(R.id.gridImageItem);

        gridTitleTxtView.setText(title[position]);
        girdDesctxtView.setText(description[position]);
        gridImgView.setImageResource(image[position]);

        return view;
    }
}
