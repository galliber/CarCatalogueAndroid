package com.example.wolverine.carcatalogueandroid.diconfig;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.async.AsyncRunnerImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncModule {
    @Provides
    public AsyncRunner asyncRunner() {
        return new AsyncRunnerImpl();
    }
}
