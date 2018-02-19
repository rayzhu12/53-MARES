package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.util.List;

import model.Model;
import model.UserInfo;

public class RegistrationActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private Button register;

    private UserInfo user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        Button cancel = (Button) findViewById(R.id.cancel_button);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                mEmailView.setText("");
////                mPasswordView.setText("");
////                mPasswordView.clearFocus();
//                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
//            }
//        });

        name = (EditText) findViewById(R.id.Name);
        email =(EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);

        register = (Button) findViewById(R.id.create_account);
        register.setOnClickListener(addNewUser);
    }

    private View.OnClickListener addNewUser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Model model = Model.getInstance();
            user = new UserInfo("", "", "");

            user.setName(name.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());

            model.checkUser(user);
            model.printArray();
        }
    };

}
