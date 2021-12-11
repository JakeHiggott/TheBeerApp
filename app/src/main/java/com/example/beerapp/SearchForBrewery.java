package com.example.beerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Objects;

//Source 1: This youtube video shows how to use okhttp and it is a library I am using to handle the API
//https://www.youtube.com/watch?v=oGWJ8xD2W6k
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;


public class SearchForBrewery extends AppCompatActivity {
    String apiName = "";
    ArrayList<String> NamesArray = new ArrayList<>();
    @Override



    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_brewery);

        brewSearchButton();
        findBrewBackButton();










    }
    private void findBrewBackButton() {
        Button displayButton = findViewById(R.id.findBrewBackButton);
        displayButton.setOnClickListener(view -> finish());
    }
    private void brewSearchButton() {
        Button displayButton = findViewById(R.id.brewSearchButton);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiName = "";
                apiCallZipCode("40206");

                int i = 0;
                while(i == 0){
                    if(NamesArray.size() == 0){


                    }else{
                        TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                        apiTestTextView.setText(NamesArray.get(1));
                        i = 1;
                    }

                }



            }
    });}


    //API STUFF




    public void apiCallZipCode(String zipcode){
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.openbrewerydb.org/breweries?by_postal=";
        Request request = new Request.Builder()
                .url(url+zipcode)
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



                    try {
                        Log.d("apitest",myResponse);
                        JSONArray jsonarray = new JSONArray(myResponse);
                        int i=0;
                        String name = "";
                        while (i < jsonarray.length()) {
                            NamesArray.add(jsonarray.getJSONObject(i).getString("name")) ;
                            i = i + 1;
                        }

                        Log.d("Amount of results from api call: ", String.valueOf(jsonarray.length()));



                    } catch (JSONException e) {
                        Log.d("apitest","Catch clause occurred");
                        e.printStackTrace();
                    }




                    /*SearchForBrewery.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                           TextView apiTestTextView = findViewById(R.id.apitTestTextView);
                            apiTestTextView.setText(myResponse);
                        }
                    });*/


                }
            }
        });

    }



}