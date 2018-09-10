package com.example.wolverine.carcatalogueandroid.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import javax.inject.Inject;

public class CarsArrayAdapter extends ArrayAdapter<String> {
    @Inject
    public CarsArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        View view1 = super.getView(position, contentView, viewGroup);
        TextView tv = view1.findViewById(android.R.id.text1);
        tv.setTextColor(Color.GREEN);
        return view1;
    }
}
