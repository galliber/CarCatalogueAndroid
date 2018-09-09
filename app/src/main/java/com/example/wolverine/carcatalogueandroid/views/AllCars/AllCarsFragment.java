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

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllCarsFragment extends Fragment implements AdapterView.OnItemClickListener, AllCarsContracts.View {


    private ListView mCarsListView;
    private ProgressBar mLoadingBar;
    private ArrayAdapter<String> mAdapter;
    private List<Car> mCars;
    private AllCarsContracts.Navigator mNavigator;
    private AllCarsContracts.Presenter mPresenter;

    public AllCarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_cars, container, false);
        mCarsListView = view.findViewById(R.id.lv_cars);
        mLoadingBar = view.findViewById(R.id.loading);
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
