package com.example.wolverine.carcatalogueandroid.views.AllCars;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;
import com.example.wolverine.carcatalogueandroid.views.BaseDrawerActivity;
import com.example.wolverine.carcatalogueandroid.views.CarInfo.CarInfoActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllCarsActivity extends BaseDrawerActivity implements AllCarsContracts.Navigator {

    public static final long IDENTIFIER = 0;


    @Inject
    AllCarsFragment mAllCarsFragment;


    @Inject
    AllCarsPresenter mPresenter;

    private CarsService mCarsService;

    @BindView(R.id.tb_drawer)
    Toolbar mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars);

        ButterKnife.bind(this);

        mAllCarsFragment.setNavigator(this);
        mAllCarsFragment.setPresenter(mPresenter);


        getFragmentManager().beginTransaction().replace(R.id.content, mAllCarsFragment).commit();


    }

    @Override
    public Toolbar getDrawerToolbar() {
        return mDrawer;
    }

    @Override
    public long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public void navigate(Car car) {
        Intent intent=new Intent(this, CarInfoActivity.class);
        intent.putExtra("car", car);
        startActivity(intent);
    }
}
