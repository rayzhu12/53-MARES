package com.example.michelleliu.homelessapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {

        //For registration of token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //To displaying token on logcat
        Log.d("TOKEN: ", refreshedToken);

    }

}