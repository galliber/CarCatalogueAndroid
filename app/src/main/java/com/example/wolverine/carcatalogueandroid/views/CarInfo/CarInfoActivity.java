package com.example.wolverine.carcatalogueandroid.views.CarInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CarInfoActivity extends BaseDrawerActivity {
    private static final int IDENTIFIER = 572;

    @Inject
    CarInfoFragment mCarInfoFragment;

    @BindView(R.id.tb_drawer)
    Toolbar mDrawer;
    @Inject
    CarInfoPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);

        ButterKnife.bind(this);

        Intent intent=getIntent();
        Car car= (Car) intent.getSerializableExtra("car");
        mPresenter.setCar(car);
        mCarInfoFragment.setPresenter(mPresenter);
        getFragmentManager().beginTransaction().replace(R.id.content, mCarInfoFragment).commit();
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
