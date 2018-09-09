package com.example.wolverine.carcatalogueandroid.views.CarInfo;

import com.example.wolverine.carcatalogueandroid.models.Car;

public interface CarInfoContracts {
    interface View{
        void setPresenter(Presenter presenter);
        void showCar(Car car);
        void showLoading();
        void hideLoading();
        void showDeleteMessage(String str);

        void showAddMessage(String s);
    }

    interface Presenter{
        void subscribe(View view);
        void loadCar();
        void deleteCarClicked();
        void deletePersonalCarClicked();
        void addToPersonalClicked();
        void setCar(Car car);

    }
}
