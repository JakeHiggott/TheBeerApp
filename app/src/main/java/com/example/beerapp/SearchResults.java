package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    ArrayList<String> NamesArray = new ArrayList<>();
    ArrayList<String> BreweryTypeArray = new ArrayList<>();
    ArrayList<String> BreweryAddressArray = new ArrayList<>();
    ArrayList<String> BreweryCityArray = new ArrayList<>();
    ArrayList<String> BreweryStateArray = new ArrayList<>();
    ArrayList<String> BreweryPhoneArray = new ArrayList<>();
    ArrayList<String> BreweryWebsiteArray = new ArrayList<>();
    ArrayList<String> BreweryLongArray = new ArrayList<>();
    ArrayList<String> BreweryLatArray = new ArrayList<>();

    private RecyclerView recyclerView;
    private Adapter_For_Recycler.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //NamesArray.clear();
        //BreweryAddressArray.clear();
        //BreweryTypeArray.clear();

        NamesArray = getIntent().getStringArrayListExtra("NamesArray");
        BreweryTypeArray = getIntent().getStringArrayListExtra("BreweryTypeArray");
        BreweryAddressArray = getIntent().getStringArrayListExtra("BreweryAddressArray");
        BreweryCityArray = getIntent().getStringArrayListExtra("BreweryCityArray");
        BreweryStateArray = getIntent().getStringArrayListExtra("BreweryStateArray");
        BreweryWebsiteArray = getIntent().getStringArrayListExtra("BreweryWebsiteArray");
        BreweryPhoneArray = getIntent().getStringArrayListExtra("BreweryPhoneArray");
        BreweryLongArray = getIntent().getStringArrayListExtra("BreweryLongArray");
        BreweryLatArray = getIntent().getStringArrayListExtra("BreweryLatArray");

        recyclerView = findViewById(R.id.BrewRecycler);
        Log.d("LIST VIEW ARRAY SIZE", String.valueOf(NamesArray.size()));
        setAdapter();









    }

    private void setAdapter() {
        setOnClickListener();
        Adapter_For_Recycler adapter = new Adapter_For_Recycler(NamesArray, listener);
        //Log.d("Actual Items in list:",String.valueOf(adapter.getItemCount()) );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void setOnClickListener() {
        listener = new Adapter_For_Recycler.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), Select_Single_Brewery.class);
                intent.putExtra("position", position);
                intent.putExtra("NamesArray", NamesArray);
                intent.putExtra("BreweryTypeArray",BreweryTypeArray);
                intent.putExtra("BreweryAddressArray",BreweryAddressArray);
                intent.putExtra("BreweryCityArray",BreweryCityArray);
                intent.putExtra("BreweryStateArray",BreweryStateArray);
                intent.putExtra("BreweryWebsiteArray",BreweryWebsiteArray);
                intent.putExtra("BreweryPhoneArray",BreweryPhoneArray);
                intent.putExtra("BreweryLongArray",BreweryLongArray);
                intent.putExtra("BreweryLatArray",BreweryLatArray);
                startActivity(intent);
            }
        };
    }

}