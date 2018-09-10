package com.example.wolverine.carcatalogueandroid.views.MyCars;

import com.example.wolverine.carcatalogueandroid.models.Car;

import java.util.List;

public interface MyCarsContracts {
    interface View{
        void setPresenter(MyCarsContracts.Presenter presenter);
        void showLoading();
        void hideLoading();
        void showCars(List<Car> cars);
        void showError(Exception e);
        void showEmptyCarList();
        void showCarDetails(Car car);
    }

    interface Presenter{
        void subscribe(View view);
        void loadCars();
        void selectCar(int position);
    }

    interface Navigator{
        void navigate(Car car);
    }
}