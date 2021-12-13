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
    private RecyclerView recyclerView;
    private Adapter_For_Recycler.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        NamesArray.clear();
        NamesArray = getIntent().getStringArrayListExtra("NamesArray");
        recyclerView = findViewById(R.id.BrewRecycler);
        Log.d("LIST VIEW ARRAY SIZE", String.valueOf(NamesArray.size()));
        setAdapter();

        /*String allTheNames = "";
        int j = 0;
        while(j < NamesArray.size()){
            allTheNames = allTheNames +" "+ NamesArray.get(j)+",";
            j = j + 1;

            }
        TextView apiTestTextView = findViewById(R.id.namesTestTextView);
        apiTestTextView.setText("");
        apiTestTextView.setText(allTheNames);*/







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
                startActivity(intent);
            }
        };
    }

}