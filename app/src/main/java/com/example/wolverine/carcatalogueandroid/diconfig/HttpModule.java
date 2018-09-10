package com.example.wolverine.carcatalogueandroid.diconfig;

import com.example.wolverine.carcatalogueandroid.http.HttpRequester;
import com.example.wolverine.carcatalogueandroid.http.OkHttpHttpRequester;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester(){
        return new OkHttpHttpRequester();
    }
}
