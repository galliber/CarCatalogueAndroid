package com.example.wolverine.carcatalogueandroid.views.CarInfo;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.io.IOException;

public class CarInfoPresenter implements CarInfoContracts.Presenter {
    private final Repository<Car> mCarRepository;
    private final Repository<Car> mPersonalRepository;
    private CarInfoContracts.View mView;
    private Car mCar;

    public CarInfoPresenter(Repository repository, Repository personalRepository) {
        mCarRepository = repository;
        mPersonalRepository = personalRepository;
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
                mCarRepository.delete(mCar.getId());
                mPersonalRepository.delete(mCar.getId());
                mView.showDeleteMessage("Deleted "+mCar.getMake()+" "+mCar.getModel()+".");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void deletePersonalCarClicked() {
        AsyncRunner.runInBackground(() -> {
            try {
                mPersonalRepository.delete(mCar.getId());
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
                mPersonalRepository.add(mCar);
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
