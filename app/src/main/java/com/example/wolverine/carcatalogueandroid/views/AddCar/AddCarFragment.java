package com.example.wolverine.carcatalogueandroid.views.AddCar;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wolverine.carcatalogueandroid.R;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCarFragment extends Fragment implements AddCarContracts.View {

    private AddCarContracts.Presenter mPresenter;

    @Inject
    public AddCarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_car, container, false);

        EditText mMake=view.findViewById(R.id.et_make);

        EditText mModel=view.findViewById(R.id.et_model);

        EditText mCubicCapacity=view.findViewById(R.id.et_cubic_capacity);

        EditText mPower=view.findViewById(R.id.et_power);

        EditText mImageUrl=view.findViewById(R.id.et_image_url);

        Button mButton=view.findViewById(R.id.btn_add);
        mButton.setText("Add Car");
        mButton.setOnClickListener((item)->{
            mPresenter.addClicked(mMake.getText().toString(), mModel.getText().toString(), mCubicCapacity.getText().toString(), mPower.getText().toString(), mImageUrl.getText().toString());
        });

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        mPresenter.subscribe(this);

    }

    @Override
    public void setPresenter(AddCarContracts.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void showAddedMessage(String message) {
        runOnUI(()->{
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });
    }

    public void runOnUI(Runnable action){
        getActivity().runOnUiThread(action);
    }
}
