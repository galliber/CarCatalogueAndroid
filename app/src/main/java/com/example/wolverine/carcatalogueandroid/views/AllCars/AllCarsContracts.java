package com.example.wolverine.carcatalogueandroid.views.AllCars;

import com.example.wolverine.carcatalogueandroid.models.Car;

import java.io.IOException;
import java.util.List;

public interface AllCarsContracts {
    interface View{
        void showCars(List<Car> cars);
        void setPresenter(Presenter presenter);

        void showError(IOException e);

        void showLoading();

        void hideLoading();

        void showEmptyCarList();

        void showCarDetails(Car car);
    }

    interface Presenter{
        void subscribe(View view);

        void loadCarss();

        void selectCar(int position);
    }

    interface Navigator{

        void navigate(Car car);
    }
}
