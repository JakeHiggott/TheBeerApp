package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Select_Single_Brewery extends AppCompatActivity {

    //Declare all the api stuff that is getting passed in by the extra's from SearchResults
    int position = 0;
    ArrayList<String> NamesArray = new ArrayList<>();
    ArrayList<String> BreweryTypeArray = new ArrayList<>();
    ArrayList<String> BreweryAddressArray = new ArrayList<>();
    ArrayList<String> BreweryCityArray = new ArrayList<>();
    ArrayList<String> BreweryStateArray = new ArrayList<>();
    ArrayList<String> BreweryPhoneArray = new ArrayList<>();
    ArrayList<String> BreweryWebsiteArray = new ArrayList<>();
    ArrayList<String> BreweryLongArray = new ArrayList<>();
    ArrayList<String> BreweryLatArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_single_brewery);


        TextView brewNameTxt = findViewById(R.id.breweryNameTextView);
        TextView brewTypeTxt = findViewById(R.id.breweryTypeTextView);
        TextView brewAddressTxt = findViewById(R.id.breweryAddressTextView);
        TextView brewPhoneTxt = findViewById(R.id.breweryPhoneTextView);
        TextView brewWebsiteTxt = findViewById(R.id.breweryWebsiteTextView);


        //Reset the data everytime so nothing funny happens -_-
        NamesArray.clear();
        int position = 0;


        position = getIntent().getIntExtra("position",0);
        NamesArray = getIntent().getStringArrayListExtra("NamesArray");
        BreweryTypeArray = getIntent().getStringArrayListExtra("BreweryTypeArray");
        BreweryAddressArray = getIntent().getStringArrayListExtra("BreweryAddressArray");
        BreweryCityArray = getIntent().getStringArrayListExtra("BreweryCityArray");
        BreweryStateArray = getIntent().getStringArrayListExtra("BreweryStateArray");
        BreweryWebsiteArray = getIntent().getStringArrayListExtra("BreweryWebsiteArray");
        BreweryPhoneArray = getIntent().getStringArrayListExtra("BreweryPhoneArray");
        BreweryLongArray = getIntent().getStringArrayListExtra("BreweryLongArray");
        BreweryLatArray = getIntent().getStringArrayListExtra("BreweryLatArray");



        brewNameTxt.setText(NamesArray.get(position));
        brewTypeTxt.setText(BreweryTypeArray.get(position));
        brewAddressTxt.setText(BreweryAddressArray.get(position)+", "+BreweryCityArray.get(position)+", "+BreweryStateArray.get(position));
        brewPhoneTxt.setText(BreweryPhoneArray.get(position));
        brewWebsiteTxt.setText(BreweryWebsiteArray.get(position));

        Log.d("LONGITUDE + LATITUDE", BreweryLongArray.get(position)+","+BreweryLatArray.get(position));



    }

}