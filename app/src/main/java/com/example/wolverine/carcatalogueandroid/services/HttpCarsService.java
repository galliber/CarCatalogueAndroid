package com.example.wolverine.carcatalogueandroid.services;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HttpCarsService implements CarsService {

    private Repository<Car> mRepository;

    public HttpCarsService(Repository<Car> repository){
        this.mRepository=repository;
    }

    @Override
    public List<Car> getAllCars() throws IOException {
        return mRepository.getAll();
    }

    @Override
    public Car getInfoById(int id) throws IOException {
        return mRepository.getById(id);
    }

    @Override
    public void addCar(Car car) throws IOException {
        mRepository.add(car);
    }

    @Override
    public void deleteCar(int id) throws IOException {
        mRepository.delete(id);
    }
}
