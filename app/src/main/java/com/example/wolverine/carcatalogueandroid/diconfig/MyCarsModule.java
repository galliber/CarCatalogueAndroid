package com.example.wolverine.carcatalogueandroid.diconfig;

import com.example.wolverine.carcatalogueandroid.views.MyCars.MyCarsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyCarsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract MyCarsFragment myCarsFragment();
}
