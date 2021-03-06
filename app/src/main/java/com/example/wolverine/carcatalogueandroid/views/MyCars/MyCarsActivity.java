package com.example.wolverine.carcatalogueandroid.views.MyCars;


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

public class MyCarsActivity extends BaseDrawerActivity implements MyCarsContracts.Navigator {

    public static final long IDENTIFIER = 10;

    @BindView(R.id.tb_drawer)
    Toolbar mDrawer;
    @Inject
    MyCarsFragment mFragment;
    @Inject
    MyCarsPresenter mPresenter;
    private CarsService mCarsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        ButterKnife.bind(this);

        mFragment.setPresenter(mPresenter);
        mFragment.setNavigator(this);
        getFragmentManager().beginTransaction().replace(R.id.content, mFragment).commit();

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
