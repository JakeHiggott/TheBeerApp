package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.io.IOException;

//Source 1: This youtube video shows how to use okhttp and it is a library I am using to handle the API
//https://www.youtube.com/watch?v=oGWJ8xD2W6k
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;


public class SearchForBrewery extends AppCompatActivity {
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_brewery);
        findBrewBackButton();
        apiTest();


    }
    private void findBrewBackButton() {
        Button displayButton = findViewById(R.id.findBrewBackButton);
        displayButton.setOnClickListener(view -> finish());
    }

    //API STUFF




    public void apiTest(){
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.openbrewerydb.org/breweries?by_city=san_diego";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String myResponse = response.body().string();
                    Log.d("apitest",myResponse);


                }
            }
        });

    }


}