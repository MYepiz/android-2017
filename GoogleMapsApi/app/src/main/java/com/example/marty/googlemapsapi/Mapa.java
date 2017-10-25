package com.example.marty.googlemapsapi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Mapa extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng Moscow = new LatLng(55.747889, 37.665462);
        mMap.addMarker(new MarkerOptions().position(Moscow).title("привет"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Moscow));

        LatLng Mexico = new LatLng(19.432314, -99.153809);
        mMap.addMarker(new MarkerOptions().position(Mexico).title("Que royo Mexico"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Mexico));


        double latitude = 17.385044;
        double longitude = 78.486671;

// create marker
        MarkerOptions marker = new MarkerOptions().position(new LatLng(28.6355977, -106.0934118)).title("Hello Shiwawa!!");

// Changing marker icon
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.marker1600,null);
        Bitmap b=bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false);

        marker.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

// adding marker
        mMap.addMarker(marker);


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(),15));
                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
                // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                return true;
            }
        });
    }
}
