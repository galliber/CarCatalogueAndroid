package com.example.wolverine.carcatalogueandroid.diconfig;

import com.example.wolverine.carcatalogueandroid.Constants;
import com.example.wolverine.carcatalogueandroid.http.HttpRequester;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.parsers.Base.JsonParser;
import com.example.wolverine.carcatalogueandroid.repositories.CarHttpRepository;
import com.example.wolverine.carcatalogueandroid.repositories.PersonalCarHttpRepository;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoriesModule {

    @Provides
    public Repository<Car> repository(HttpRequester httpRequester, JsonParser<Car> jsonParser){
        return new CarHttpRepository<>(httpRequester, Constants.SERVER_URL, jsonParser);
    }

    @Provides
    public PersonalCarHttpRepository<Car> personalCarHttpRepository(HttpRequester httpRequester, JsonParser<Car> jsonParser) {
        return new PersonalCarHttpRepository<>(httpRequester, Constants.SERVER_URL, jsonParser);
    }

    @Provides
    public CarHttpRepository<Car> carHttpRepository(HttpRequester httpRequester, JsonParser<Car> jsonParser){
        return new CarHttpRepository<>(httpRequester, Constants.SERVER_URL, jsonParser);
    }
}
