package com.example.wolverine.carcatalogueandroid.diconfig;

import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.CarHttpRepository;
import com.example.wolverine.carcatalogueandroid.repositories.PersonalCarHttpRepository;
import com.example.wolverine.carcatalogueandroid.services.HttpCarsService;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    @Named("all")
    public CarsService allCarsService(CarHttpRepository<Car> repository){
        return new HttpCarsService(repository);
    }

    @Provides
    @Named("personal")
    public CarsService personalCarsService(PersonalCarHttpRepository<Car> repository){
        return new HttpCarsService(repository);
    }
}
