package com.example.wolverine.carcatalogueandroid.views.AllCars;


import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class AllCarsPresenter implements AllCarsContracts.Presenter {

    private final AsyncRunner mAsyncRunner;
    private List<Car> mCars;
    private AllCarsContracts.View mView;
    private CarsService mCarsService;

    @Inject
    public AllCarsPresenter(@Named("all") CarsService carsService, AsyncRunner asyncRunner){
        this.mCarsService=carsService;
        mAsyncRunner=asyncRunner;
    }

    @Override
    public void subscribe(AllCarsContracts.View view) {
        mView=view;
    }

    @Override
    public void loadCarss() {
        mView.showLoading();
        mAsyncRunner.runInBackground(()->{
            try {
                mCars=mCarsService.getAllCars();
                if(mCars.isEmpty()){
                    mView.showEmptyCarList();
                }
                else {
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
