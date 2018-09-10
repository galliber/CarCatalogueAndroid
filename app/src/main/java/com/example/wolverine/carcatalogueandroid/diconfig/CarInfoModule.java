package com.example.wolverine.carcatalogueandroid.diconfig;


import com.example.wolverine.carcatalogueandroid.views.CarInfo.CarInfoFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CarInfoModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract CarInfoFragment carInfoFragment();
}
