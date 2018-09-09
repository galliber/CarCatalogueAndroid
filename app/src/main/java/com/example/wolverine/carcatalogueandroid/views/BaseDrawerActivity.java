package com.example.wolverine.carcatalogueandroid.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.example.wolverine.carcatalogueandroid.R;
import com.example.wolverine.carcatalogueandroid.views.AddCar.AddCarActivity;
import com.example.wolverine.carcatalogueandroid.views.AllCars.AllCarsActivity;
import com.example.wolverine.carcatalogueandroid.views.MyCars.MyCarsActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public abstract class BaseDrawerActivity extends AppCompatActivity {
    private void setupDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem allCarsItem = new PrimaryDrawerItem().withIdentifier(AllCarsActivity.IDENTIFIER).withIcon(R.drawable.material_drawer_circle_mask).withName("All Cars");
        PrimaryDrawerItem myCarsItem = new PrimaryDrawerItem().withIdentifier(MyCarsActivity.IDENTIFIER).withIcon(R.drawable.material_drawer_circle_mask).withName("My Cars");
        PrimaryDrawerItem addCarItem= new PrimaryDrawerItem().withIdentifier(AddCarActivity.IDENTIFIER).withIcon(R.drawable.material_drawer_circle_mask).withName("Add Car");


//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
                .addDrawerItems(
                        allCarsItem,
                        new DividerDrawerItem(),
                        myCarsItem,
                        new DividerDrawerItem(),
                        addCarItem
                )
                .withSelectedItem(getIdentifier())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        long identifier=drawerItem.getIdentifier();
                        if(identifier==getIdentifier()){
                            return false;
                        };
                        navigate(identifier);
                        return false;
                    }
                })
                .build();
    }

    public abstract Toolbar getDrawerToolbar();
    public abstract long getIdentifier();
    void navigate(long n){
        if(n== AllCarsActivity.IDENTIFIER){
            Intent intent=new Intent(this, AllCarsActivity.class);
            startActivity(intent);
        }
        else if(n==MyCarsActivity.IDENTIFIER){
            Intent intent=new Intent(this, MyCarsActivity.class);
            startActivity(intent);
        }
        else if(n== AddCarActivity.IDENTIFIER){
            Intent intent=new Intent(this, AddCarActivity.class);
            startActivity(intent);
        }
        return;
    }
    @Override
    protected void onStart(){
        super.onStart();
        setupDrawer();
    }

}
