package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.List;

import model.Model;
import model.UserInfo;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name;
    private EditText age;
    private EditText number;
    private Button register;
    private UserInfo user;

    private Spinner typeOfUser;

    public enum adminOrUser {
        Admin,
        User;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        // return email.equals("user");
        if (email.equals("user")) {
            return true;
        }
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        // return password.equals("pass");
        if (password.equals("pass")) {
            return true;
        }
        return password.length() >= 8;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.Name);
        age =(EditText) findViewById(R.id.Age);
        number = (EditText) findViewById(R.id.phoneNumber);

        typeOfUser = (Spinner) findViewById(R.id.typeOfUser);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, adminOrUser.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfUser.setAdapter(adapter2);

        register = (Button) findViewById(R.id.create_account);
        register.setOnClickListener(addNewUser);

        /***************************************************
         * NEED TO FIX CODE FOR CANCEL BUTTON SINCE REGISTRATION IS NOW ON A DIFFERENT SCREEN
         **************************************************/
        Button cancel2 = (Button) findViewById(R.id.cancel2);
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                age.setText("");
                number.setText("");
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });


    }

    private View.OnClickListener addNewUser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Model model = Model.getInstance();

            boolean cancel = false;
            View focusView = null;

//            // Check for a valid password, if the user entered one.
//            if (!isPasswordValid(password.getText().toString())) {
//                password.setError(getString(R.string.error_invalid_password));
//                focusView = password;
//                cancel = true;
//            }
//            if (TextUtils.isEmpty(password.getText().toString())) {
//                password.setError(getString(R.string.error_field_required));
//                focusView = password;
//                cancel = true;
//            }
//
//            // Check for a valid email address.
//            if (TextUtils.isEmpty(email.getText().toString())) {
//                email.setError(getString(R.string.error_field_required));
//                focusView = email;
//                cancel = true;
//            }
//            if (!isEmailValid(email.getText().toString())) {
//                email.setError(getString(R.string.error_invalid_email));
//                focusView = email;
//                cancel = true;
//            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                // Show a progress spinner, and kick off a background task to
                // perform the user login attempt.
                user = new UserInfo("", 0, "", "");
                user = new UserInfo(name.getText().toString(), Integer.parseInt(age.getText().toString()), number.getText().toString(), typeOfUser.getSelectedItem().toString());

//                user.setName(name.getText().toString());
//                user.setEmail(email.getText().toString());
//                user.setPassword(password.getText().toString());
//                user.setUserType(typeOfUser.getSelectedItem().toString());


                if (model.checkUser(user)) {
                    startActivity(new Intent(RegistrationActivity.this, AppActivity.class));
                }
//                } else {
//                    email.setError(getString(R.string.error_duplicate_email));
//                    email.requestFocus();
//                }
                // model.printArray();

            }
        }
    };
}
