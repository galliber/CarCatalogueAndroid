package com.example.wolverine.carcatalogueandroid.views.AllCars;


import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.io.IOException;
import java.util.List;

public class AllCarsPresenter implements AllCarsContracts.Presenter {

    private List<Car> mCars;
    private AllCarsContracts.View mView;
    private Repository<Car> mCarRepository;

    public AllCarsPresenter(Repository repository){
        this.mCarRepository=repository;
    }

    @Override
    public void subscribe(AllCarsContracts.View view) {
        mView=view;
    }

    @Override
    public void loadCarss() {
        mView.showLoading();
        AsyncRunner.runInBackground(()->{
            try {
                mCars=mCarRepository.getAll();
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
