package com.example.wolverine.carcatalogueandroid.views.AddCar;

public interface AddCarContracts {
    interface View{
        void setPresenter(Presenter presenter);
        void showAddedMessage(String message);
    }

    interface Presenter{
        void subscribe(View view);
        void addClicked(String s, String toString, String string, String s1, String toString1);

    }
}