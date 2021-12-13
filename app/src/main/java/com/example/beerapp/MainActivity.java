package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBrew();
    }
    private void findBrew(){
        Button displayButton = findViewById(R.id.SearchForBrewButton);
        displayButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SearchForBrewery.class)));

    }
}