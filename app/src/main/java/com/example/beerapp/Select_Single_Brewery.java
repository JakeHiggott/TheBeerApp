package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Select_Single_Brewery extends AppCompatActivity {
    int position = 0;
    ArrayList<String> NamesArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_single_brewery);
        TextView nameTxt = findViewById(R.id.breweryNameTextView);
        NamesArray.clear();
        int position = 0;
        position = getIntent().getIntExtra("position",0);
        NamesArray = getIntent().getStringArrayListExtra("NamesArray");
        nameTxt.setText(NamesArray.get(position));

    }

}