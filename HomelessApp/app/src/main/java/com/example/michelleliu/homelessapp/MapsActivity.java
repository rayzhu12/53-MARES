package com.example.michelleliu.homelessapp;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/*
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
*/

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;

import model.FireBaseCallBack;
import model.Restriction;
import model.Shelter;
import model.ShelterManager;

/**
 * Maps Activity
 * @author snack overflow
 */
public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private final ShelterManager sm = ShelterManager.getInstance();
    private List<Shelter> shelterList;

    private static final String TAG = MapsActivity.class.getSimpleName();
    private CameraPosition mCameraPosition;

    // The entry points to the Places API.
    //private GeoDataClient mGeoDataClient;
    //private PlaceDetectionClient mPlaceDetectionClient;

    // The entry point to the Fused Location Provider.
    //private FusedLocationProviderClient mFusedLocationProviderClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    //private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";



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

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(.75, -84.39), 12));
        mMap.setOnInfoWindowClickListener(this);

        Button clearMap = findViewById(R.id.clearmap);
        clearMap.setOnClickListener(v -> populateList(shelterList));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //Location Permission already granted
            //buildGoogleApiClient();
            Log.d(TAG, "hereA");
            mMap.setMyLocationEnabled(true);
        } else {
            //Request Location Permission
            Log.d(TAG, "hereB");
            getLocationPermission();
        }
    }

    /**
     * Shows the given shelters on the map.
     * @param inputList the list of input Shelters
     */
    private void populateList(Iterable<Shelter> inputList) {
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

    private void showCurrentPlace() {
        if (mMap == null) {
            return;
        }
        if (mLocationPermissionGranted) {

        } else {
            // The user has not granted permission.
            Log.i(TAG, "The user did not grant location permission.");
            // Prompt the user for permission.
            getLocationPermission();
        }
    }

    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        Log.d("getLocPerm", "runs");
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        Log.d("onRequestPermResult", "runs");
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    private void updateLocationUI() {
        Log.d("updateLocUI", "runs");
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    //todo: delete eventually
    //sets location permissions back to false after closing the app
    @Override
    public void onStop() {
        super.onStop();
        mLocationPermissionGranted = false;
    }


}
