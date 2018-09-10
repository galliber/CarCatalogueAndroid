package com.example.wolverine.carcatalogueandroid.views.AddCar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wolverine.carcatalogueandroid.AndroidApplication;
import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;
import com.example.wolverine.carcatalogueandroid.views.BaseDrawerActivity;


public class AddCarActivity extends BaseDrawerActivity {

    public static final long IDENTIFIER = 100;

    private AddCarFragment mFragemnt;
    private Toolbar mToollbar;
    private AddCarContracts.Presenter mPresenter;
    private Repository<Car> mRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        mRepo= AndroidApplication.getCarRepository(Car.class, Car[].class);
        mToollbar=findViewById(R.id.tb_drawer);
        mPresenter=new AddCarPresenter(mRepo);
        mFragemnt=new AddCarFragment();
        mFragemnt.setPresenter(mPresenter);

        getFragmentManager().beginTransaction().replace(R.id.content, mFragemnt).commit();
    }

    @Override
    public Toolbar getDrawerToolbar() {
        return mToollbar;
    }

    @Override
    public long getIdentifier() {
        return IDENTIFIER;
    }
}