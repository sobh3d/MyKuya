package com.sobhan.mykuya.view;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Color;

import android.graphics.drawable.ColorDrawable;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import android.view.animation.Transformation;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.model.LatLng;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.sobhan.mykuya.R;
import com.sobhan.mykuya.model.Product;

import com.sobhan.mykuya.model.WhatNew;
import com.sobhan.mykuya.utils.Constant;
import com.sobhan.mykuya.utils.CustomGridLayout;
import com.sobhan.mykuya.view.adapter.AllAdapter;
import com.sobhan.mykuya.view.adapter.FeaturesAdapter;
import com.sobhan.mykuya.view.adapter.WhatNewAdapter;
import com.sobhan.mykuya.viewmodel.ResultViewModel;


import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private ConstraintLayout layoutLocation;
    private RecyclerView featuredRecyclerView, allRecyclerView, whatNewRecyclerView;
    private ImageView imageCollapse;
    private ResultViewModel resultViewModel;
    private TextView textViewAddress;
    ActionBar actionBar;
    private ProgressBar progressBarFeatured, progressBarAllService;
    Constant constant;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        initViewModel();
        checkLocationPermission();
        initUi();

        listeners();
        requestCall();
        setWhatNewRecyclerView();


    }

    private void initViewModel() {
        resultViewModel = ViewModelProviders.of(this)
                .get(ResultViewModel.class);
    }

    private void requestCall() {

        resultViewModel.getResultLiveData().observe(this, this::setFeaturedRecyclerViews);
        resultViewModel.getAllResultLiveData().observe(this, this::setAllRecyclerViews);

    }

    private void setFeaturedRecyclerViews(List<Product> products) {

        //FeaturedRecyclerView
        FeaturesAdapter featuresAdapter = new FeaturesAdapter(getApplicationContext(), products);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        featuredRecyclerView.setLayoutManager(mLayoutManager);
        featuredRecyclerView.setItemAnimator(new DefaultItemAnimator());
        featuredRecyclerView.setAdapter(featuresAdapter);
        progressBarFeatured.setVisibility(View.GONE);
        featuredRecyclerView.setVisibility(View.VISIBLE);


    }

    private void setAllRecyclerViews(List<Product> products) {

        AllAdapter allAdapter = new AllAdapter(getApplicationContext(), products);
        int numOfColumns = 3;
        RecyclerView.LayoutManager manager = new CustomGridLayout(getApplicationContext(), numOfColumns);
        allRecyclerView.setLayoutManager(manager);
        allRecyclerView.setItemAnimator(new DefaultItemAnimator());
        allRecyclerView.setAdapter(allAdapter);
        progressBarAllService.setVisibility(View.GONE);
        allRecyclerView.setVisibility(View.VISIBLE);

    }

    @SuppressLint("SetTextI18n")
    private void initUi() {
        TextView textViewHeader = findViewById(R.id.tv_hello);
        featuredRecyclerView = findViewById(R.id.rv_featured);
        allRecyclerView = findViewById(R.id.rv_all_service);
        whatNewRecyclerView = findViewById(R.id.rv_what_new);
        imageCollapse = findViewById(R.id.iv_collapse);
        layoutLocation = findViewById(R.id.layout_location);
        textViewAddress = findViewById(R.id.tv_location);
        progressBarAllService = findViewById(R.id.pb_all_service);
        progressBarFeatured = findViewById(R.id.pb_featured);
        progressBarAllService.setVisibility(View.VISIBLE);
        progressBarFeatured.setVisibility(View.VISIBLE);
        textViewHeader.setText(Html.fromHtml("Good Afternoon," + "<b>" + "Sobhan!" + "</b>"));


    }

    private void listeners() {

        layoutLocation.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });

        imageCollapse.setOnClickListener(new View.OnClickListener() {
            private boolean expanded = true;

            @Override
            public void onClick(View view) {

                if (expanded) {
                    collaps();

                    expanded = false;
                } else {
                    expand();

                    expanded = true;
                }

            }
        });
    }

    private void expand() {
        imageCollapse.setImageResource(R.drawable.open_layout);
        ViewGroup.LayoutParams params = allRecyclerView.getLayoutParams();


        Animation animation = new Animation() {
            @Override
            public boolean willChangeBounds() {
                return true;
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                params.height = interpolatedTime == 450
                        ? 450
                        : (int) (450 * interpolatedTime);
                allRecyclerView.requestLayout();
            }
        };

        animation.setDuration(17);
        allRecyclerView.startAnimation(animation);

    }

    private void collaps() {

        final int initialHeight = allRecyclerView.getMeasuredHeight();
        Animation animation = new Animation() {
            @Override
            public boolean willChangeBounds() {
                return true;
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                allRecyclerView.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                allRecyclerView.requestLayout();
            }
        };

        animation.setDuration(17);
        allRecyclerView.startAnimation(animation);
        imageCollapse.setImageResource(R.drawable.close);
    }


    private void setWhatNewRecyclerView() {
        Uri uri1 = Uri.parse("android.resource://com.sobhan.mykuya/" + R.drawable.howtouseapp);
        Uri uri2 = Uri.parse("android.resource://com.sobhan.mykuya/" + R.drawable.listyourservice);
        String path1 = uri1.toString();
        String path2 = uri2.toString();
        WhatNew[] whatNew = new WhatNew[]{
                new WhatNew("How To Use The App", "Getting access to on-demand", path1),
                new WhatNew("List Your Service On MyKuya", "Do You Offer Manpowe", path2),
        };

        //FeaturedRecyclerView
        WhatNewAdapter whatNewAdapter = new WhatNewAdapter(getApplicationContext(), whatNew);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false);
        whatNewRecyclerView.setLayoutManager(mLayoutManager);
        whatNewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        whatNewRecyclerView.setAdapter(whatNewAdapter);
    }

    private String getAddress(Context context, double lat, double lng) {
        Constant constant = new Constant();
        LatLng latLng = new LatLng(Double.parseDouble(constant.getLAT()), Double.parseDouble(constant.getLNG()));
        String address = null;


        try {

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());


            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            Log.d("momy", "momy");
            if (addresses.size() > 0) {
                Address address1 = addresses.get(0);
                address = address1.getAddressLine(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("ex", e.toString());
        }


        return address;

    }


    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)


            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {


                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
}



