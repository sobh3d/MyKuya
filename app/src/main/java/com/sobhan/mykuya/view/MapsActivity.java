package com.sobhan.mykuya.view;


import androidx.fragment.app.FragmentActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;

import android.os.Bundle;
import android.os.Handler;

import android.util.TypedValue;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sobhan.mykuya.R;
import com.sobhan.mykuya.utils.Constant;
import com.sobhan.mykuya.utils.ProgressButton;
import com.sobhan.mykuya.viewmodel.ResultViewModel;


import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ImageView imageViewMarker;
    private Constant constant = new Constant();
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        imageViewMarker = findViewById(R.id.marker);
        getLatLng();
        setUpMap();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);


    }


    private void setUpMap() {

        // Find myLocationButton view
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        @SuppressLint("ResourceType") View locationButton = ((View) mapFragment.getView().findViewById(1).getParent()).findViewById(2);

        if (locationButton != null && locationButton.getLayoutParams() instanceof RelativeLayout.LayoutParams) {

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();


            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);


            final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                    getResources().getDisplayMetrics());
            params.setMargins(margin, margin, margin, margin);

            locationButton.setLayoutParams(params);
        }
    }

    private void getLatLng() {
        view = findViewById(R.id.btn_get_lat_lng);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressButton progressButton = new ProgressButton(MapsActivity.this, view);
                progressButton.buttonActivated();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        constant.setLAT(String.valueOf(mMap.getCameraPosition().target.latitude));
                        constant.setLNG(String.valueOf(mMap.getCameraPosition().target.longitude));
                        Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                        progressButton.buttonFinished();
                        startActivity(intent);
                    }
                }, 500);
            }
        });


    }


}
