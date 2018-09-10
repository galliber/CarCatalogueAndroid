package com.example.wolverine.carcatalogueandroid.views.CarInfo;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;

import java.io.IOException;

public class CarInfoPresenter implements CarInfoContracts.Presenter {
    private final CarsService mCarsService;
    private final CarsService mPresonalCarsService;
    private CarInfoContracts.View mView;
    private Car mCar;

    public CarInfoPresenter(CarsService carsService, CarsService personalRepository) {
        mCarsService = carsService;
        mPresonalCarsService = personalRepository;
    }

    @Override
    public void subscribe(CarInfoContracts.View view) {
        mView = view;
    }

    @Override
    public void loadCar() {
        mView.showLoading();
        AsyncRunner.runInBackground(() -> {
            mView.showCar(mCar);
            mView.hideLoading();
        });
    }

    @Override
    public void deleteCarClicked() {
        AsyncRunner.runInBackground(() -> {
            try {
                mCarsService.deleteCar(mCar.getId());
                mPresonalCarsService.deleteCar(mCar.getId());
                mView.showDeleteMessage("Deleted " + mCar.getMake() + " " + mCar.getModel() + ".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void deletePersonalCarClicked() {
        AsyncRunner.runInBackground(() -> {
            try {
                mPresonalCarsService.deleteCar(mCar.getId());
                mView.showDeleteMessage("Deleted "+mCar.getMake()+" "+mCar.getModel()+".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void addToPersonalClicked() {
        AsyncRunner.runInBackground(()->{
            try {
                mPresonalCarsService.addCar(mCar);
                mView.showAddMessage("Added "+mCar.getMake()+" "+mCar.getModel()+".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void setCar(Car car) {
        mCar = car;
    }
}
