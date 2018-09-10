package com.example.wolverine.carcatalogueandroid.views.MyCars;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.models.Car;

import java.util.List;
import java.util.stream.Collectors;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCarsFragment extends Fragment implements AdapterView.OnItemClickListener, MyCarsContracts.View {


    private MyCarsContracts.Presenter mPresenter;
    private ListView mCarsListView;
    private ProgressBar mLoading;
    private ArrayAdapter<String> mAdapter;
    private List<Car> mCars;
    private MyCarsContracts.Navigator mNavigator;

    public MyCarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cars, container, false);
        mCarsListView = view.findViewById(R.id.lv_cars);
        mLoading = view.findViewById(R.id.loading);
        mAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View contentView, ViewGroup viewGroup) {
                View view1 = super.getView(position, contentView, viewGroup);
                TextView tv = view1.findViewById(android.R.id.text1);
                tv.setTextColor(Color.GREEN);
                return view1;
            }
        };
        mCarsListView.setAdapter(mAdapter);
        mCarsListView.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.selectCar(position);
    }
}