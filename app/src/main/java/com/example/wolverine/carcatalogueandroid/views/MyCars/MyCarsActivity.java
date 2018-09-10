package com.example.wolverine.carcatalogueandroid.views.MyCars;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wolverine.carcatalogueandroid.AndroidApplication;
import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;
import com.example.wolverine.carcatalogueandroid.repositories.base.Repository;
import com.example.wolverine.carcatalogueandroid.views.BaseDrawerActivity;
import com.example.wolverine.carcatalogueandroid.views.CarInfo.CarInfoActivity;

public class MyCarsActivity extends BaseDrawerActivity implements MyCarsContracts.Navigator {

    public static final long IDENTIFIER = 10;
    private Toolbar mDrawer;
    private MyCarsFragment mFragment;
    private MyCarsPresenter mPresenter;
    private Repository<Car> mMyCarRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars);

        mDrawer=findViewById(R.id.tb_drawer);
        mFragment=new MyCarsFragment();
        mMyCarRepository= AndroidApplication.getPersonalCarRepository(Car.class, Car[].class);
        mPresenter=new MyCarsPresenter(mMyCarRepository);
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
