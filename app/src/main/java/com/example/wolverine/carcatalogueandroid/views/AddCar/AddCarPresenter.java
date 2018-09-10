package com.example.wolverine.carcatalogueandroid.views.AddCar;

import com.example.wolverine.carcatalogueandroid.async.AsyncRunner;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;

import java.io.IOException;
import java.util.Random;

public class AddCarPresenter implements AddCarContracts.Presenter {

    private AddCarContracts.View mView;
    private Repository<Car> mRepository;

    public AddCarPresenter(Repository<Car> repository) {
        mRepository = repository;
    }

    @Override
    public void subscribe(AddCarContracts.View view) {
        mView = view;
    }

    @Override
    public void addClicked(String make, String model, String cubicCapacity, String power, String imgUrl) {
        AsyncRunner.runInBackground(() -> {
            try {
                if (make.length() < 2 || model.length() < 2 || cubicCapacity.length() == 0 || power.length() == 0) {
                    mView.showAddedMessage("Please fill blanks");
                } else {
                    Random r = new Random();
                    int[] id = new int[1];
                    id[0] = r.nextInt(5000);
                    while (mRepository.getAll().stream().anyMatch(i -> i.getId() == id[0])) {
                        id[0] = r.nextInt(5000);
                    }
                    Car car = new Car(id[0], make, model, Integer.parseInt(cubicCapacity), Integer.parseInt(power), imgUrl);
                    mRepository.add(car);
                    mView.showAddedMessage("Added " + make + " " + model + ".");
                }
            } catch (IOException e) {
                e.printStackTrace();
                mView.showAddedMessage("Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                mView.showAddedMessage("Invalid input!");
            }
        });
    }

    @Override
    public void loadContent() {

    }

}