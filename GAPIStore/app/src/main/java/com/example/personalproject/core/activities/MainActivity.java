package com.example.personalproject.core.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalproject.R;
import com.example.personalproject.core.fragments.AppBarFragment;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, AppBarFragment.class, null)
                    .commit();
        }
        setEvents();
    }


    private void setEvents() {}



}