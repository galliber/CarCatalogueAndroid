package com.example.wolverine.carcatalogueandroid.services.base;
import com.example.wolverine.carcatalogueandroid.models.Car;

import java.io.IOException;
import java.util.List;

public interface CarsService {
    List<Car> getAllCars() throws IOException;

    Car getDetailsById(int id) throws IOException;

    List<Car> getFilteredCars(String pattern) throws IOException;
}