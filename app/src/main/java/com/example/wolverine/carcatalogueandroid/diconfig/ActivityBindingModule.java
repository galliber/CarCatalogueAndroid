package com.example.wolverine.carcatalogueandroid.diconfig;


import com.example.wolverine.carcatalogueandroid.views.AddCar.AddCarActivity;
import com.example.wolverine.carcatalogueandroid.views.AllCars.AllCarsActivity;
import com.example.wolverine.carcatalogueandroid.views.CarInfo.CarInfoActivity;
import com.example.wolverine.carcatalogueandroid.views.MyCars.MyCarsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = AllCarsModule.class)
    abstract AllCarsActivity allCarsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = MyCarsModule.class)
    abstract MyCarsActivity myCarsActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = AddCarModule.class)
    abstract AddCarActivity addCarActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = CarInfoModule.class)
    abstract CarInfoActivity carInfoActivity();
}