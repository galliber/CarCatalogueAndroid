package com.example.wolverine.carcatalogueandroid.views.MyCars;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class MyCarsPresenter implements MyCarsContracts.Presenter {

    private final AsyncRunner mAsyncRunner;
    private CarsService mPersonalCarsService;
    private MyCarsContracts.View mView;
    private List<Car> mCars;

    @Inject
    public MyCarsPresenter(@Named("personal") CarsService carsService, AsyncRunner asyncRunner) {
        mPersonalCarsService = carsService;
        mAsyncRunner=asyncRunner;
    }


    @Override
    public void subscribe(MyCarsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadCars() {
        mView.showLoading();
        mAsyncRunner.runInBackground(() -> {
            try {
                mCars = mPersonalCarsService.getAllCars();
                if (mCars.isEmpty()) {
                    mView.showEmptyCarList();
                } else {
                    mView.showCars(mCars);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mView.showError(e);
            }
            mView.hideLoading();
        });
    }

    @Override
    public void selectCar(int position) {
        Car car=mCars.get(position);
        mView.showCarDetails(car);
    }
}
