package com.example.wolverine.carcatalogueandroid.views.CarInfo;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarInfoFragment extends Fragment implements CarInfoContracts.View {


    private TextView mTvMake;
    private TextView mTvModel;
    private TextView mTvPower;
    private TextView mTvCubicCapacity;
    private Button mBtnAddToPersonal;
    private Button mBtnDeleteFromPersonal;
    private Button mBtnDeleteFromAll;
    private ImageView mImImage;
    private RelativeLayout mContent;
    private ProgressBar mLoading;
    private CarInfoContracts.Presenter mPresenter;

    public CarInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_info, container, false);

        mContent = view.findViewById(R.id.content);
        mLoading = view.findViewById(R.id.loading);
        mTvMake = view.findViewById(R.id.tv_make);
        mTvModel = view.findViewById(R.id.tv_model);
        mTvPower = view.findViewById(R.id.tv_power);
        mImImage=view.findViewById(R.id.im_image);
        mTvCubicCapacity = view.findViewById(R.id.tv_cubic_capacity);
        mBtnAddToPersonal = view.findViewById(R.id.btn_add_to_personal);
        mBtnAddToPersonal.setText("Add to personal.");
        mBtnAddToPersonal.setOnClickListener((item) -> {
            mPresenter.addToPersonalClicked();
        });

        mBtnDeleteFromPersonal = view.findViewById(R.id.btn_delete_from_personal);
        mBtnDeleteFromPersonal.setText("Delete from Personal.");
        mBtnDeleteFromPersonal.setOnClickListener((item) -> {
            mPresenter.deletePersonalCarClicked();
        });
        mBtnDeleteFromAll = view.findViewById(R.id.btn_delete_from_all);
        mBtnDeleteFromAll.setText("Delete Car.");
        mBtnDeleteFromAll.setOnClickListener((item) -> {
            mPresenter.deleteCarClicked();
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadCar();
    }

    @Override
    public void setPresenter(CarInfoContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCar(Car car) {
        runOnUI(() -> {
            mTvMake.setText("Make: " + car.getMake());
            mTvModel.setText("Model: " + car.getModel());
            mTvCubicCapacity.setText("Cubic Capacity: " + car.getCubicCapacity());
            mTvPower.setText("Power: " + car.getPower());

            Glide.with(getContext()).load(car.getImgUrl()).into(mImImage);
        });
    }

    @Override
    public void showLoading() {
        runOnUI(() -> {
            mLoading.setVisibility(View.VISIBLE);
            mContent.setVisibility(View.GONE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUI(() -> {
            mLoading.setVisibility(View.GONE);
            mContent.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void showDeleteMessage(String str) {
        runOnUI(() -> {
            Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void showAddMessage(String message) {
        runOnUI(() -> {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });
    }

    public void runOnUI(Runnable action) {
        getActivity().runOnUiThread(action);
    }

}
