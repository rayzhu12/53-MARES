package com.example.michelleliu.homelessapp.dummy;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.michelleliu.homelessapp.DetailActivity;
import com.example.michelleliu.homelessapp.R;
import com.example.michelleliu.homelessapp.ShelterListActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.CSVFile;
import model.Shelter;
import model.ShelterManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);
        ShelterListActivity.setShelterList(csvFile.read());
        ShelterListActivity.getSm().setShelterList(ShelterListActivity.getShelterList());

        for (Shelter entry : ShelterListActivity.getShelterList()) {
            LatLng coord = new LatLng(entry.getLatitude(), entry.getLongitude());
            mMap.addMarker(new MarkerOptions().position(coord).title(entry.getName()).snippet("tap for details"));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.75, -84.39), 12));
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        ArrayList<Shelter> shelterList = (ArrayList) ShelterListActivity.getShelterList();
        for (Shelter entry : shelterList) {
            if (entry.getName().equals(marker.getTitle())) {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("passed shelter", entry);
                startActivity(intent);
            }
        }
    }
}
