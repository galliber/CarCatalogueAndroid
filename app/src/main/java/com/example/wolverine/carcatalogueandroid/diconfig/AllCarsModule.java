package com.example.wolverine.carcatalogueandroid.diconfig;


import com.example.wolverine.carcatalogueandroid.views.AllCars.AllCarsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AllCarsModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AllCarsFragment allCarsFragment();
}
