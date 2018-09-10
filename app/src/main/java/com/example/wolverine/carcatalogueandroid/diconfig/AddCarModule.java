package com.example.wolverine.carcatalogueandroid.diconfig;



import com.example.wolverine.carcatalogueandroid.views.AddCar.AddCarFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AddCarModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddCarFragment addCarFragment();
}
