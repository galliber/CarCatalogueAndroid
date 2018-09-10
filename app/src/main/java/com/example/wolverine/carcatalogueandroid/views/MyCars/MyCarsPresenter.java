package com.example.wolverine.carcatalogueandroid.views.MyCars;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

public class MyCarsPresenter implements MyCarsContracts.Presenter {

    private Repository<Car> mMyCarRepository;
    private MyCarsContracts.View mView;
    private List<Car> mCars;

    public MyCarsPresenter(Repository repository) {
        mMyCarRepository = repository;
    }


    @Override
    public void subscribe(MyCarsContracts.View view) {
        mView = view;
    }

    @Override
    public void loadCars() {
        mView.showLoading();
        AsyncRunner.runInBackground(() -> {
            try {
                mCars = mMyCarRepository.getAll();
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