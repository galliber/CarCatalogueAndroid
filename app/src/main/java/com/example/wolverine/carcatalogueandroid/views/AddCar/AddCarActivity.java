package com.example.wolverine.carcatalogueandroid.views.AddCar;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.services.base.CarsService;
import com.example.wolverine.carcatalogueandroid.views.BaseDrawerActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddCarActivity extends BaseDrawerActivity {

    public static final long IDENTIFIER = 100;

    @Inject
    AddCarFragment mFragemnt;

    @BindView(R.id.tb_drawer)
    Toolbar mToollbar;


    private CarsService mCarsService;
    @Inject
    AddCarPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        ButterKnife.bind(this);

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
