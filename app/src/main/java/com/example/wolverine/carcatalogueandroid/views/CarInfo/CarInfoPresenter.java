package com.example.wolverine.carcatalogueandroid.views.CarInfo;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

public class CarInfoPresenter implements CarInfoContracts.Presenter {

    private final CarsService mCarsService;
    private final CarsService mPresonalCarsService;
    private final AsyncRunner mAsyncRunner;
    private CarInfoContracts.View mView;
    private Car mCar;

    @Inject
    public CarInfoPresenter(@Named("all") CarsService carsService, @Named("personal") CarsService personalCarService, AsyncRunner asyncRunner) {
        mCarsService = carsService;
        mPresonalCarsService = personalCarService;
        mAsyncRunner = asyncRunner;
    }

    @Override
    public void subscribe(CarInfoContracts.View view) {
        mView = view;
    }

    @Override
    public void loadCar() {
        mView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            mView.showCar(mCar);
            mView.hideLoading();
        });
    }

    @Override
    public void deleteCarClicked() {
        mAsyncRunner.runInBackground(() -> {
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
        mAsyncRunner.runInBackground(() -> {
            try {
                mPresonalCarsService.deleteCar(mCar.getId());
                mView.showDeleteMessage("Deleted " + mCar.getMake() + " " + mCar.getModel() + ".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void addToPersonalClicked() {
        mAsyncRunner.runInBackground(() -> {
            try {
                mPresonalCarsService.addCar(mCar);
                mView.showAddMessage("Added " + mCar.getMake() + " " + mCar.getModel() + ".");
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
