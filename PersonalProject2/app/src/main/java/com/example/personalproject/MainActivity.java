package com.example.personalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.personalproject.endpoints.DatabaseProvider;

public class MainActivity extends AppCompatActivity {

    DatabaseProvider databaseProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.databaseProvider = new DatabaseProvider(this);


    }

    public Context getContext(){
        return this.getApplicationContext();
    }
}