package com.example.wolverine.carcatalogueandroid.views.MyCars;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarsFragment extends Fragment implements MyCarsContracts.View {


    private MyCarsContracts.Presenter mPresenter;

    @BindView(R.id.lv_cars)
    ListView mCarsListView;
    @BindView(R.id.loading)
    ProgressBar mLoading;
    @Inject
    ArrayAdapter<String> mAdapter;
    private List<Car> mCars;
    private MyCarsContracts.Navigator mNavigator;

    @Inject
    public MyCarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cars, container, false);

        ButterKnife.bind(this, view);

        mCarsListView.setAdapter(mAdapter);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadCars();

    }

    @Override
    public void setPresenter(MyCarsContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        runOnUI(() -> {
            mLoading.setVisibility(View.VISIBLE);
            mCarsListView.setVisibility(View.GONE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUI(() -> {
            mLoading.setVisibility(View.GONE);
            mCarsListView.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void showCars(List<Car> cars) {
        mCars = cars;
        List<String> names = cars
                .stream()
                .map(item -> item.getMake() + " " + item.getModel())
                .collect(Collectors.toList());
        runOnUI(() -> {
            mAdapter.clear();
            mAdapter.addAll(names);

        });
    }

    @Override
    public void showError(Exception e) {
        runOnUI(() -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT);
        });
    }

    @Override
    public void showEmptyCarList() {
        runOnUI(() -> {
            Toast.makeText(getContext(), "There are no cars in this list!", Toast.LENGTH_SHORT);
        });
    }

    @Override
    public void showCarDetails(Car car) {
        runOnUI(() -> {
            mNavigator.navigate(car);
        });
    }

    public void setNavigator(MyCarsContracts.Navigator navigator) {
        mNavigator = navigator;
    }

    private void runOnUI(Runnable action) {
        getActivity().runOnUiThread(action);
    }

    @OnItemClick(R.id.lv_cars)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.selectCar(position);
    }
}
