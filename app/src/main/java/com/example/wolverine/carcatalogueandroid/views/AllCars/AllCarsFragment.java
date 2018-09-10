package com.example.wolverine.carcatalogueandroid.views.AllCars;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
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
import com.example.wolverine.carcatalogueandroid.views.CarsArrayAdapter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCarsFragment extends Fragment implements AllCarsContracts.View {

    @BindView(R.id.lv_cars)
    ListView mCarsListView;

    @BindView((R.id.loading))
    ProgressBar mLoadingBar;

    @Inject
    ArrayAdapter<String> mAdapter;
    private List<Car> mCars;
    private AllCarsContracts.Navigator mNavigator;
    private AllCarsContracts.Presenter mPresenter;

    @Inject
    public AllCarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_cars, container, false);

        ButterKnife.bind(this, view);

        mCarsListView.setAdapter(mAdapter);
        return view;
    }

    @OnItemClick({R.id.lv_cars})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPresenter.selectCar(position);
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.subscribe(this);
        mPresenter.loadCarss();
    }


    @Override
    public void showCars(List<Car> cars) {
        List<String> carNames=cars
                .stream()
                .map(str-> str.getMake()+" "+str.getModel())
                .collect(Collectors.toList());
        mCars=cars;
        runOnUI(()->{
            mAdapter.clear();
            mAdapter.addAll(carNames);
        });
    }

    @Override
    public void setPresenter(AllCarsContracts.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showError(IOException e) {
        runOnUI(()->{
            Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT);
        });
    }

    @Override
    public void showLoading() {
        runOnUI(()->{
            mCarsListView.setVisibility(View.GONE);
            mLoadingBar.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void hideLoading() {
        runOnUI(()->{
            mCarsListView.setVisibility(View.VISIBLE);
            mLoadingBar.setVisibility(View.GONE);
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
        runOnUI(()->{
            mNavigator.navigate(car);
        });
    }

    private void runOnUI(Runnable runnable) {
        getActivity().runOnUiThread(runnable);
    }

    public void setNavigator(AllCarsContracts.Navigator navigator){
        mNavigator=navigator;
    }

}
