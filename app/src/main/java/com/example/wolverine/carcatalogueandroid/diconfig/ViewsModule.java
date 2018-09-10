package com.example.wolverine.carcatalogueandroid.diconfig;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.example.wolverine.carcatalogueandroid.views.CarsArrayAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {
    @Provides
    public ArrayAdapter<String> carArrayAdapter(Context context){
        return new CarsArrayAdapter(context, android.R.layout.simple_list_item_1);
    }


}
