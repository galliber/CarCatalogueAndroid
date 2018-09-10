package com.example.wolverine.carcatalogueandroid.diconfig;

import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.parsers.Base.JsonParser;
import com.example.wolverine.carcatalogueandroid.parsers.GsonParser;

import dagger.Module;
import dagger.Provides;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<Car> carJsonParser(){
        return new GsonParser<>(Car.class, Car[].class);
    }
}
