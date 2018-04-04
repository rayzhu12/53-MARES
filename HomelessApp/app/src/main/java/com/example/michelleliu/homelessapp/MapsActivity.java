package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import model.Restriction;
import model.Shelter;
import model.ShelterManager;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ShelterManager sm = ShelterManager.getInstance();
    private List<Shelter> shelterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Log.d("help", Boolean.toString(sm == null));
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
        //InputStream inputStream = getResources().openRawResource(R.raw.stats);

        /*
        CSVFile csvFile = new CSVFile(inputStream);
        ShelterListActivity.setShelterList(csvFile.read());
        ShelterListActivity.getSm().setShelterList(ShelterListActivity.getShelterList());
        */

        shelterList = sm.getShelterList();
        populateList(shelterList);

        //final List<Shelter>[] filteredList = new List[];

        RadioButton rb1 = findViewById(R.id.radio_male);
        rb1.setOnClickListener(
                view -> populateList(sm.findShelterByRestriction(Restriction.MALE)));
        RadioButton rb2 = findViewById(R.id.radio_female);
        rb2.setOnClickListener(
                view -> populateList(sm.findShelterByRestriction(Restriction.FEMALE)));
        RadioButton rb3 = findViewById(R.id.radio_nonbinary);
        rb3.setOnClickListener(
                view -> populateList(sm.findShelterByRestriction(Restriction.NONBINARY)));
        RadioButton rb4 = findViewById(R.id.radio_families);
        rb4.setOnClickListener(
                view -> populateList(sm.findShelterByRestriction(Restriction.FAMILIES)));
        RadioButton rb5 = findViewById(R.id.radio_ya);
        rb5.setOnClickListener(
                view -> populateList(sm.findShelterByRestriction(Restriction.YOUNG_ADULTS)));
        RadioButton rb6 = findViewById(R.id.radio_children);
        rb6.setOnClickListener(
                view -> populateList(sm.findShelterByRestriction(Restriction.CHILDREN)));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.75, -84.39), 12));
        mMap.setOnInfoWindowClickListener(this);

        Button clearMap = findViewById(R.id.clearmap);
        clearMap.setOnClickListener(v -> populateList(shelterList));
    }

    /**
     * Shows the given shelters on the map.
     * @param inputList the list of input Shelters
     */
    public void populateList(List<Shelter> inputList) {
        mMap.clear();
        for (Shelter entry : inputList) {
            LatLng coord = new LatLng(entry.getLatitude(), entry.getLongitude());
            mMap.addMarker(new MarkerOptions().position(coord).title(entry.getName()).snippet("tap "
                + "for details"));
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //ArrayList<Shelter> shelterList = (ArrayList) ShelterListActivity.getShelterList();
        for (Shelter entry : shelterList) {
            if (entry.getName().equals(marker.getTitle())) {
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("passed shelter", entry);
                startActivity(intent);
            }
        }
    }
}
