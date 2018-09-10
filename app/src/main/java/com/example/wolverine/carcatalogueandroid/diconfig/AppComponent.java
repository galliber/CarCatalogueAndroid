package com.example.wolverine.carcatalogueandroid.diconfig;

import android.app.Application;


import com.example.wolverine.carcatalogueandroid.AndroidApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {
        ApplicationModule.class,
        ViewsModule.class,
        ActivityBindingModule.class,
        ServicesModule.class,
        RepositoriesModule.class,
        ParsersModule.class,
        HttpModule.class,
        AsyncModule.class,
        ServicesModule.class,
        HttpModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<AndroidApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}