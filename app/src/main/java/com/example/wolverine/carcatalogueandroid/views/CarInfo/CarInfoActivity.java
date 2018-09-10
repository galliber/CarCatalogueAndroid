package com.example.wolverine.carcatalogueandroid.views.CarInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.wolverine.carcatalogueandroid.AndroidApplication;
import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;
import com.example.wolverine.carcatalogueandroid.views.BaseDrawerActivity;


public class CarInfoActivity extends BaseDrawerActivity {

    public static final int IDENTIFIER = 572;
    private CarInfoFragment mCarInfoFragment;
    private Toolbar mDrawer;
    private CarInfoContracts.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        Intent intent=getIntent();
        Car car= (Car) intent.getSerializableExtra("car");
        CarsService mPersonalCarsService=AndroidApplication.getPersonalCarsService();
        CarsService mCarsService = AndroidApplication.getCarsService();
        mPresenter=new CarInfoPresenter(mCarsService, mPersonalCarsService);
        mPresenter.setCar(car);
        mCarInfoFragment=new CarInfoFragment();
        mCarInfoFragment.setPresenter(mPresenter);
        getFragmentManager().beginTransaction().replace(R.id.content, mCarInfoFragment).commit();
        mDrawer=findViewById(R.id.mDrawer);
    }

    @Override
    public Toolbar getDrawerToolbar() {
        return mDrawer;
    }

    @Override
    public long getIdentifier() {
        return IDENTIFIER;
    }

}
