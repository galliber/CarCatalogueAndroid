package com.example.wolverine.carcatalogueandroid.services.base;
import com.example.wolverine.carcatalogueandroid.models.Car;

import java.io.IOException;
import java.util.List;

public interface CarsService {
    List<Car> getAllCars() throws IOException;
    Car getInfoById(int id) throws IOException;
    void addCar(Car car) throws IOException;
    void deleteCar(int id) throws IOException;
}